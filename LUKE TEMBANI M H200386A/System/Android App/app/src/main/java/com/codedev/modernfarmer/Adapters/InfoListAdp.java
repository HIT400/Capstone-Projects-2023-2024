package com.codedev.modernfarmer.Adapters;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codedev.modernfarmer.Activities.Act_Info;
import com.codedev.modernfarmer.R;

import java.util.ArrayList;
import java.util.List;

public class InfoListAdp extends RecyclerView.Adapter<InfoListAdp.InfoListAdpViewHolder> {

    private List<String> inforlistitems;

    public InfoListAdp(List<String> inforlistitems) {
        this.inforlistitems = inforlistitems;
    }

    @NonNull
    @Override
    public InfoListAdp.InfoListAdpViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.infocarditem,parent,false);
        return new InfoListAdpViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InfoListAdp.InfoListAdpViewHolder holder, int position) {
        holder.onBind(inforlistitems.get(position));

        holder.itemView.setOnClickListener(v -> {
            Intent openVP = new Intent(v.getContext(), Act_Info.class);
            v.getContext().startActivity(openVP);
        });

    }

    @Override
    public int getItemCount() {
        if(inforlistitems != null){
            return inforlistitems.size();
        }else{
            return 0;
        }
    }


    public class InfoListAdpViewHolder extends RecyclerView.ViewHolder{
        TextView info_title;
        private String name;
        public InfoListAdpViewHolder(@NonNull View itemView) {
            super(itemView);
            info_title = itemView.findViewById(R.id.info_title);
        }

        private void onBind(String name){
            this.name = name;
            info_title.setText(name);

        }
    }
}
