package com.codedev.modernfarmer.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.codedev.modernfarmer.API.ApiService;
import com.codedev.modernfarmer.API.RetrofitClient;
import com.codedev.modernfarmer.Callbacks.TokenCallback;
import com.codedev.modernfarmer.Classes.PushNotificationService;
import com.codedev.modernfarmer.Classes.UpdateToken;
import com.codedev.modernfarmer.Constants;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.messaging.FirebaseMessaging;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class GetTokenFCM {
    private SharedPreferences sharedPreferences;
    private Context context;

    public GetTokenFCM(Context context) {
        this.context = context;
    }

    public void getToken(final TokenCallback callback) {
        sharedPreferences = context.getSharedPreferences(Constants.SHARED_PREFS_NAME, Context.MODE_PRIVATE);
        FirebaseMessaging.getInstance().getToken()
                .addOnSuccessListener(new OnSuccessListener<String>() {
                    @Override
                    public void onSuccess(String s) {
                        if (callback != null) {
                            callback.onSuccess(s);
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context, "Error" + e, Toast.LENGTH_SHORT).show();
                        if (callback != null) {
                            callback.onFailure("Failed to get token");
                        }
                    }
                });
    }

    public void updateToken(String token, String username){
        Retrofit retrofit = RetrofitClient.getClient();
        UpdateToken new_token = new UpdateToken(token,username);
        ApiService api = retrofit.create(ApiService.class);
        Call<Void> call = api.updateFCMToken(new_token);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()){
                    Log.d("Token Update","Token Updated Successfully");
                }else{
                   Log.d("Error", "Something went wrong while updating token");
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

                Toast.makeText(context, "Error"+ t, Toast.LENGTH_SHORT).show();

            }
        });


    }
}
