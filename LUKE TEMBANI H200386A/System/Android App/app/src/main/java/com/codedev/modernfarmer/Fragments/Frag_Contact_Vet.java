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
import com.codedev.modernfarmer.Adapters.VetListAdp;
import com.codedev.modernfarmer.Callbacks.OnVetsReceived;
import com.codedev.modernfarmer.Classes.Veterinarians;
import com.codedev.modernfarmer.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class Frag_Contact_Vet extends Fragment implements OnVetsReceived {

    private RecyclerView vet_list;
    private VetListAdp vet_adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_frag__contact__vet, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        vet_list = view.findViewById(R.id.vet_list);
        vet_list.setLayoutManager(new LinearLayoutManager(getContext()));
        getVets(this);
    }

    @Override
    public void onVetsDataReceived(List<Veterinarians> veterinariansList) {
        vet_adapter = new VetListAdp(veterinariansList,getContext(),getActivity());
        vet_list.setAdapter(vet_adapter);

    }

    private void getVets(OnVetsReceived callback){
        Retrofit retrofit = RetrofitClient.getClient();
        ApiService api = retrofit.create(ApiService.class);
        Call<List<Veterinarians>> call = api.getVeterinarians();

        call.enqueue(new Callback<List<Veterinarians>>() {
            @Override
            public void onResponse(Call<List<Veterinarians>> call, Response<List<Veterinarians>> response) {
                if(response.isSuccessful()){
                    callback.onVetsDataReceived(response.body());
                }else{
                    Toast.makeText(getContext(), "Failed to get veterinarians", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Veterinarians>> call, Throwable t) {
                Toast.makeText(getContext(), "Failed to get veterinarians"+t, Toast.LENGTH_SHORT).show();

            }
        });

    }
}