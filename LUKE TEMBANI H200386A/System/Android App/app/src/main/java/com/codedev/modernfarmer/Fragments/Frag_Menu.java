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

import com.codedev.modernfarmer.Adapters.InfoListAdp;
import com.codedev.modernfarmer.R;

import java.util.ArrayList;

public class Frag_Menu extends Fragment {

    private RecyclerView info_cards_rcv;
    private ArrayList<String> infolist;
    private InfoListAdp infoListAdp;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_frag__menu, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        info_cards_rcv = view.findViewById(R.id.info_cards_rcv);
        info_cards_rcv.setLayoutManager(new LinearLayoutManager(getContext()));
        infolist = new ArrayList<>();
        infolist.add("Biosecurity Measures");
        infolist.add("Breeding and Genetics");
        infolist.add("Effective Poultry Nutrition");
        infolist.add("Poultry Housing and Equipment");
        infolist.add("Disease Prevention and Management");
        infolist.add("Meat Production and Processing");
        infolist.add("Poultry Health and Veterinary Care");
        infolist.add("Sustainable and Organic Poultry Farming");
        infoListAdp = new InfoListAdp(infolist);
        info_cards_rcv.setAdapter(infoListAdp);
    }
}