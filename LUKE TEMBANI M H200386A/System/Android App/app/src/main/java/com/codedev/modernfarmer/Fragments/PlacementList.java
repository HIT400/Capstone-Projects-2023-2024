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

import com.codedev.modernfarmer.Adapters.PlacementListAdp;
import com.codedev.modernfarmer.Callbacks.PlacementListCallback;
import com.codedev.modernfarmer.Constants;
import com.codedev.modernfarmer.Dao.PlacementListDAO;
import com.codedev.modernfarmer.Database.MFDBClient;
import com.codedev.modernfarmer.Database.ModernFarmerDB;
import com.codedev.modernfarmer.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;


public class PlacementList extends Fragment implements PlacementListCallback {

    private RecyclerView placements_rcv;
    private FloatingActionButton add_new_placement_btn;

    private SwipeRefreshLayout swipeRefreshLayout;
    private PlacementListAdp placementListAdp;

    Frag_AddPlacement frag_addPlacement = new Frag_AddPlacement();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_placement_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        placements_rcv = view.findViewById(R.id.placements_list_rcv);
        add_new_placement_btn = view.findViewById(R.id.add_placement_btn);
        placements_rcv.setLayoutManager(new LinearLayoutManager(getContext()));
        swipeRefreshLayout = view.findViewById(R.id.refresher);
        swipeRefreshLayout.setOnRefreshListener(() -> getPlacementList(this));
        getPlacementList(this);
        add_new_placement_btn.setOnClickListener(v -> requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frag_container,frag_addPlacement).commit());

    }

    @Override
    public void onPlacementsReceived(List<com.codedev.modernfarmer.Entities.PlacementList> placementList) {
        placementListAdp = new PlacementListAdp(placementList,getContext());
        placements_rcv.setAdapter(placementListAdp);
        if(swipeRefreshLayout.isRefreshing())swipeRefreshLayout.setRefreshing(false);
    }

    private void getPlacementList(PlacementListCallback callback){
        ModernFarmerDB database = ((MFDBClient) getActivity().getApplication()).getDatabase();
        PlacementListDAO placementListDAO = database.placementListDAO();
        CompletableFuture.runAsync(() -> {
            List<com.codedev.modernfarmer.Entities.PlacementList> placementLists = placementListDAO.getAllPlacements();
            callback.onPlacementsReceived(placementLists);
        });

    }


}