# from __future__ import print_function
import Image

import Adafruit_ILI9341 as TFT
import Adafruit_GPIO as GPIO
import Adafruit_GPIO.SPI as SPI

from twython import Twython

# authentication and linking account
APP_KEY = "MDLWg51hXxnNENttjUn6FN5iy"
APP_SECRET = "3jGJJ1m1vhoHsK3Uz57raOUdyQLvye6yfyMoml3M93S6Raiqrk"
OAUTH_TOKEN = "732289723327217665-ALplYL9qc9OOB5R7sWbmJixkicwtyNg"
OAUTH_TOKEN_SECRET = "uLWGcOR69qNqqBCAKz5hmXMLOA021Gw4Xt6hdLl2a2ZH3"
twitter = Twython(APP_KEY, APP_SECRET, OAUTH_TOKEN, OAUTH_TOKEN_SECRET)

# Raspberry Pi configuration.
DC = 24
RST = 25
SPI_PORT = 0
SPI_DEVICE = 0

# create TFT LCD display class
disp = TFT.ILI9341(DC, rst=RST, spi=SPI.SpiDev(SPI_PORT, SPI_DEVICE, max_speed_hz=64000000))

# initialize display
disp.begin()

# load default font
font = ImageFont.load_default()
# alternatively load a TTF font


def draw_rotated_text(image, text, position, angle, font, fill=(255, 255, 255)):
    draw = ImageDraw.Draw(image)
    width, height = draw.textsize(text, font=font)
    textimage = Image.new('RGBA', (width, height), (0, 0, 0, 0))
    textdraw = ImageDraw.Draw(textimage)
    textdraw.text((0,0), text, font=font, fill=fill)
    rotated = textimage.rotate(angle, expand=1)
    image.paste(rotated, position, rotated)


def update_status_foo():
    print("Status: ", end="")
    status = raw_input()
    twitter.update_status(status=status)
    print("Status updated!")
    draw_rotated_text(disp.buffer, "Status: " + status, (2, 200), 0, font, fill=(255, 255, 255))


def send_message_foo():
    print("Recipient's username: ", end="")
    recipient = raw_input()
    print("Message: ", end="")
    message = raw_input()
    twitter.send_direct_message(screen_name=recipient, text=message)
    print("Message sent!")
    # split_message = string_splitter(message)
    draw_rotated_text(disp.buffer, "Recipient's username: " + recipient, (2, 200), 0, font, fill=(255, 255, 255))
    draw_rotated_text(disp.buffer, "Message: " + message, (2, 220), 0, font, fill=(255, 255, 255))


def receive_message_foo():
    json_msgs = twitter.get_direct_messages(count=1)
    for json_msg in json_msgs:
        print("Sender: " + json_msg['sender_screen_name'])
        draw_rotated_text(disp.buffer, "Sender: " + json_msg['sender_screen_name'], (2, 200), 0, font, fill=(255, 255, 255))
        print("Message: " + json_msg['text'])
        draw_rotated_text(disp.buffer, "Text: " + json_msg['text'], (2, 220), 0, font, fill=(255, 255, 255))


def exit_foo():
    print("Goodbye!")
    draw_rotated_text(disp.buffer, "Goodbye!", (20,20), 90, font, fill=(255,255,255))
    exit(69)

switcher = {

    "1": update_status_foo,
    "2": send_message_foo,
    "3": receive_message_foo,
    "0": exit_foo
}


def main():
    disp.begin()
    disp.clear((0, 139, 139))
    print("Loading image")
    image = Image.open('twitter.jpg')
    image = image.rotate(0).resize((240, 170))
    disp.display(image)
    disp.display()
    while True:
        disp.clear((0, 139, 139))
        disp.display(image)
        print("Select: ");
        print("1: update status")
        print("2: send direct message")
        print("3: get last message")
        print("0: exit")
        print("Your selection: ", end="")
        selection = raw_input()
        switcher[selection]()
        disp.display()

main()

