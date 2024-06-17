from flask import Flask, request
import RPi.GPIO as GPIO
import time

GPIO.setmode(GPIO.BCM)

# Relay 1
GPIO.setup(21, GPIO.OUT)

# Relay 2
GPIO.setup(26, GPIO.OUT)

app = Flask(__name__)

@app.route('/trigger', methods = ['POST'])
def trigger():
    request_data = request.json
    status = request_data['STATUS']
    GPIO_PIN = request_data['GPIO_PIN']
    print(request_data)


    try:
        GPIO.output(GPIO_PIN,GPIO.LOW)


    finally:
        GPIO.cleanup()

    return "Done"
       

if __name__ == '__main__':
    app.run(host='192.168.23.126', port=5030)
