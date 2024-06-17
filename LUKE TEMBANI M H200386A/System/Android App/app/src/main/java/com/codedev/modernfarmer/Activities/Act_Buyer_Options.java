package com.codedev.modernfarmer.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.codedev.modernfarmer.API.ApiService;
import com.codedev.modernfarmer.API.RetrofitClient;
import com.codedev.modernfarmer.Classes.SellChicks;
import com.codedev.modernfarmer.Constants;
import com.codedev.modernfarmer.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Act_Buyer_Options extends AppCompatActivity {

    private TextInputEditText number_of_chicks, location, weight_of_chicks;
    private MaterialButton sell_chicks_btn;

    private SharedPreferences sharedPreferences;

    private String selectedCompany;

    private String chicks_price;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_buyer_options);
        sharedPreferences = getSharedPreferences(Constants.SHARED_PREFS_NAME, Context.MODE_PRIVATE);
        number_of_chicks = findViewById(R.id.number_of_birds);
        location = findViewById(R.id.location);
        weight_of_chicks = findViewById(R.id.weight_of_birds);
        sell_chicks_btn = findViewById(R.id.sell_chicks_btn);
        selectedCompany = getIntent().getExtras().getString("Selected_Company");
        chicks_price = getIntent().getExtras().getString("Chicks_Price","5");


        sell_chicks_btn.setOnClickListener(v -> {
            if(number_of_chicks.getText().toString().isEmpty() || location.getText().toString().isEmpty() || weight_of_chicks.getText().toString().isEmpty()){
                Toast.makeText(Act_Buyer_Options.this, "Fill in all details", Toast.LENGTH_SHORT).show();
                number_of_chicks.requestFocus();
            }else{
                SellChicks sellChicksOrder = new SellChicks();
                Date current = new Date();
                String username = sharedPreferences.getString("CUSTOMER_NAME","default");

                try {
                    sellChicksOrder.setCustomer_username(username);
                    sellChicksOrder.setNumber_of_birds(Integer.parseInt(number_of_chicks.getText().toString()));
                    sellChicksOrder.setWeight_of_birds(Float.parseFloat(weight_of_chicks.getText().toString()));
                    sellChicksOrder.setLocation(location.getText().toString());
                    sellChicksOrder.setCompany(selectedCompany);
                    sellChicksOrder.setPrice(chicks_price);
                    sellChicksOrder.setDate(current);
                    sellChicks(sellChicksOrder);
                }catch (Exception e){
                    Toast.makeText(this, "Error"+ e, Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private void sellChicks(SellChicks sellChicks){
        Retrofit retrofit = RetrofitClient.getClient();
        ApiService api = retrofit.create(ApiService.class);
        Call<Void> call = api.postChicks(sellChicks);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

                if(response.isSuccessful()){
                    Toast.makeText(Act_Buyer_Options.this, "Chicks Order Placed Successfully", Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    Toast.makeText(Act_Buyer_Options.this, "Failed to Sell Chicks", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(Act_Buyer_Options.this, "Failed to Sell Chicks"+t, Toast.LENGTH_SHORT).show();

            }
        });
    }

}