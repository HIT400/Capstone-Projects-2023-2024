from django.urls import path
from . import views
from .views import DriverByPictureNameAPIView
from .views import DriverByDriverIdAPIView
from .views import PictureUploadAPIView

urlpatterns = [
    path('add/', views.add_driver, name='add_driver'),
    path('added/', views.driver_added, name='driver_added'),
    path('recognize/', views.recognize_driver, name='recognize_driver'),
    path('test/', views.get_driver_by_picture_name, name='test_driver'),
    path('api/', DriverByPictureNameAPIView.as_view(), name='driver_by_picture_name'),
    path('id/', views.driver_by_driver_id, name='driver_by_driver_id'),
    #path('drivers/<int:driver_id>/', DriverByDriverIdAPIView.as_view(), name='driver_by_driver_id'),
    path('upload-picture/', PictureUploadAPIView.as_view(), name='upload_picture'),

    
]