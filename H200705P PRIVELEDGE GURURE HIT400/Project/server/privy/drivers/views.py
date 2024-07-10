from django.shortcuts import render
import os
from django.shortcuts import render
from .models import Driver,Crime
from deepface import DeepFace

# Create your views here.
# drivers/views.py
from django.shortcuts import render, redirect
from .models import Driver
from .forms import DriverForm

def add_driver(request):
    if request.method == 'POST':
        form = DriverForm(request.POST, request.FILES)
        if form.is_valid():
            form.save()
            return redirect('driver_added')
    else:
        form = DriverForm()
    return render(request, 'drivers/add_driver.html', {'form': form})

def driver_added(request):
    return render(request, 'drivers/driver_added.html')

# Add view for police to retrieve driver information based on picture (facial recognition logic here)
# drivers/views.py
from django.shortcuts import render, redirect
from .models import Driver
from .forms import DriverForm
from deepface import DeepFace

def add_driver(request):
    if request.method == 'POST':
        form = DriverForm(request.POST, request.FILES)
        if form.is_valid():
            form.save()
            return redirect('driver_added')
    else:
        form = DriverForm()
    return render(request, 'add_driver.html', {'form': form})

def driver_added(request):
    return render(request, 'driver_added.html')


import os
from django.shortcuts import render
from .models import Driver
from deepface import DeepFace

def recognize_driver(request):
    if request.method == 'POST':
        try:
            uploaded_image = request.FILES['uploaded_image']
            known_faces_dir = '/Users/User/Desktop/Project/server/privy/media/driver_images'
            
            print("WSA"+uploaded_image)
            # Specify the path to the folder you want to loop through
            folder_path = '/Users/User/Desktop/Project/server/privy/media/driver_images'
            
            # List of valid image file extensions
            image_extensions = ['.jpg', '.jpeg', '.png', '.gif', '.bmp', '.tiff']

            isTrue =False
            # Loop through the files in the folder and subdirectories
            for root, dirs, files in os.walk(folder_path):
                for filename in files:
                    # Check if the file has a valid image extension
                    if any(filename.lower().endswith(ext) for ext in image_extensions):
                        # Display the image using IPython's display module
                        image_path = os.path.join(root, filename)

                        print(image_path)
                        result = DeepFace.verify(img1_path = "/Users/User/Desktop/Project/server/privy/media/driver_images/20221230_143814.jpg", img2_path = "/Users/User/Desktop/Project/server/privy/media/driver_images/20221230_143814.jpg")
                        
                        if result["verified"]== True:
                            print(result["verified"])
                            desired_part = image_path.rsplit('/', 1)[-1]  # Splitting at the last '/' and taking the last part


                            print(desired_part) 


                        print(result["verified"])


            # List all files in the known_faces directory
            # known_face_files = [os.path.join(known_faces_dir, f) for f in os.listdir(known_faces_dir) if os.path.isfile(os.path.join(known_faces_dir, f))]


            # Initialize variables for best match
            best_match_score = 0
            best_matched_driver = None
            print(uploaded_image)

            # Perform face search with each known face image
            # for known_face_file in known_face_files:
            # similarity_score = result["similarity"]
           

            # if similarity_score > best_match_score:
            #         best_match_score = similarity_score
            #         driver_name = os.path.basename(known_face_file).split('.')[0]  # Extract driver name from file name
            #         driver = Driver.objects.get(name=driver_name)
            #         best_matched_driver = driver

            if best_matched_driver:
                return render(request, 'driver_info.html', {'driver': best_matched_driver, 'similarity_score': best_match_score})
            else:
                return render(request, 'driver_info.html', {'message': 'Driver not recognized.'})
        except Exception as e:
            # Log the exception for debugging
            print(str(e))
            return render(request, 'driver_info.html', {'message': 'Error processing image. Please try again.'})
    else:
        return render(request, 'recognize_driver.html')













from django.shortcuts import get_object_or_404
from .models import Driver  # Import your Driver model

def get_driver_by_picture_name(request):
    picture_name ="Photo_on_21-4-2024_at_15.10_3.jpg"
    try:
        driver = Driver.objects.get(picture__icontains=picture_name)
        print(f"Driver found: {driver.name}")
    except Driver.DoesNotExist:
        print("Driver not found.")



from rest_framework.views import APIView
from rest_framework.response import Response
from django.shortcuts import get_object_or_404

