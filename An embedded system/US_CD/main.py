from twython import Twython

# authentication and linking account
APP_KEY = "MDLWg51hXxnNENttjUn6FN5iy"
APP_SECRET = "3jGJJ1m1vhoHsK3Uz57raOUdyQLvye6yfyMoml3M93S6Raiqrk"
OAUTH_TOKEN = "732289723327217665-ALplYL9qc9OOB5R7sWbmJixkicwtyNg"
OAUTH_TOKEN_SECRET = "uLWGcOR69qNqqBCAKz5hmXMLOA021Gw4Xt6hdLl2a2ZH3"
twitter = Twython(APP_KEY, APP_SECRET, OAUTH_TOKEN, OAUTH_TOKEN_SECRET)


def update_status_foo():
    print("Status: ", end="")
    status = input()
    twitter.update_status(status=status)
    print("Status updated!")


def send_message_foo():
    print("Recipient's username: ", end="")
    recipient = input()
    print("Message: ", end="")
    message = input()
    twitter.send_direct_message(screen_name=recipient, text=message)
    print("Message sent!")


def receive_message_foo():
    json_msgs = twitter.get_direct_messages(count=1)
    for json_msg in json_msgs:
        print("Sender: " + json_msg['sender_screen_name'])
        print("Text: " + json_msg['text'])


def exit_foo():
    print("Goodbye!")
    exit(69)

switcher = {

    "1": update_status_foo,
    "2": send_message_foo,
    "3": receive_message_foo,
    "0": exit_foo
}


def main():

    while True:

        print("Select: ");
        print("1: update status")
        print("2: send direct message")
        print("3: get last message")
        print("0: exit")
        print("Your selection: ", end="")
        selection = input()
        switcher[selection]()

main()

