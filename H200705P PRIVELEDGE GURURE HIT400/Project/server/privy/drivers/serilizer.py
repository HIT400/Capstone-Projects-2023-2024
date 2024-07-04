from rest_framework import serializers

class ImageUploadSerializer(serializers.Serializer):
    picture = serializers.ImageField()
