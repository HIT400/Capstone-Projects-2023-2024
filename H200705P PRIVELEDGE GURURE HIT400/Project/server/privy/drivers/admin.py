from django.contrib import admin

# Register your models here.
from .models import Driver
from .models import Crime

admin.site.register(Driver)
admin.site.register(Crime)
