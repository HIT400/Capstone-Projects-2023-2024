package com.codedev.modernfarmer.Adapters;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.codedev.modernfarmer.Classes.Veterinarians;
import com.codedev.modernfarmer.R;

import java.util.List;

public class VetListAdp extends RecyclerView.Adapter<VetListAdp.VetListVh> {

    private static final int PERMISSION_REQUEST_CALL_PHONE = 90;
    private List<Veterinarians> veterinariansList;
    private Context context;

    private Activity parentActivity;


    public VetListAdp(List<Veterinarians> veterinariansList, Context context, Activity parentActivity) {
        this.veterinariansList = veterinariansList;
        this.context = context;
        this.parentActivity = parentActivity;
    }

    @NonNull
    @Override
    public VetListAdp.VetListVh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vet_list_lyt,parent,false);
        return new VetListVh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VetListAdp.VetListVh holder, int position) {
        holder.onBind(veterinariansList.get(position));

        holder.itemView.setOnClickListener(v -> callVet(veterinariansList.get(position).getVet_contact()));

    }

    @Override
    public int getItemCount() {

        if(veterinariansList != null){
            return  veterinariansList.size();
        }else{
            return 0;
        }

    }


    public class VetListVh extends RecyclerView.ViewHolder{

        private TextView vetname, vetlocation;
        private List<Veterinarians> veterinariansList;

        public VetListVh(@NonNull View itemView) {
            super(itemView);
            vetname = itemView.findViewById(R.id.vetname);
            vetlocation = itemView.findViewById(R.id.vetlocation);
        }

        private void onBind(Veterinarians veterinarians){
            vetname.setText("Contact Name: "+veterinarians.getVet_name());
            vetlocation.setText("Service Area: "+veterinarians.getVet_location());
        }

    }

    private void callVet(String contact){
        if(ContextCompat.checkSelfPermission(parentActivity, Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(parentActivity, new String[]{Manifest.permission.CALL_PHONE}, PERMISSION_REQUEST_CALL_PHONE);
        }else{

            Intent openDialer = new Intent(Intent.ACTION_DIAL);
            openDialer.setData(Uri.parse("tel:"+contact));
            context.startActivity(openDialer);
        }

    }
}
