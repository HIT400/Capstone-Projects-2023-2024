package com.codedev.modernfarmer.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.codedev.modernfarmer.API.ApiService;
import com.codedev.modernfarmer.API.RetrofitClient;
import com.codedev.modernfarmer.Adapters.FeedStockAdp;
import com.codedev.modernfarmer.Callbacks.OnFeedStockRecevied;
import com.codedev.modernfarmer.Classes.FeedStockParams;
import com.codedev.modernfarmer.Entities.FeedStock;
import com.codedev.modernfarmer.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Act_Supplier_Products extends AppCompatActivity implements OnFeedStockRecevied{

    private RecyclerView supplier_products_rcv;
    private FeedStockAdp feedStockAdp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_supplier_products2);
        supplier_products_rcv = findViewById(R.id.supplier_products_rcv);
        supplier_products_rcv.setLayoutManager(new LinearLayoutManager(this));
        Intent company_data = getIntent();
        String company_name = company_data.getStringExtra("Company_name");
        FeedStockParams feedStockParams = new FeedStockParams(company_name,"feed");
        getFeedStock(this,feedStockParams);
    }

    private void getFeedStock(OnFeedStockRecevied callback, FeedStockParams feedStockParams){
        Log.d("Tag","Method Hit");
        Retrofit retrofit = RetrofitClient.getClient();
        ApiService api = retrofit.create(ApiService.class);
        Call<List<FeedStock>> call = api.getFeedStock(feedStockParams);

        call.enqueue(new Callback<List<FeedStock>>() {
            @Override
            public void onResponse(Call<List<FeedStock>> call, Response<List<FeedStock>> response) {
                if(response.isSuccessful()){
                    callback.onFeedStockReceived(response.body());
                }else{
                    Toast.makeText(Act_Supplier_Products.this, "Failed to get Feed Stock!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<FeedStock>> call, Throwable t) {
                Toast.makeText(Act_Supplier_Products.this, "Failed"+t, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onFeedStockReceived(List<FeedStock> feedStockList) {
        Log.i("Feed",feedStockList.toString());
        feedStockAdp = new FeedStockAdp(feedStockList,Act_Supplier_Products.this);
        supplier_products_rcv.setAdapter(feedStockAdp);
    }
}