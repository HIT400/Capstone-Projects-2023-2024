package com.codedev.modernfarmer.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.codedev.modernfarmer.API.ApiService;
import com.codedev.modernfarmer.API.RetrofitClient;
import com.codedev.modernfarmer.Adapters.ComponentsAdp;
import com.codedev.modernfarmer.Callbacks.OnComponentsReceived;
import com.codedev.modernfarmer.Callbacks.OnComponentsReceivedDb;
import com.codedev.modernfarmer.Classes.ComponentsSwitcher;
import com.codedev.modernfarmer.Dao.ComponentsDAO;
import com.codedev.modernfarmer.Database.MFDBClient;
import com.codedev.modernfarmer.Database.ModernFarmerDB;
import com.codedev.modernfarmer.Entities.Components;
import com.codedev.modernfarmer.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Frag_Env_Control extends Fragment implements OnComponentsReceived, OnComponentsReceivedDb, ComponentsAdp.OnLightToggled {

    private RecyclerView components_rcv;
    private ComponentsAdp componentsAdpter;

    private ComponentsDAO componentsDAO;
    private TextView lights_on, fans_on;

    private SwipeRefreshLayout components_refresh;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_frag__env__control, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        componentsDAO = ((MFDBClient) getActivity().getApplication()).getDatabase().componentsDAO();
        components_rcv = view.findViewById(R.id.components_rcv);
        lights_on = view.findViewById(R.id.lights_turned_on);
        fans_on = view.findViewById(R.id.fans_turned_on);
        components_refresh = view.findViewById(R.id.components_refresh);
        components_refresh.setOnRefreshListener(() -> initRecyclerView(this));
        getRegisteredComponents(this);
        initRecyclerView(this);
    }

    @Override
    public void onComponentsReceived(List<ComponentsSwitcher> componentsSwitcherList) {

        Log.d("Components","Items received"+componentsSwitcherList);

        for(ComponentsSwitcher componentsSwitcher : componentsSwitcherList){
            CompletableFuture.runAsync(() -> {
                Components componentCheck = componentsDAO.getComponent(componentsSwitcher.getComponent_id());

                Components new_component = new Components();
                new_component.setComponent_id(componentsSwitcher.getComponent_id());
                new_component.setComponent_name(componentsSwitcher.getComponent_name());
                new_component.setComponent_status(componentsSwitcher.getComponent_status());

                if(componentCheck == null){
                    componentsDAO.insert(new_component);
                    Log.d("TAG","Saved Record");
                }else{
//                    componentsDAO.update(new_component);
//                    Log.d("TAG","Record Updated");
                }

            });


        }



    }

    private void getRegisteredComponents(OnComponentsReceived callback){
        Retrofit retrofit = RetrofitClient.getClient();
        ApiService api = retrofit.create(ApiService.class);
        Call<List<ComponentsSwitcher>> call = api.getComponents();

        call.enqueue(new Callback<List<ComponentsSwitcher>>() {
            @Override
            public void onResponse(Call<List<ComponentsSwitcher>> call, Response<List<ComponentsSwitcher>> response) {
                if(response.isSuccessful()){
                    callback.onComponentsReceived(response.body());
                }else{
                    Toast.makeText(getContext(), "Failed to get components", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<ComponentsSwitcher>> call, Throwable t) {
                try {
                    Toast.makeText(getActivity().getApplicationContext(), "Failed to get components"+ t, Toast.LENGTH_SHORT).show();
                    t.printStackTrace();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

    }

    private void initRecyclerView(OnComponentsReceivedDb callback){
        CompletableFuture.runAsync(() -> {
            callback.onComponentsReceivedDb(componentsDAO.getComponents());
            Log.d("Components",componentsDAO.getComponents().toString());
            List<Components> lightsOn = componentsDAO.getLightsOn();
            List<Components> fansOn = componentsDAO.getFansOn();
            lights_on.setText("Lights Turned On: "+lightsOn.size());
            fans_on.setText("Fans Turned On: "+fansOn.size());
            if(components_refresh.isRefreshing())components_refresh.setRefreshing(false);
        });

    }



    @Override
    public void onComponentsReceivedDb(List<Components> componentsList) {
        componentsAdpter = new ComponentsAdp(componentsList,getContext(),getActivity());
        componentsAdpter.setOnLightToggled(this);
        components_rcv.setAdapter(componentsAdpter);
    }

    @Override
    public void toggled(ComponentsSwitcher componentsSwitcher) {
        Log.d("Updated Status", "Switched");
        String status  = componentsSwitcher.getComponent_status().equals("0")?"1":"0";
        Integer component_id = componentsSwitcher.getComponent_id();
        CompletableFuture.runAsync(() -> componentsDAO.updateStatus(status, component_id));
        ComponentsSwitcher component = new ComponentsSwitcher();
        component.setComponent_id(component_id);
        component.setComponent_status(status);
        toggleServerLightStatus(component);
    }

    private void toggleServerLightStatus(ComponentsSwitcher componentsSwitcher){
        Retrofit retrofit = RetrofitClient.getClient();
        ApiService apiService = retrofit.create(ApiService.class);
        Call<Void> call = apiService.toggleLightStatus(componentsSwitcher);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getContext(), "Status Updated", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getContext(), "Status Update Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

                Toast.makeText(getContext(), "Error"+t, Toast.LENGTH_SHORT).show();

            }
        });
    }
}