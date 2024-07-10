package com.codedev.modernfarmer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.codedev.modernfarmer.Activities.Act_Home;
import com.codedev.modernfarmer.Activities.Act_Login;
import com.codedev.modernfarmer.Classes.PushNotificationService;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences sharedPreferences = getSharedPreferences(Constants.SHARED_PREFS_NAME, Context.MODE_PRIVATE);
        super.onCreate(savedInstanceState);
        Boolean login_status = sharedPreferences.getBoolean("LOGIN_STATUS",false);
        imageView = findViewById(R.id.splash_img);

        setContentView(R.layout.activity_main);
        Runnable openLogin = () -> {
            if(login_status){
                Intent openHome = new Intent(MainActivity.this, Act_Home.class);
                startActivity(openHome);
                finish();
            }else{
                Intent openLogin1 = new Intent(MainActivity.this, Act_Login.class);
                startActivity(openLogin1);
                finish();
            }

        };
        Handler handler = new Handler();
        handler.postDelayed(openLogin,1000);
    }


}