# Generated by Django 5.0.4 on 2024-05-01 12:10

import django.db.models.deletion
from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('drivers', '0002_auto_20240501_1207'),
    ]

    operations = [
        migrations.AddField(
            model_name='driver',
            name='driver_id',
            field=models.IntegerField(default=1),
            preserve_default=False,
        ),
        migrations.AlterField(
            model_name='driver',
            name='issued_year',
            field=models.DateTimeField(auto_now_add=True),
        ),
        migrations.CreateModel(
            name='Crime',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('crime_description', models.TextField()),
                ('driver', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='drivers.driver')),
            ],
        ),
    ]
