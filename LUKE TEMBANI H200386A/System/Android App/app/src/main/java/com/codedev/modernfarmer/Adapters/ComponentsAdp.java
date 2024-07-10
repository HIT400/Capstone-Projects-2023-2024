package com.codedev.modernfarmer.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.codedev.modernfarmer.API.ApiService;
import com.codedev.modernfarmer.API.RetrofitClient;
import com.codedev.modernfarmer.Classes.ComponentsSwitcher;
import com.codedev.modernfarmer.Dao.ComponentsDAO;
import com.codedev.modernfarmer.Database.MFDBClient;
import com.codedev.modernfarmer.Database.ModernFarmerDB;
import com.codedev.modernfarmer.Entities.Components;
import com.codedev.modernfarmer.R;
import com.google.android.material.button.MaterialButton;

import org.w3c.dom.Text;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ComponentsAdp extends RecyclerView.Adapter<ComponentsAdp.ComponentsAdpVh> {

    private List<Components> componentsSwitcherList;
    private Context context;

    private OnLightToggled onLightToggled;

    private Activity activity;


    public ComponentsAdp(List<Components> componentsSwitcherList, Context context, Activity activity) {
        this.componentsSwitcherList = componentsSwitcherList;
        this.context = context;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ComponentsAdp.ComponentsAdpVh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.components_layout, parent,false);
        return new ComponentsAdpVh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ComponentsAdp.ComponentsAdpVh holder, int position) {
        holder.onBind(componentsSwitcherList.get(position).getComponentsSwitcher());


    }

    @Override
    public int getItemCount() {

        if(componentsSwitcherList != null){
            return componentsSwitcherList.size();
        }else{
            return 0;
        }


    }

    public class ComponentsAdpVh extends RecyclerView.ViewHolder{

        private ImageView component_image;
        private TextView component_name;

        public ComponentsAdpVh(@NonNull View itemView) {
            super(itemView);
            component_image = itemView.findViewById(R.id.component_item_option);
            component_name = itemView.findViewById(R.id.component_name);


        }

        public void onBind(ComponentsSwitcher componentsSwitcher){
            component_name.setText(componentsSwitcher.getComponent_name());

            itemView.setOnClickListener(v -> onLightToggled.toggled(componentsSwitcher));

            if(Objects.equals(componentsSwitcher.getComponent_status(), "1")){
                component_image.setImageResource(R.drawable.light_on);
            }else{
                component_image.setImageResource(R.drawable.light_off);
            }
        }
    }


    public void setOnLightToggled(OnLightToggled onLightToggled) {
        this.onLightToggled = onLightToggled;
    }

    public interface OnLightToggled {

        void toggled(ComponentsSwitcher componentsSwitcher);
    }


}
