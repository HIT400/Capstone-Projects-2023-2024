import cv2

# Define colors
red = (0, 0, 255)
font_scale = 1.0
font_thickness = 2

# Load the Haar cascade classifier for face detection
face_cascade = cv2.CascadeClassifier('models/haarcascade_frontalface_default.xml')

# Open the video capture (change 0 to your webcam device ID if needed)
cap = cv2.VideoCapture(0)

while True:
  # Capture frame-by-frame
  ret, frame = cap.read()

  # Convert frame to grayscale for faster processing
  gray = cv2.cvtColor(frame, cv2.COLOR_BGR2GRAY)

  # Detect faces
  faces = face_cascade.detectMultiScale(gray, scaleFactor=1.1, minNeighbors=5)

  # Draw bounding boxes and text for detected faces
  for (x, y, w, h) in faces:
    cv2.rectangle(frame, (x, y), (x+w, y+h), red, 2)
    cv2.putText(frame, "Unknown user", (x, y-10), cv2.FONT_HERSHEY_SIMPLEX, 
                font_scale, red, font_thickness)

  # Display the resulting frame
  cv2.imshow('Face Detection with Unknown User Text', frame)

  # Quit if 'q' key is pressed
  if cv2.waitKey(1) & 0xFF == ord('q'):
    break

# When everything is done, release the capture
cap.release()
cv2.destroyAllWindows()
