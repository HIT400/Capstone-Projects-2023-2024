package com.codedev.modernfarmer.Fragments;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.codedev.modernfarmer.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class Frag_AddStock extends Fragment {


    private FrameLayout frameLayout;
    private TextView stockTypesSelector;

    private MaterialButton save_stock;

    private TextInputEditText med_name, med_quantity, chicks_breed, chicks_number, feed_type, number_of_bags;

    ArrayAdapter<String> stockTyps;

    Frag_Add_Meds frag_add_meds = new Frag_Add_Meds();
    Frag_Add_Feed frag_add_feed = new Frag_Add_Feed();
    Frag_Add_Chicks frag_add_chicks = new Frag_Add_Chicks();

    private Dialog stockTypesDialog;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_frag__add_stock, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        frameLayout = view.findViewById(R.id.stock_type_fields);
        stockTypesSelector = view.findViewById(R.id.stock_type);
        ArrayList<String> types = new ArrayList<>();
        types.add("Feed");
        types.add("Chicks");
        types.add("Medication");

        stockTyps = new ArrayAdapter<>(getContext(), com.google.android.material.R.layout.support_simple_spinner_dropdown_item,types);


        stockTypesSelector.setOnClickListener(v -> {

            stockTypesDialog = new Dialog(getContext());
            stockTypesDialog.setContentView(R.layout.stock_type_spinner);
            // set custom height and width
            stockTypesDialog.getWindow().setLayout(800,1200);
            // set transparent background
            stockTypesDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            // show dialog
            stockTypesDialog.show();

            //Listview & TextView

            ListView stocktypeslsv = stockTypesDialog.findViewById(R.id.stock_type_lsv);
            TextView search_st = stockTypesDialog.findViewById(R.id.search_stock_type_edt);

            stocktypeslsv.setAdapter(stockTyps);

            stocktypeslsv.setOnItemClickListener((parent, view1, position, id) -> {
                stockTypesSelector.setText(stockTyps.getItem(position));
                stockTypesDialog.dismiss();

                //Inflate Frag Layout with Screens
                switch(stockTyps.getItem(position)){
                    case "Feed":
                        requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.stock_type_fields,frag_add_feed).commit();
                        break;

                    case "Chicks":
                        requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.stock_type_fields,frag_add_chicks).commit();
                        break;

                    case "Medication":
                        requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.stock_type_fields,frag_add_meds).commit();
                        break;

                }

            });


            search_st.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    stockTyps.getFilter().filter(s);

                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });


        });



    }
}