from django.db import models

# Create your models here.
# drivers/models.py

class Driver(models.Model):
    picture = models.ImageField(upload_to='driver_images/')
    age = models.IntegerField()
    license_number = models.CharField(max_length=20)
    name = models.CharField(max_length=255)
    national_id = models.CharField(max_length=20)
    issued_year = models.DateTimeField(auto_now_add=True)
    driver_class = models.CharField(max_length=50)
    driver_id = models.IntegerField()

    

    def __str__(self):
        return self.name

class Crime(models.Model):
    driver = models.ForeignKey(Driver, on_delete=models.CASCADE)
    crime_description = models.TextField()


    def __str__(self):
        return f"{self.driver.name} - {self.crime_description}"
