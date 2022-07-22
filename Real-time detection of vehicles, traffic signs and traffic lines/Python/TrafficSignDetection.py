# traffic sign detection

import cv2

cap = cv2.VideoCapture("VideoTrafficSigns.mp4")

ret, original_image = cap.read()

original_image_height = original_image.shape[0]
original_image_width = original_image.shape[1]

sign1 = cv2.imread("noImage.jpg")
sign2 = cv2.imread("noImage.jpg")
sign3 = cv2.imread("noImage.jpg")

car_cascade = cv2.CascadeClassifier('trafficSign.xml')


def traffic_sign_detection(original_image):
    global sign1, sign2, sign3
    image = original_image[0:original_image_height, round(original_image_width / 2 + 250):original_image_width - 50]

    gray = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)
    signs = car_cascade.detectMultiScale(gray, 1.3, 35)

    for (x1, y1, x2, y2) in signs:
        x1 = x1 + round(original_image_width / 2 + 250)
        cv2.rectangle(original_image, (x1, y1), (x1 + x2, y1 + y2), (0, 255, 0), 2)
        cv2.rectangle(original_image, (x1, y1 - 40), (x1 + x2, y1), (0, 255, 0), -2)
        cv2.putText(original_image, 'Sign', (x1, y1 - 10), cv2.FONT_HERSHEY_SIMPLEX, 0.7, (255, 255, 255), 2)

        resized_dimension = (140, 140)
        temporary_sign = original_image[y1:y1 + y2, x1: x1 + x2]
        temporary_sign_resized = cv2.resize(temporary_sign, resized_dimension)
        temporary_sign1_resized = cv2.resize(sign1, resized_dimension)

        errorL2 = cv2.norm(temporary_sign_resized, temporary_sign1_resized, cv2.NORM_L2)
        similarity = 1 - errorL2 / (140 * 140)

        if similarity > 0.35:
            sign1 = original_image[y1:y1 + y2, x1: x1 + x2]
        if similarity < 0.25:
            sign3 = sign2
            sign2 = sign1
            sign1 = original_image[y1:y1 + y2, x1: x1 + x2]


def display_traffic_signs():
    global sign1, sign2, sign3
    x1 = round(original_image_width / 4) - 40
    y1 = original_image_height
    x2 = round(original_image_width / 2) + 80
    height = 200

    cv2.rectangle(original_image, (x1, y1 - height), (x1 + x2, y1), (192, 192, 192), -2)
    cv2.putText(original_image, 'Last three traffic signs', (round((x1 + x2) / 2 + 5), y1 - 210), cv2.FONT_HERSHEY_SIMPLEX, 0.7, (11, 11, 11), 2)

    resized_dimension = (140, 140)
    resized_sign1 = cv2.resize(sign1, resized_dimension)
    resized_sign2 = cv2.resize(sign2, resized_dimension)
    resized_sign3 = cv2.resize(sign3, resized_dimension)

    original_image[y1 - height + 30:y1 - height + 30 + resized_sign1.shape[0], round((x1 + x2) / 2 - 150):round((x1 + x2) / 2 - 150) + resized_sign1.shape[1]] = resized_sign1
    original_image[y1 - height + 30:y1 - height + 30 + resized_sign1.shape[0], round((x1 + x2) / 2 + 70):round((x1 + x2) / 2 + 70) + resized_sign1.shape[1]] = resized_sign2
    original_image[y1 - height + 30:y1 - height + 30 + resized_sign1.shape[0], round((x1 + x2) / 2 + 290):round((x1 + x2) / 2 + 290) + resized_sign1.shape[1]] = resized_sign3

    cv2.putText(original_image, '#1', (round((x1 + x2) / 2 - 95), y1 - 7), cv2.FONT_HERSHEY_TRIPLEX, 0.7, (11, 11, 11), 1)
    cv2.putText(original_image, '#2', (round((x1 + x2) / 2 + 125), y1 - 7), cv2.FONT_HERSHEY_TRIPLEX, 0.7, (11, 11, 11), 1)
    cv2.putText(original_image, '#3', (round((x1 + x2) / 2 + 345), y1 - 7), cv2.FONT_HERSHEY_TRIPLEX, 0.7, (11, 11, 11), 1)


while True:
    ret, original_image = cap.read()

    if not ret:
        cap = cv2.VideoCapture("VideoTrafficSigns.mp4")
        continue

    traffic_sign_detection(original_image)
    display_traffic_signs()

    cv2.imshow('image', original_image)
    key = cv2.waitKey(1)
    if key == 27:
        break
cv2.destroyAllWindows()
