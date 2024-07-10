package com.codedev.modernfarmer.Database;

import android.app.Application;

import androidx.room.Room;

public class MFDBClient extends Application {

    public static ModernFarmerDB modernFarmerDB;

    @Override
    public void onCreate() {
        super.onCreate();
        modernFarmerDB = Room.databaseBuilder(getApplicationContext(),ModernFarmerDB.class,"Modern_Farmer")
                .fallbackToDestructiveMigration()
                .build();
    }


    public ModernFarmerDB getDatabase(){
        return modernFarmerDB;
    }

}
