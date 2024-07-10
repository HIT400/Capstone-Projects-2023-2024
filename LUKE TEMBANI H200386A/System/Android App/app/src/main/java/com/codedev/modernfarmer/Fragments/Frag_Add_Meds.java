package com.codedev.modernfarmer.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.codedev.modernfarmer.Dao.MedsInventoryDAO;
import com.codedev.modernfarmer.Database.MFDBClient;
import com.codedev.modernfarmer.Database.ModernFarmerDB;
import com.codedev.modernfarmer.Entities.MedsInventory;
import com.codedev.modernfarmer.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Date;
import java.util.concurrent.CompletableFuture;

public class Frag_Add_Meds extends Fragment {


    private TextInputEditText med_name, med_quantity;
    private MaterialButton save_med;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_frag__add__meds, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        med_name = view.findViewById(R.id.meds_type);
        med_quantity = view.findViewById(R.id.meds_qty);
        save_med = view.findViewById(R.id.save_stock_btn);


        save_med.setOnClickListener(v -> {
            if(med_name.getText().toString().isEmpty() || med_quantity.getText().toString().isEmpty()){
                Toast.makeText(getContext(), "Fill in all fields", Toast.LENGTH_SHORT).show();
            }else{
                MedsInventory new_med = new MedsInventory();
                Date current = new Date();
                new_med.setMed_name(med_name.getText().toString());
                new_med.setMed_quantity(Integer.parseInt(med_quantity.getText().toString()));
                new_med.setModified_date(current);
                saveMed(new_med);
                Log.d("Method","Method Called");
            }
        });

    }

    private void saveMed(MedsInventory medsInventory) {

        ModernFarmerDB database = ((MFDBClient) requireActivity().getApplication()).getDatabase();
        MedsInventoryDAO medsInventoryDAO = database.medsInventoryDAO();
        CompletableFuture.runAsync(() -> {
       medsInventoryDAO.insert(medsInventory);
       getActivity().runOnUiThread(() -> Toast.makeText(getContext(), "Medication added successfully", Toast.LENGTH_SHORT).show());
        });




    }
}