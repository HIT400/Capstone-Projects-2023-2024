package com.codedev.modernfarmer.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.codedev.modernfarmer.API.ApiService;
import com.codedev.modernfarmer.API.RetrofitClient;
import com.codedev.modernfarmer.Classes.UserData;
import com.codedev.modernfarmer.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Act_Register extends AppCompatActivity {


    private MaterialButton login, register;
    private TextInputEditText fullname, username, password, contact, address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_register);

        login = findViewById(R.id.btn_login_reg);
        register = findViewById(R.id.btn_register);
        fullname = findViewById(R.id.fullname);
        username = findViewById(R.id.reg_username);
        password = findViewById(R.id.reg_password);
        contact = findViewById(R.id.reg_contact);
        address = findViewById(R.id.reg_address);


        login.setOnClickListener(v -> {
            Intent openLogin = new Intent(Act_Register.this, Act_Login.class);
            startActivity(openLogin);
            finish();
        });


        register.setOnClickListener(v -> {
            if(fullname.getText().toString().isEmpty() ||
            username.getText().toString().isEmpty()||
            password.getText().toString().isEmpty() ||
            address.getText().toString().isEmpty() ||
            contact.getText().toString().isEmpty()){
                Toast.makeText(Act_Register.this, "Fill in all fields!", Toast.LENGTH_SHORT).show();
            }else{
                UserData userData = new UserData(fullname.getText().toString().trim(), username.getText().toString().trim(), password.getText().toString().trim(), address.getText().toString().trim(),contact.getText().toString().trim());
                registerUser(userData);
                clearFields(fullname,username,password,address,contact);
            }
        });


    }

    private void registerUser(UserData userData){

        Retrofit retrofit = RetrofitClient.getClient();
        ApiService api = retrofit.create(ApiService.class);
        Call<Void> call = api.registerUser(userData);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()){
                    Toast.makeText(Act_Register.this, "Registration Successful, Login now", Toast.LENGTH_SHORT).show();
                    Intent openLg = new Intent(Act_Register.this,Act_Login.class);
                    startActivity(openLg);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                t.printStackTrace();

            }
        });

    }

    private void clearFields(EditText ed1, EditText ed2, EditText ed3, EditText ed4, EditText ed5){
        ed1.setText("");
        ed2.setText("");
        ed3.setText("");
        ed4.setText("");
        ed5.setText("");

    }
}