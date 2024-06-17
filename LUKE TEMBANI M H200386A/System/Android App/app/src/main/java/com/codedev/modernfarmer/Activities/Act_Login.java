package com.codedev.modernfarmer.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.codedev.modernfarmer.API.ApiService;
import com.codedev.modernfarmer.API.RetrofitClient;
import com.codedev.modernfarmer.Callbacks.TokenCallback;
import com.codedev.modernfarmer.Classes.PushNotificationService;
import com.codedev.modernfarmer.Classes.UserData;
import com.codedev.modernfarmer.Classes.UserLogin;
import com.codedev.modernfarmer.Constants;
import com.codedev.modernfarmer.R;
import com.codedev.modernfarmer.Utils.GetTokenFCM;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Act_Login extends AppCompatActivity {

    private MaterialButton login_btn, regiter_btn;
    private TextInputEditText username,password;

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences(Constants.SHARED_PREFS_NAME, Context.MODE_PRIVATE);
        setContentView(R.layout.activity_act_login);
        login_btn = findViewById(R.id.login_btn);
        regiter_btn = findViewById(R.id.register_btn_login);
        username = findViewById(R.id.login_username);
        password = findViewById(R.id.login_password);


        regiter_btn.setOnClickListener(v -> {
            Intent openRegister = new Intent(Act_Login.this, Act_Register.class);
            startActivity(openRegister);
            finish();

        });
        login_btn.setOnClickListener(v -> {
            if(username.getText().toString().isEmpty() || password.getText().toString().isEmpty()){
                Toast.makeText(Act_Login.this, "Fill in all fields!", Toast.LENGTH_SHORT).show();
            }else{
                UserLogin userLoginData = new UserLogin(username.getText().toString().trim(),password.getText().toString().trim());
                Login(userLoginData);
                clearFields(username,password);
            }
        });
    }

    private void Login(UserLogin userLogin){

        Retrofit retrofitClient = RetrofitClient.getClient();
        ApiService api = retrofitClient.create(ApiService.class);
        Call <Void> call = api.userLogin(userLogin);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.d("Login","Login User"+response);

                if(response.isSuccessful()){
                    GetTokenFCM getTokenFCM = new GetTokenFCM(Act_Login.this);
                    getTokenFCM.getToken(new TokenCallback() {
                        @Override
                        public void onSuccess(String token) {
                            try {
                                getTokenFCM.updateToken(token,userLogin.getUsername());
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onFailure(String error) {

                            try {
                                Log.d("FCM", error);
                                Toast.makeText(Act_Login.this, "Error Saving Token", Toast.LENGTH_SHORT).show();
                            }catch (Exception e){
                                e.printStackTrace();
                            }


                        }
                    });

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("LOGIN_STATUS",true).apply();
                    editor.putString("CUSTOMER_NAME",userLogin.getUsername()).apply();
                    Intent openHome = new Intent(Act_Login.this, Act_Home.class);
                    startActivity(openHome);
                    finish();
                }else{
                    Toast.makeText(Act_Login.this, "Login Failed, Please try again!", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }

    private void clearFields(EditText ed1, EditText ed2){
        ed1.setText("");
        ed2.setText("");
    }


}