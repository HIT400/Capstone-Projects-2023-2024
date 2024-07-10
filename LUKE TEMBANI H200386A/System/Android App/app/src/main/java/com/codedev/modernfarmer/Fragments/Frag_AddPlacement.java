package com.codedev.modernfarmer.Fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.codedev.modernfarmer.API.ApiService;
import com.codedev.modernfarmer.API.RetrofitClient;
import com.codedev.modernfarmer.Callbacks.OnFeedSuppliersReceived;
import com.codedev.modernfarmer.Classes.CompanyStockDC;
import com.codedev.modernfarmer.Classes.FeedSuppliers;
import com.codedev.modernfarmer.Constants;
import com.codedev.modernfarmer.Dao.CompanyStockDAO;
import com.codedev.modernfarmer.Dao.PlacementListDAO;
import com.codedev.modernfarmer.Entities.Chicks_Placement;
import com.codedev.modernfarmer.Entities.CompanyStock;
import com.codedev.modernfarmer.Database.MFDBClient;
import com.codedev.modernfarmer.Database.ModernFarmerDB;
import com.codedev.modernfarmer.Entities.Components;
import com.codedev.modernfarmer.Entities.PlacementList;
import com.codedev.modernfarmer.R;
import com.codedev.modernfarmer.Utils.CheckNetwork;
import com.google.android.material.button.MaterialButton;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Frag_AddPlacement extends Fragment implements OnFeedSuppliersReceived{

    private MaterialButton place_order;
    private Dialog supplier_dialog, breed_dialog;

    private TextView supplier_name,chicks_breed;

    private ModernFarmerDB database;

    private EditText search_suppl, search_breed, chicks_number;

    private SharedPreferences sharedPreferences;


    private ArrayList<String> suppliers_list, breed_list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_frag__weather, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Get Db Instance
        database = ((MFDBClient) requireActivity().getApplication()).getDatabase();

        CompanyStockDC dc = new CompanyStockDC("Profeeds","feed");
        getStock(dc);


        place_order = view.findViewById(R.id.place_placement_btn);
        supplier_name = view.findViewById(R.id.chicks_supplier);
        chicks_breed = view.findViewById(R.id.chicks_breed);
        chicks_number = view.findViewById(R.id.chicks_number);
        sharedPreferences = getContext().getSharedPreferences(Constants.SHARED_PREFS_NAME, Context.MODE_PRIVATE);
        suppliers_list = new ArrayList<>();
        getSuppliers(this);
        breed_list = new ArrayList<>();
        //Breeds
        breed_list.add("Ross 308");
        breed_list.add("Cobb 500");
        breed_list.add("Habbard");
        breed_list.add("Arbor Cres");
        breed_list.add("Indian River");


        //Supplier
        supplier_name.setOnClickListener(v -> {
            supplier_dialog = new Dialog(getContext());
            supplier_dialog.setContentView(R.layout.searchable_spinner);
            // set custom height and width
            supplier_dialog.getWindow().setLayout(800,1200);
            // set transparent background
            supplier_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            // show dialog
            supplier_dialog.show();
            search_suppl = supplier_dialog.findViewById(R.id.search_supplier_edt);
            ListView lsv = supplier_dialog.findViewById(R.id.suppliers_listv);
            ArrayAdapter<String> suppliers = new ArrayAdapter<>(getContext(), com.google.android.material.R.layout.support_simple_spinner_dropdown_item,suppliers_list);
            lsv.setAdapter(suppliers);
            search_suppl.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    suppliers.getFilter().filter(s);
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });


            lsv.setOnItemClickListener((parent, view1, position, id) -> {
                supplier_name.setText(suppliers.getItem(position));
                supplier_dialog.dismiss();
            });

        });

        //Breed
        chicks_breed.setOnClickListener(v -> {
            breed_dialog = new Dialog(getContext());
            breed_dialog.setContentView(R.layout.breed_spinner_lyt);
            // set custom height and width
            breed_dialog.getWindow().setLayout(800,1200);
            // set transparent background
            breed_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            // show dialog
            breed_dialog.show();

            ListView breedlsv = breed_dialog.findViewById(R.id.breed_listv);
            search_breed = breed_dialog.findViewById(R.id.search_breed_edt);
            ArrayAdapter<String> breedAdp = new ArrayAdapter<>(getContext(), com.google.android.material.R.layout.support_simple_spinner_dropdown_item,breed_list);
            breedlsv.setAdapter(breedAdp);


            //Search Breed
            search_breed.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    breedAdp.getFilter().filter(s);

                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            //Selected Item
            breedlsv.setOnItemClickListener((parent, view12, position, id) -> {
                chicks_breed.setText(breedAdp.getItem(position));
                breed_dialog.dismiss();
            });

        });


        place_order.setOnClickListener(v -> {
            Date current = new Date();
            Chicks_Placement chicks_placement = new Chicks_Placement();
            if(CheckNetwork.isNetworkAvailable(getContext())){

                if(supplier_name.getText().toString().isEmpty() || chicks_breed.getText().toString().isEmpty() || chicks_number.getText().toString().isEmpty()){
                    Toast.makeText(getContext(), "Fill in all details", Toast.LENGTH_SHORT).show();
                    chicks_number.requestFocus();
                }else{

                    chicks_placement.setChicks_number(Integer.parseInt(chicks_number.getText().toString()));
                    chicks_placement.setPlacement_date(current);
                    chicks_placement.setChicks_breed(chicks_breed.getText().toString());
                    chicks_placement.setSupplier(supplier_name.getText().toString());
                    chicks_placement.setTotal_price(100.00);
                    chicks_placement.setUnit_price(0.85);
                    chicks_placement.setCustomer_name(sharedPreferences.getString("CUSTOMER_NAME","Default"));
                    postPlacement(chicks_placement);


                }


            }else{
                if(supplier_name.getText().toString().isEmpty() || chicks_breed.getText().toString().isEmpty() || chicks_number.getText().toString().isEmpty()){
                    Toast.makeText(getContext(), "Fill in all details", Toast.LENGTH_SHORT).show();
                    chicks_number.requestFocus();

                }else{

                    chicks_placement.setChicks_number(Integer.parseInt(chicks_number.getText().toString()));
                    chicks_placement.setPlacement_date(current);
                    chicks_placement.setChicks_breed(chicks_breed.getText().toString());
                    chicks_placement.setSupplier(supplier_name.getText().toString());
                    chicks_placement.setTotal_price(100.00);
                    chicks_placement.setUnit_price(0.85);
                    chicks_placement.setCustomer_name(sharedPreferences.getString("CUSTOMER_NAME","Default"));


                }
            }

        });
    }

    private void savePlacement(PlacementList placementList){
        ModernFarmerDB database = ((MFDBClient) getActivity().getApplication()).getDatabase();
        PlacementListDAO placementListDAO = database.placementListDAO();
        CompletableFuture.runAsync(() -> {
            placementListDAO.insert(placementList);
            Log.d("Saved","Placement Saved");
        });

    }

    private void postPlacement(Chicks_Placement chicks_placement){
        Retrofit retrofit = RetrofitClient.getClient();
        ApiService api = retrofit.create(ApiService.class);
        Call<JsonObject> call = api.postPlacement(chicks_placement);

        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                if(response.isSuccessful()){
                    JsonObject placement_data = response.body();
                    PlacementList placement = new PlacementList();
                    placement.setCompany_name(supplier_name.getText().toString());
                    placement.setPlacement_id("PL_"+placement_data.get("placement_id"));
                    placement.setPlacement_status("Pending");
                    placement.setCustomer_username(sharedPreferences.getString("CUSTOMER_NAME","Default"));
                    placement.setChicks_quantity(chicks_number.getText().toString());
                    placement.setPlacement_date(new Date());
                    placement.setChicks_breed(chicks_breed.getText().toString());
                    savePlacement(placement);
                    Toast.makeText(getContext(), "Placement Saved", Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(getContext(), "Failed to Save Placement!", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

                Toast.makeText(getContext(), "Failed to Save Placement!"+t, Toast.LENGTH_SHORT).show();
            }
        });



    }

    private ArrayList<String> getSuppliers(OnFeedSuppliersReceived callback){
        Retrofit retrofit = RetrofitClient.getClient();
        ApiService api = retrofit.create(ApiService.class);
        ArrayList<String> feedSuppliersArrayList = new ArrayList<>();
        Call<List<FeedSuppliers>> call = api.getSuppliers();

        call.enqueue(new Callback<List<FeedSuppliers>>() {
            @Override
            public void onResponse(Call<List<FeedSuppliers>> call, Response<List<FeedSuppliers>> response) {
                if(response.isSuccessful()){
                    callback.onFeedSuppliersReceived(response.body());
                }else{
                    Toast.makeText(getContext(), "Failed to get Suppliers", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<FeedSuppliers>> call, Throwable t) {

            }
        });

        Log.d("Suppliers",feedSuppliersArrayList.toString());
        return feedSuppliersArrayList;
    }


    private void checkBeforeInsert(List<CompanyStock> companyStockList){

        Log.d("Recevied List", companyStockList.toString());

        CompanyStockDAO stockDAO = database.companyStockDAO();

        for (CompanyStock stock : companyStockList){

            CompletableFuture.runAsync(() -> {
                Log.d("Names",stock.getCompany_name()+stock.getProduct_name());
                List<CompanyStock> retrievedList = stockDAO.getCompanyStock(stock.getCompany_name(),stock.getProduct_name());


                if(!retrievedList.isEmpty()){
                    CompletableFuture.runAsync(() -> {
                        Log.d("Stock Update",stock.toString());
                        stockDAO.update(stock);
                        Log.d("Stock Updated","Stock Updated Success");
                    });
                }else{
                    CompletableFuture.runAsync(() -> {
                        Log.d("Stock Insert",stock.toString());
                        stockDAO.insert(stock);
                        Log.d("Stock Inserted","Stock Inserted Successfully");
                    });
                }
            });



        }


    }


    private void getStock(CompanyStockDC companyStockDC){
        Retrofit retrofit = RetrofitClient.getClient();
        ApiService api = retrofit.create(ApiService.class);
        Call<List<CompanyStock>> stockList = api.getStock(companyStockDC);
        stockList.enqueue(new Callback<List<CompanyStock>>() {
            @Override
            public void onResponse(Call<List<CompanyStock>> call, Response<List<CompanyStock>> response) {
                if(response.isSuccessful()){
                    Log.d("Incoming","Stock Received"+response.body());
                    checkBeforeInsert(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<CompanyStock>> call, Throwable t) {
                t.printStackTrace();

            }
        });
}

    @Override
    public void onFeedSuppliersReceived(List<FeedSuppliers> feedSuppliersList) {
        Log.i("List",feedSuppliersList.toString());
        for(FeedSuppliers feedSupplier:feedSuppliersList){
            suppliers_list.add(feedSupplier.getCompanyname());
        }
    }
}


