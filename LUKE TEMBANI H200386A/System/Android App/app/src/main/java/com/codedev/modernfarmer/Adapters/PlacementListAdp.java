package com.codedev.modernfarmer.Adapters;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.codedev.modernfarmer.Entities.PlacementList;
import com.codedev.modernfarmer.R;

import java.util.List;
import java.util.Objects;

public class PlacementListAdp extends RecyclerView.Adapter<PlacementListAdp.PlacementListVh> {

    private List<PlacementList> placementList;

    private Context context;

    public PlacementListAdp(List<PlacementList> placementList, Context context) {
        this.placementList = placementList;
        this.context = context;
    }

    @NonNull
    @Override
    public PlacementListAdp.PlacementListVh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.placement_list_layout,parent,false);
        return new PlacementListVh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlacementListAdp.PlacementListVh holder, int position) {
        holder.onBind(placementList.get(position));

    }

    @Override
    public int getItemCount() {

        if(placementList != null){
            return placementList.size();
        }else{
            return 0;
        }

    }


    public class PlacementListVh extends RecyclerView.ViewHolder{
        private TextView company_name, quantity, breed, date, status;
        private List<PlacementList> placementLists;
        private CardView placement_card;

        public PlacementListVh(@NonNull View itemView) {
            super(itemView);
            company_name = itemView.findViewById(R.id.company_name_placement);
            quantity = itemView.findViewById(R.id.chicks_quantity_placement);
            breed = itemView.findViewById(R.id.chicks_breed_placement);
            date = itemView.findViewById(R.id.placement_date_placement);
            status = itemView.findViewById(R.id.status_placement);
            placement_card = itemView.findViewById(R.id.placement_card_item);
        }


        public void onBind(PlacementList placementList){
            company_name.setText(placementList.getCompany_name());
            quantity.setText(placementList.getChicks_quantity());
            breed.setText(placementList.getChicks_breed());
            date.setText("Date: "+placementList.getPlacement_date());
            status.setText(placementList.getPlacement_status());

            if(Objects.equals(placementList.getPlacement_status(),"Revoked")){
                status.setTextColor(0xFFe32222);
            } else if (Objects.equals(placementList.getPlacement_status(),"Approved")) {
                status.setTextColor(0xFF3a9e1e);
            } else{
                status.setTextColor(0xFFe3ac14);
            }

        }
    }



}
