package com.codedev.modernfarmer.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

public class InfoVPAdp extends FragmentStateAdapter {

    private ArrayList<Fragment> fragmentArrayList = new ArrayList<>();

    public InfoVPAdp(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragmentArrayList.get(position);
    }

    public void addFragment(Fragment fragment) {
        fragmentArrayList.add(fragment);
    }


    @Override
    public int getItemCount() {
        if(fragmentArrayList != null){
            return fragmentArrayList.size();
        }else{
            return 0;
        }
    }
}
