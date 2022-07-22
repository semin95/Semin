# car detection

import cv2

cap = cv2.VideoCapture("VideoCarDetection.mp4")


ret, original_image = cap.read()

original_image_height = original_image.shape[0]
original_image_width = original_image.shape[1]

slow_down_show = False
break_show = False

car_cascade = cv2.CascadeClassifier('cars.xml')

def vehicle_detection(original_image):
    image = original_image[round(original_image_height / 2 - 100):original_image_height, 200:original_image_width - 200]

    gray = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)
    vehicles = car_cascade.detectMultiScale(gray, 1.25, 6)

    image_height = image.shape[0]
    image_width = image.shape[1]

    top_left_x = round(image_width / 2 - 10)
    top_left_y = round(image_height / 3)
    down_left_x = round(image_width / 2 - 200)
    down_left_y = image_height

    top_right_x = round(image_width / 2 + 110)
    top_right_y = round(image_height / 3)
    down_right_x = round(image_width / 2 + 350)
    down_right_y = image_height

    upper_limit_slow_down = down_left_y // 2
    lower_limit_slow_down = top_left_y
    upper_limit_break = down_left_y
    lower_limit_break = down_left_y // 2

    cv2.line(image, (down_left_x, down_left_y), (top_left_x, top_left_y), (0, 0, 255), 1)
    cv2.line(image, (down_right_x, down_right_y), (top_right_x, top_right_y), (0, 0, 255), 1)
    cv2.line(image, (top_left_x, top_left_y), (top_right_x, top_right_y), (0, 0, 255), 1)
    cv2.line(image, (top_left_x - 47, upper_limit_slow_down), (top_right_x + 60, upper_limit_slow_down), (0, 0, 255), 1)

    for (x1,y1,x2,y2) in vehicles:
        global slow_down_show, break_show
        cv2.rectangle(image, (x1, y1), (x1 + x2, y1 + y2), (0 ,255, 0),2)
        cv2.rectangle(image, (x1, y1 - 40), (x1 + x2, y1), (0, 255, 0), -2)
        cv2.putText(image, 'Vehicle', (x1, y1 - 10), cv2.FONT_HERSHEY_SIMPLEX, 0.7, (255, 255, 255), 2)

        bottom_line_middle_x = x1 + (x2 // 2)
        bottom_line_middle_y = y1 + y2
        if (top_left_x < bottom_line_middle_x < top_right_x) and (upper_limit_slow_down > bottom_line_middle_y > lower_limit_slow_down):
            slow_down_show = True
        else:
            slow_down_show = False
        if (top_left_x < bottom_line_middle_x < top_right_x) and (lower_limit_break <= bottom_line_middle_y < upper_limit_break):
            break_show = True
        else:
            break_show = False

    if slow_down_show:
        cv2.rectangle(image, (down_left_x, down_left_y), (down_right_x, down_right_y - 82), (122,160,255),-2)
        cv2.putText(image, 'Slow down!', (down_left_x + 10,down_left_y - 10), cv2.FONT_HERSHEY_SIMPLEX, 3, (0, 0, 0), 2)
    elif break_show:
        cv2.rectangle(image, (down_left_x, down_left_y), (down_right_x, down_right_y - 82), (0 ,0, 255),-2)
        cv2.putText(image, 'Break!', (down_left_x + 120,down_left_y - 10), cv2.FONT_HERSHEY_SIMPLEX, 3, (0, 0, 0), 2)


while True:
    ret, original_image = cap.read()

    if not ret:
        cap = cv2.VideoCapture("VideoCarDetection.mp4")
        continue

    vehicle_detection(original_image)

    cv2.imshow('image', original_image)
    key = cv2.waitKey(1)
    if key == 27:
        break
cv2.destroyAllWindows()
