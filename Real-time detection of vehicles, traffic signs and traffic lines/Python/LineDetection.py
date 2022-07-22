# line detection

import numpy as np
import cv2

# params
cap = cv2.VideoCapture("VideoLineDetection.mp4")
ret, original_image = cap.read()

# pixel (x,y) locations of the left and right road lines
left_line_x = 0
left_line_y = 0
right_line_x = 0
right_line_y = 0

# road width in meters
road_width_in_meters = 3

# height and width of original image in pixels
original_image_height = original_image.shape[0]
original_image_width = original_image.shape[1]

# upper limit
upper_limit_height = round(original_image_height / 2 + 40)

# location of the middle of the car (camera location) along the x axis
middle_x = round(original_image_width * 0.555)

# height from the bottom of the image
distance_arrowed_line_height = 10
distance_in_meters_height = distance_arrowed_line_height + 5
turn_arrowed_line_height = 75
turn_text_height = turn_arrowed_line_height + 8


def add_brightness(image):
    image_HLS = cv2.cvtColor(image, cv2.COLOR_RGB2HLS)
    image_HLS = np.array(image_HLS, dtype=np.float64)

    brightness_coefficient = 0.8
    image_HLS[:, :, 1] = image_HLS[:, :, 1] * brightness_coefficient
    image_HLS[:, :, 1][image_HLS[:, :, 1] > 255] = 255

    image_HLS = np.array(image_HLS, dtype=np.uint8)
    image_RGB = cv2.cvtColor(image_HLS, cv2.COLOR_HLS2RGB)

    return image_RGB

def add_morphological_transformations(image):
    kernel = np.ones((2, 2), np.uint8)
    image = cv2.morphologyEx(image, cv2.MORPH_CLOSE, kernel)

    kernel = np.ones((2, 2), np.uint8)
    image = cv2.morphologyEx(image, cv2.MORPH_OPEN, kernel)

    kernel = np.ones((4, 4), np.uint8)
    image = cv2.dilate(image, kernel)

    return image


def isolate_traffic_lines(image):
    image_height = image.shape[0]
    image_width = image.shape[1]

    region_of_interest = [
        (150, image_height),
        (image_width / 4 + 300, 0),
        (3 * (image_width / 4) - 200, 0),
        (image_width - 150, image_height)
    ]

    vertices = np.array([region_of_interest], np.int32)
    mask = np.zeros_like(image)
    channel = image.shape[2]
    match_mask_color = (255,) * channel
    cv2.fillPoly(mask, vertices, match_mask_color)
    image = cv2.bitwise_and(image, mask)

    return image


def add_blue_lines(image):
    image_height = image.shape[0]
    image_width = image.shape[1]

    image = cv2.line(image, (middle_x, image_height), (middle_x, image_height - 150), (0, 0, 255), 3)

    image_line_detection = image[image_height - 50:image_height, 0:image_width]
    mask = cv2.inRange(image_line_detection, (0, 250, 0), (0, 255, 0))
    image_line_detection[mask != 0] = [0, 0, 255]

    return image


def calculate_line_locations(image):
    global right_line_x, right_line_y, left_line_x, left_line_y
    height = image.shape[0]
    width = image.shape[1]

    for x in range(middle_x + 10, width):
        if image[height - 1, x][2] > 250 and image[height - 10, x][2] > 250:
            right_line_x = x
            right_line_y = height
            break
    image = cv2.line(image, (right_line_x, right_line_y), (right_line_x, right_line_y - 50), (255, 0, 0), 3)

    for x in range(middle_x - 10, 0, -1):
        if image[height - 1, x][2] > 250 and image[height - 10, x][2] > 250:
            left_line_x = x
            left_line_y = height
            break
    cv2.line(image, (left_line_x, left_line_y), (left_line_x, left_line_y - 50), (255, 0, 0), 3)


