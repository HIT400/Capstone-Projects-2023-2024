# Generated by Django 3.2.23 on 2024-02-06 16:21

from django.db import migrations


class Migration(migrations.Migration):

    dependencies = [
        ('main', '0002_prediction'),
    ]

    operations = [
        migrations.RenameField(
            model_name='prediction',
            old_name='inpiut',
            new_name='input',
        ),
    ]
