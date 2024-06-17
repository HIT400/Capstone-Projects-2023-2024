package com.codedev.modernfarmer.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codedev.modernfarmer.Entities.FeedStock;
import com.codedev.modernfarmer.R;

import java.util.List;

public class FeedStockAdp extends RecyclerView.Adapter<FeedStockAdp.FeedStockVh> {

    private List<FeedStock> feedStockList;
    private Context context;

    public FeedStockAdp(List<FeedStock> feedStockList, Context context) {
        this.feedStockList = feedStockList;
        this.context = context;
    }

    @NonNull
    @Override
    public FeedStockAdp.FeedStockVh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.supplier_products_lyt,parent,false);
        return new FeedStockVh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedStockAdp.FeedStockVh holder, int position) {
        holder.onBind(feedStockList.get(position));

    }

    @Override
    public int getItemCount() {
        if(feedStockList != null){
            return feedStockList.size();
        }else{
            return 0;
        }

    }

    public class FeedStockVh extends RecyclerView.ViewHolder{
        private TextView product_name;

        public FeedStockVh(@NonNull View itemView) {
            super(itemView);
            product_name = itemView.findViewById(R.id.product_name);
        }

        public void onBind(FeedStock feedStock){
            product_name.setText(feedStock.getProduct_name());

        }

    }
}
