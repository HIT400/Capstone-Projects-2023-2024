package com.codedev.modernfarmer.Adapters;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codedev.modernfarmer.Activities.Act_Buyer_Options;
import com.codedev.modernfarmer.R;

import java.util.ArrayList;
import java.util.List;

public class BuyingCompanies extends RecyclerView.Adapter<BuyingCompanies.BCViewHolder> {

   private List<com.codedev.modernfarmer.Classes.BuyingCompanies> companiesList;


    public BuyingCompanies(List<com.codedev.modernfarmer.Classes.BuyingCompanies> companiesList) {
        this.companiesList = companiesList;
    }

    @NonNull
    @Override
    public BuyingCompanies.BCViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.buying_companies_layout,parent,false);
        return new BCViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BuyingCompanies.BCViewHolder holder, int position) {
        holder.onBind(companiesList.get(position));

        holder.itemView.setOnClickListener(v -> {
            Log.d("Company_ID",companiesList.get(position).getBuying_price());
            Intent openBuyerOptions = new Intent(v.getContext(), Act_Buyer_Options.class);
            openBuyerOptions.putExtra("Selected_Company",companiesList.get(position).getCompany_name());
            openBuyerOptions.putExtra("Chicks_Price",companiesList.get(position).getBuying_price());
            v.getContext().startActivity(openBuyerOptions);
        });


    }

    @Override
    public int getItemCount() {
        if(companiesList != null){
            return companiesList.size();
        }else{
            return 0;
        }
    }

    public class BCViewHolder extends RecyclerView.ViewHolder{

        private TextView company_name, address, unit_price;

        public BCViewHolder(@NonNull View itemView) {
            super(itemView);
            company_name = itemView.findViewById(R.id.company_name_buyin_price);
            address = itemView.findViewById(R.id.address_buying_companies);
            unit_price = itemView.findViewById(R.id.buying_price);
        }

        private void onBind(com.codedev.modernfarmer.Classes.BuyingCompanies buyingCompanies){
            company_name.setText(buyingCompanies.getCompany_name());
            address.setText(buyingCompanies.getAddress());
            unit_price.setText(buyingCompanies.getBuying_price());
        }
    }
}