class DriverByPictureNameAPIView(APIView):
    def get(self, request):
        picture_name = "Photo_on_21-4-2024_at_15.10_3.jpg"
        try:
            driver = Driver.objects.get(picture__icontains=picture_name)
            data = {
                'found': True,
                'driver_name': driver.name,
                'driver_id': driver.driver_id,
                # Add other fields you want to include in the response
            }
        except Driver.DoesNotExist:
            data = {'found': False}

        return Response(data)
    


from rest_framework.decorators import api_view
from rest_framework.response import Response
from rest_framework import status
from django.core.exceptions import ObjectDoesNotExist
from .models import Driver  # Import your Driver model
import json
import re

@api_view(['POST'])
def driver_by_driver_id(request):
    if request.method == 'POST':
        try:
            # Retrieve the driver ID from the JSON request data
            driver_id = request.data.get('driver_id')
           
            start_index = driver_id.index('#') + 1

            end_index = driver_id.index('w')
            extracted_number = driver_id[start_index:end_index]
            print("---------------------"+extracted_number)  # 
            #'Found ID #1 with confidence of 100\r\n'.

            # Retrieve the driver information based on the provided ID
            driver = Driver.objects.get(driver_id=extracted_number)
            data = {
                'driver_name': driver.name,
                'age': driver.age,
                'license_number': driver.license_number,
                'national_id': driver.national_id,
                'issued_year': driver.issued_year,
                'driver_class': driver.driver_class,
                # Add other fields as needed
            }
            return Response(data)
        except ObjectDoesNotExist:
            error_message = {'error': f'Driver with id {driver_id} does not exist.'}
            return Response(error_message, status=status.HTTP_404_NOT_FOUND)


from rest_framework.views import APIView
from rest_framework.response import Response # Import your Driver model
from django.shortcuts import get_object_or_404
from rest_framework import status

class DriverByDriverIdAPIView(APIView):
    def post(self, request):
        try:
            # Retrieve the driver ID from the JSON request data
            request_data = json.loads(request.body)
            driver_id = request_data.get('driver_id')

            # Retrieve the driver information based on the provided ID
            driver = Driver.objects.get(driver_id=driver_id)
            data = {
                'driver_name': driver.name,
                'age': driver.age,
                'license_number': driver.license_number,
                'national_id': driver.national_id,
                'issued_year': driver.issued_year,
                'driver_class': driver.driver_class,
                # Add other fields as needed
            }
            return Response(data)
        except ObjectDoesNotExist:
            error_message = {'error': f'Driver with id {driver_id} does not exist.'}
            return Response(error_message, status=status.HTTP_404_NOT_FOUND)

from rest_framework.views import APIView
from rest_framework.response import Response
from rest_framework import status
from .serilizer import ImageUploadSerializer
from django.core.files.storage import FileSystemStorage

image_extensions = ['.jpg', '.jpeg', '.png', '.gif', '.bmp', '.tiff']
folder_path = '/Users/User/Desktop/Project/server/privy/media/driver_images'


# from rest_framework.views import APIView
# from rest_framework.response import Response
# from rest_framework import status
# from django.core.files.storage import FileSystemStorage



# class PictureUploadAPIView(APIView):
#     def post(self, request):
#         serializer = ImageUploadSerializer(data=request.data)
#         if serializer.is_valid():
#             picture = serializer.validated_data['picture']
#             print("---------------------------------------")
#             print("---------------------------------------")
#             print("---------------------------------------")
#             result1 = DeepFace.verify(img1_path="/Users/User/Desktop/Project/server/privy/media/driver_images/20221230_143814.jpg", img2_path="/Users/User/Desktop/WhatsApp Image 2024-05-01 at 21.09.16_c7f6c3ea.jpg")
#             print("Upload Path Path:",result1["verified"] )
#             print("---------------------------------------")
#             print("---------------------------------------")
#             print("---------------------------------------")
#             print("---------------------------------------")


#             # Save the uploaded picture to a specific directory
#             fs = FileSystemStorage(location='media/')  # Set your desired location
#             saved_picture = fs.save(picture.name, picture)
#             picture_paths = fs.url(saved_picture)
#             print("---------------------------------------")
#             print("----------- File Uploaded Successfully ---------------")
#             print("Upload Path:", picture_paths)
#             print("---------------------------------------")
            

