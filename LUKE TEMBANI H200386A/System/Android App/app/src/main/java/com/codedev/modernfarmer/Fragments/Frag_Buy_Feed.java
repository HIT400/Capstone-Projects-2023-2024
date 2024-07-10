package com.codedev.modernfarmer.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.codedev.modernfarmer.API.ApiService;
import com.codedev.modernfarmer.API.RetrofitClient;
import com.codedev.modernfarmer.Adapters.FeedSupplierListAdp;
import com.codedev.modernfarmer.Callbacks.OnFeedSuppliersReceived;
import com.codedev.modernfarmer.Classes.FeedSuppliers;
import com.codedev.modernfarmer.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class Frag_Buy_Feed extends Fragment implements OnFeedSuppliersReceived {

    private RecyclerView feed_supplier_rcv;
    private FeedSupplierListAdp feedSupplierListAdp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_frag__buy__feed, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        feed_supplier_rcv = view.findViewById(R.id.supplier_list_rcv);
        feed_supplier_rcv.setLayoutManager(new LinearLayoutManager(getContext()));
        getSuppliers(this);
    }

    @Override
    public void onFeedSuppliersReceived(List<FeedSuppliers> feedSuppliersList) {
        feedSupplierListAdp = new FeedSupplierListAdp(feedSuppliersList,getContext(),getActivity());
        feed_supplier_rcv.setAdapter(feedSupplierListAdp);
    }

    private void getSuppliers(OnFeedSuppliersReceived callback) {
        Retrofit retrofit = RetrofitClient.getClient();
        ApiService api = retrofit.create(ApiService.class);
        Call<List<FeedSuppliers>> call = api.getSuppliers();

        call.enqueue(new Callback<List<FeedSuppliers>>() {
            @Override
            public void onResponse(Call<List<FeedSuppliers>> call, Response<List<FeedSuppliers>> response) {
                if(response.isSuccessful()){
                    callback.onFeedSuppliersReceived(response.body());
                }else{
                    Toast.makeText(getContext(), "Failed to get suppliers", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<FeedSuppliers>> call, Throwable t) {
                Toast.makeText(getContext(), "Failure"+t, Toast.LENGTH_SHORT).show();
            }
        });

    }
}