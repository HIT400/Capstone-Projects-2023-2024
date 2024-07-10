# import Adafruit_DHT
import time
import requests

# DHT_SENSOR = Adafruit_DHT.DHT11
# DHT_PIN = 4
API_URL = "http://localhost:8822/rasp_weather_hook"

while True:
    humidity, temperature = Adafruit_DHT.read(DHT_SENSOR, DHT_PIN)
    if humidity is not None and temperature is not None:
        # Create a dictionary to represent the data you want to send
        data = {
            "username": "Luke Tembani M",
            "capture_time": "16:50",
            "temperature": "{0:0.1f}".format(temperature),
            "humidity": "{0:0.1f}".format(humidity)
        }
        
        # Send a POST request to the API with the data
        response = requests.post(API_URL, json=data)
        
        if response.status_code == 200:
            print("Data sent successfully.")
        else:
            print("Failed to send data. Status code:", response.status_code)
    
    # Delay for 10 minutes (600 seconds) before the next request
        time.sleep(5)