#             # Loop through the files in the folder and subdirectories
#             for root, dirs, files in os.walk(folder_path):
#                 for filename in files:
#                     # Check if the file has a valid image extension
#                     if any(filename.lower().endswith(ext) for ext in image_extensions):
#                         # Display the image using IPython's display module
#                         image_path = os.path.join(root, filename)
#                         print("---------------------------------------")
#                         print("----------- directory ---------------")
#                         print(image_path)
#                         print("---------------------------------------")
#                         print("----------- upload path ---------------")
#                         newpatch = "/Users/User/Desktop/Project/server/privy/media" + picture_paths
#                         print(newpatch)
#                         print("----------- ------- ---------------")
#                         result = DeepFace.verify(img1_path=newpatch, img2_path=image_path)
#                         if result["verified"] == True:
#                             print(result["verified"])
#                             desired_part = image_path.rsplit('/', 1)[-1]  # Splitting at the last '/' and taking the last part
#                             print(desired_part)
#                             picture_name = desired_part
#                             try:
#                                 driver = Driver.objects.get(picture__icontains=picture_name)
#                                 crimes = Crime.objects.filter(driver=driver)

#                                 crime_list = [{'crime_description': crime.crime_description} for crime in crimes]
#                                 data = {
#                                     'driver_name': driver.name,
#                                     'age': driver.age,
#                                     'license_number': driver.license_number,
#                                     'national_id': driver.national_id,
#                                     'issued_year': driver.issued_year,
#                                     'driver_class': driver.driver_class,
#                                     'crimes': crime_list,
#                                     'total_crimes': len(crimes),    
#                                     # Add other fields as needed
#                                 }
#                             except Driver.DoesNotExist:
#                                 data = {'found': False}
                                
#                             return Response(data)
#                         print(result["verified"])
            
#             return Response({'error': 'Driver information not found.'}, status=status.HTTP_404_NOT_FOUND)
#         else:
#             return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)
# import os

# class PictureUploadAPIView(APIView):
#     def post(self, request):
#         serializer = ImageUploadSerializer(data=request.data)
#         if serializer.is_valid():
#             picture = serializer.validated_data['picture']

#             # Save the uploaded picture to a specific directory
#             fs = FileSystemStorage(location='media\\')  # Set your desired location
#             saved_picture = fs.save(picture.name, picture)
#             picture_paths = fs.url(saved_picture)
        
#             print("---------------------------------------")
#             print("---------------------------------------")
#             print("picture")
#             result1 = DeepFace.verify(img1_path="/Users/User/Desktop/WhatsApp Image 2024-05-01 at 21.09.16_c7f6c3ea.jpg", img2_path="/Users/User/Desktop/W.jpg")
#             print(result1["verified"])
#             print("---------------------------------------")
#             print("---------------------------------------")
#             # print("Upload Path:", picture_paths)


#             # Loop through the files in the folder and subdirectories
#             for root, dirs, files in os.walk(folder_path):
#                 for filename in files:
#                     # Check if the file has a valid image extension
#                     if any(filename.lower().endswith(ext) for ext in image_extensions):
#                         # Display the image using IPython's display module
#                         image_path = os.path.join(root, filename)
#                         # print("---------------------------------------")
#                         # print("----------- directory ---------------")
#                         # print(image_path)
#                         # print("---------------------------------------")
#                         # print("----------- upload path ---------------")
#                         # newpath = "C:\\Users\\User\\Desktop\\Project\\server\\privy\\vehicle_license_system" + picture_paths.replace("/", "\\")
#                         # print(newpath)
#                         # print("----------- ------- ---------------")
#                         result = DeepFace.verify(img1_path=newpath, img2_path=image_path)
#                         if result["verified"] == True:
#                             print(result["verified"])
#                             desired_part = image_path.rsplit('\\', 1)[-1]  # Splitting at the last '\\' and taking the last part
#                             print(desired_part)
#                             picture_name = desired_part
#                             try:
#                                 driver = Driver.objects.get(picture__icontains=picture_name)
#                                 crimes = Crime.objects.filter(driver=driver)

#                                 crime_list = [{'crime_description': crime.crime_description} for crime in crimes]
#                                 data = {
#                                     'driver_name': driver.name,
#                                     'age': driver.age,
#                                     'license_number': driver.license_number,
#                                     'national_id': driver.national_id,
#                                     'issued_year': driver.issued_year,
#                                     'driver_class': driver.driver_class,
#                                     'crimes': crime_list,
#                                     'total_crimes': len(crimes),    
#                                     # Add other fields as needed
#                                 }
#                             except Driver.DoesNotExist:
#                                 data = {'found': False}
                                
