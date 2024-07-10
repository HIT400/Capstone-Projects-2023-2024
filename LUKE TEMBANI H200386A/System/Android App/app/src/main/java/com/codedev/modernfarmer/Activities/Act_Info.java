package com.codedev.modernfarmer.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.codedev.modernfarmer.Adapters.InfoVPAdp;
import com.codedev.modernfarmer.Fragments.InfoFrags.Frag_Breeding;
import com.codedev.modernfarmer.Fragments.InfoFrags.Frag_Nutrition;
import com.codedev.modernfarmer.Fragments.InfoFrags.Frag_Security;
import com.codedev.modernfarmer.R;

import me.relex.circleindicator.CircleIndicator3;

public class Act_Info extends AppCompatActivity {

    private ViewPager2 info_viewpager;
    private InfoVPAdp adapter;

    private CircleIndicator3 indicator3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_info);

        info_viewpager = findViewById(R.id.viewpager_box);
        adapter = new InfoVPAdp(getSupportFragmentManager(),getLifecycle());

        indicator3 = findViewById(R.id.circle_indicator);
        indicator3.setViewPager(info_viewpager);

        //Add Frags to Adapter
        adapter.addFragment(new Frag_Breeding());
        adapter.addFragment(new Frag_Security());


        //Set Orientation
        info_viewpager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);

        //Assign Adapter
        info_viewpager.setAdapter(adapter);
    }
}