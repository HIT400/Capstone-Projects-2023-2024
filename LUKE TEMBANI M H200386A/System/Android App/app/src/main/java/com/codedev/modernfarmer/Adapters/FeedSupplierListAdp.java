package com.codedev.modernfarmer.Adapters;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.codedev.modernfarmer.Activities.Act_Supplier_Products;
import com.codedev.modernfarmer.Classes.FeedSuppliers;
import com.codedev.modernfarmer.Fragments.Frag_Supplier_Products;
import com.codedev.modernfarmer.R;

import org.w3c.dom.Text;

import java.util.List;

public class FeedSupplierListAdp extends RecyclerView.Adapter<FeedSupplierListAdp.FeedSupplierVh> {

    private List<FeedSuppliers> feedSuppliersList;
    private Context context;

    private Activity activity;

    public FeedSupplierListAdp(List<FeedSuppliers> feedSuppliersList, Context context, Activity activity) {
        this.feedSuppliersList = feedSuppliersList;
        this.context = context;
        this.activity = activity;
    }

    @NonNull
    @Override
    public FeedSupplierListAdp.FeedSupplierVh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.feed_supplier_list_lyt,parent,false);
        return new FeedSupplierVh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedSupplierListAdp.FeedSupplierVh holder, int position) {
        holder.onBind(feedSuppliersList.get(position));

        holder.itemView.setOnClickListener(v -> {
            Intent openSupplierProducts = new Intent(context, Act_Supplier_Products.class);
            openSupplierProducts.putExtra("Company_name",feedSuppliersList.get(position).getCompanyname());
            context.startActivity(openSupplierProducts);

        });
    }

    @Override
    public int getItemCount() {
        if(feedSuppliersList != null){
            return feedSuppliersList.size();
        }else{
            return 0;
        }

    }

    public class FeedSupplierVh extends RecyclerView.ViewHolder{

        private List<FeedSuppliers> feedSuppliersList;
        private TextView company_name;

        public FeedSupplierVh(@NonNull View itemView) {
            super(itemView);
            company_name = itemView.findViewById(R.id.feed_supplier_name);
        }

        public void onBind(FeedSuppliers feedSuppliers){
            company_name.setText(feedSuppliers.getCompanyname());
        }
    }
}