#                             return Response(data)
#                         print(result["verified"])

#             return Response({'error': 'Driver information not found.'}, status=status.HTTP_404_NOT_FOUND)
#         else:
#             return Response(serializer.errors,status.HTTP_400_BAD_REQUEST)





import os
from rest_framework.views import APIView
from rest_framework.response import Response
from rest_framework import status
from django.core.files.storage import FileSystemStorage


class PictureUploadAPIView(APIView):
    def post(self, request):
        serializer = ImageUploadSerializer(data=request.data)
        if serializer.is_valid():
            picture = serializer.validated_data['picture']

            # result = DeepFace.verify(img1_path="/Users/User/Desktop/Project/server/privy/media/WhatsApp%20Image%202024-05-01%20at%2021.09.16_c7f6c3ea.jpg", img2_path=image_path)
           
            fs = FileSystemStorage(location='media/')  # Set your desired location
            saved_picture = fs.save(picture.name, picture)
            picture_paths = fs.url(saved_picture)
            print("---------------------------------------")
            print("----------- File Uploaded Successfully ---------------")
            print("Upload Path:", picture_paths)
            print("---------------------------------------")

            try:
                print("---------------------------------------")
                print("---------------------test------------------")
                newpatch = "/Users/User/Desktop/Project/server/privy" + picture_paths
                result = DeepFace.verify(img1_path=newpatch ,img2_path="/Users/User/Desktop/Project/server/privy/media/driver_images/W.jpg")

            except Exception:
                 return Response({'error': 'image not clear found.'})
                        

                
            
            # Loop through the files in the folder and subdirectories
            for root, dirs, files in os.walk(folder_path):
                for filename in files:
                    # Check if the file has a valid image extension
                    if any(filename.lower().endswith(ext) for ext in image_extensions):
                        # Display the image using IPython's display module
                        image_path = os.path.join(root, filename)
                        print("---------------------------------------")
                        print("----------- directory ---------------")
                        print(image_path)
                        print("---------------------------------------")
                        print("----------- upload path ---------------")
                        newpatch = "/Users/User/Desktop/Project/server/privy" + picture_paths
                        print(newpatch)
                        print("----------- ------- ---------------")

                        result = DeepFace.verify(img1_path=newpatch, img2_path=image_path)
                        print("----------- upload path ---------------")
                        print("----------- upload path ---------------")
                        print("----------- upload path ---------------")
                        print("----------- upload path ---------------")
                        print(result["verified"])
                        print("----------- upload path ---------------")
                        print("----------- upload path ---------------")
                        print("----------- upload path ---------------")
                        print("----------- upload path ---------------")
                        if result["verified"] == True:
                            print(result["verified"])
                            print("----------- upload path ---------------")
                            print("----------- upload path ---------------")
                            print("----------- upload path ---------------")
                            print(image_path)
                            
                            last_string = image_path.split("\\")[-1]
                            print(last_string)
                         
                            

                            desired_part = image_path.rsplit('/', 1)[-1] 
                            print("----------- upload path ---------------")
                            print("----------- upload path ---------------")
                            print("----------- upload path ---------------")
                            print(desired_part) 
                            print(desired_part)
                            print("----------- upload path ---------------")
                            print("----------- upload path ---------------")
                            print("----------- upload path ---------------")
                            picture_name = last_string
                            try:
                                driver = Driver.objects.get(picture__icontains=picture_name)
                                crimes = Crime.objects.filter(driver=driver)

                                crime_list = [{'crime_description': crime.crime_description} for crime in crimes]
                                data = {
                                    'driver_name': driver.name,
                                    'age': driver.age,
                                    'license_number': driver.license_number,
                                    'national_id': driver.national_id,
                                    'issued_year': driver.issued_year,
                                    'driver_class': driver.driver_class,
                                    'crimes': crime_list,
                                    'total_crimes': len(crimes),    
                                    # Add other fields as needed
                                }
                            except Driver.DoesNotExist:
                                data = {'found': False}
                                
                            return Response(data)
                        print(result["verified"])
            
            return Response({'error': 'Driver information not found.'})
        else:
            return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)