def calculate_car_position(image):
    image_height = image.shape[0]

    road_width_pixel = right_line_x - left_line_x
    right_part_of_road_pixel = right_line_x - middle_x
    left_part_of_road_pixel = middle_x - left_line_x
    right_part_of_road_in_meters = round((right_part_of_road_pixel / road_width_pixel) * road_width_in_meters, 2)
    left_part_of_road_in_meters = round((left_part_of_road_pixel / road_width_pixel) * road_width_in_meters, 2)

    cv2.arrowedLine(original_image, (middle_x, image_height - distance_arrowed_line_height + upper_limit_height), (right_line_x, image_height - distance_arrowed_line_height + upper_limit_height), (0, 0, 0), 2)
    cv2.arrowedLine(original_image, (middle_x, image_height - distance_arrowed_line_height + upper_limit_height), (left_line_x, image_height - distance_arrowed_line_height + upper_limit_height), (0, 0, 0), 2)
    cv2.putText(original_image, str(right_part_of_road_in_meters) + "m", (middle_x + 30, image_height - distance_in_meters_height + upper_limit_height), cv2.FONT_HERSHEY_SIMPLEX, 0.7, (0, 0, 0), 2, cv2.LINE_AA)
    cv2.putText(original_image, str(left_part_of_road_in_meters) + "m", (middle_x - 100, image_height - distance_in_meters_height + upper_limit_height), cv2.FONT_HERSHEY_SIMPLEX, 0.7, (0, 0, 0), 2, cv2.LINE_AA)

    if abs(right_part_of_road_pixel - left_part_of_road_pixel) > 20:
        if right_part_of_road_pixel > left_part_of_road_pixel:
            cv2.arrowedLine(original_image, (middle_x, image_height - turn_arrowed_line_height + upper_limit_height), (middle_x + 185, image_height - turn_arrowed_line_height + upper_limit_height), (0, 0, 0), 2)
            cv2.putText(original_image, 'Turn right', (middle_x + 9, image_height - turn_text_height + upper_limit_height), cv2.FONT_HERSHEY_SIMPLEX, 1, (0, 0, 0), 2, cv2.LINE_AA)
        else:
            cv2.arrowedLine(original_image, (middle_x, image_height - turn_arrowed_line_height + upper_limit_height), (middle_x - 185, image_height - turn_arrowed_line_height + upper_limit_height), (0, 0, 0), 2)
            cv2.putText(original_image, 'Turn left', (middle_x - 152, image_height - turn_text_height + upper_limit_height), cv2.FONT_HERSHEY_SIMPLEX, 1, (0, 0, 0), 2, cv2.LINE_AA)


def isolate_white_color_and_turn_it_green(image):

    image = cv2.cvtColor(image, cv2.COLOR_RGB2HLS)
    thresh = (150, 255)
    image[(image >= thresh[0]) & (image <= thresh[1])] = 255
    image[image < 250] = 0

    image = cv2.cvtColor(image, cv2.COLOR_HLS2RGB)
    image = cv2.cvtColor(image, cv2.COLOR_RGB2HLS)

    return image


def merge_image_with_blank_image(image):
    height_blank = original_image_height - image.shape[0]
    width_blank = original_image_width

    blank_image = np.zeros(shape=[height_blank, width_blank, 3], dtype=np.uint8)
    image = np.concatenate((blank_image, image), axis=0)

    return image


def line_detection(original_image):
    global upper_limit_height
    image = original_image[round(original_image_height / 2 + 40):original_image_height, 0:original_image_width]

    image = add_brightness(image)
    image = add_morphological_transformations(image)
    image = isolate_white_color_and_turn_it_green(image)
    image = isolate_traffic_lines(image)
    image = add_blue_lines(image)
    calculate_line_locations(image)
    calculate_car_position(image)

    return merge_image_with_blank_image(image)

while True:
    ret, original_image = cap.read()

    if not ret:
        cap = cv2.VideoCapture("VideoLineDetection.mp4")
        continue

    original_image = cv2.addWeighted(original_image, 0.95, line_detection(original_image), 500, 0)

    cv2.imshow('image', original_image)
    key = cv2.waitKey(1)
    if key == 27:
        break
cv2.destroyAllWindows()

