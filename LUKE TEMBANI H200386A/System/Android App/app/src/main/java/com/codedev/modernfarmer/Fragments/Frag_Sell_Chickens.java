package com.codedev.modernfarmer.Fragments;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.codedev.modernfarmer.API.ApiService;
import com.codedev.modernfarmer.API.RetrofitClient;
import com.codedev.modernfarmer.Adapters.BuyingCompanies;
import com.codedev.modernfarmer.Callbacks.OnBuyersReceived;
import com.codedev.modernfarmer.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Frag_Sell_Chickens extends Fragment implements OnBuyersReceived {

    private RecyclerView buyersList;
    private BuyingCompanies buyingCompanies;
    private List<com.codedev.modernfarmer.Classes.BuyingCompanies> buyingCompaniesList;

    private String selectedCompany;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_frag__sell_chickens, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        buyersList = view.findViewById(R.id.buyers_list);
        buyersList.setLayoutManager(new LinearLayoutManager(getContext()));
        buyingCompaniesList = new ArrayList<>();
        getBuyers(this);

    }


    private void getBuyers(OnBuyersReceived onBuyersReceivedCallback){
        Retrofit retrofit  = RetrofitClient.getClient();
        ApiService api = retrofit.create(ApiService.class);
        Call<List<com.codedev.modernfarmer.Classes.BuyingCompanies>> call = api.getBuyers();
        call.enqueue(new Callback<List<com.codedev.modernfarmer.Classes.BuyingCompanies>>() {
            @Override
            public void onResponse(Call<List<com.codedev.modernfarmer.Classes.BuyingCompanies>> call, Response<List<com.codedev.modernfarmer.Classes.BuyingCompanies>> response) {
                if(response.isSuccessful()){
                    Log.d(TAG, "onResponse from API:"+response.body());
                    onBuyersReceivedCallback.onBuyersReceived(response.body());
                }else{
                    Log.d(TAG, "onResponse Error:"+response.body());
                }
            }

            @Override
            public void onFailure(Call<List<com.codedev.modernfarmer.Classes.BuyingCompanies>> call, Throwable t) {
                t.printStackTrace();
                try {
                    Toast.makeText(getContext(),"Failed to get companies",Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    public void onBuyersReceived(List<com.codedev.modernfarmer.Classes.BuyingCompanies> buyingCompaniesList) {
        buyingCompanies = new BuyingCompanies(buyingCompaniesList);
        buyersList.setAdapter(buyingCompanies);

    }
}
