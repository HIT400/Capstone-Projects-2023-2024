package com.codedev.modernfarmer.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import com.codedev.modernfarmer.Constants;
import com.codedev.modernfarmer.Fragments.Frag_AddPlacement;
import com.codedev.modernfarmer.Fragments.Frag_AddStock;
import com.codedev.modernfarmer.Fragments.Frag_Buy_Feed;
import com.codedev.modernfarmer.Fragments.Frag_CheckWeather;
import com.codedev.modernfarmer.Fragments.Frag_Contact_Vet;
import com.codedev.modernfarmer.Fragments.Frag_Env_Control;
import com.codedev.modernfarmer.Fragments.Frag_Expected_Weights;
import com.codedev.modernfarmer.Fragments.Frag_Home;
import com.codedev.modernfarmer.Fragments.Frag_Menu;
import com.codedev.modernfarmer.Fragments.Frag_Required_Stock;
import com.codedev.modernfarmer.Fragments.Frag_Sell_Chickens;
import com.codedev.modernfarmer.Fragments.PlacementList;
import com.codedev.modernfarmer.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class Act_Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;

    private SharedPreferences sharedPreferences;
    BottomNavigationView bottomNavigationView;

    NavigationView navigationView;
    //Frags

    Frag_Sell_Chickens frag_sellChickens = new Frag_Sell_Chickens();
    Frag_Menu frag_menu = new Frag_Menu();

    Frag_Expected_Weights frag_expected_weights = new Frag_Expected_Weights();

    Frag_Env_Control env_control = new Frag_Env_Control();

    Frag_Required_Stock frag_required_stock = new Frag_Required_Stock();

    Frag_CheckWeather frag_checkWeather = new Frag_CheckWeather();

    PlacementList frag_placementList = new PlacementList();

    Frag_Buy_Feed frag_buy_feed = new Frag_Buy_Feed();

    Frag_Contact_Vet frag_contact_vet = new Frag_Contact_Vet();

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_home);

        sharedPreferences = getSharedPreferences(Constants.SHARED_PREFS_NAME, Context.MODE_PRIVATE);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_lyt);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(Act_Home.this,drawerLayout,toolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();



        bottomNavigationView = findViewById(R.id.bottom_navigation);
        getSupportFragmentManager().beginTransaction().replace(R.id.frag_container,frag_checkWeather).commit();

        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.home:
                    getSupportFragmentManager().beginTransaction().replace(R.id.frag_container,frag_checkWeather).commit();
                    return true;


                case R.id.menu:
                    getSupportFragmentManager().beginTransaction().replace(R.id.frag_container,frag_menu).commit();
                    return true;


                case R.id.settings:
                    getSupportFragmentManager().beginTransaction().replace(R.id.frag_container, frag_sellChickens).commit();
                    return true;

            }
            return false;
        });

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){

            case R.id.add_placement:
                getSupportFragmentManager().beginTransaction().replace(R.id.frag_container, frag_placementList).commit();
                break;

            case R.id.alter_env:
                getSupportFragmentManager().beginTransaction().replace(R.id.frag_container, env_control).commit();
                break;


            case R.id.contact_vet:
                getSupportFragmentManager().beginTransaction().replace(R.id.frag_container, frag_contact_vet).commit();
                break;

            case R.id.buy_feed:
                getSupportFragmentManager().beginTransaction().replace(R.id.frag_container,frag_buy_feed).commit();
                getSupportFragmentManager().beginTransaction().addToBackStack("buy_feed").commit();

                break;

            case R.id.expected_weights:
                getSupportFragmentManager().beginTransaction().replace(R.id.frag_container,frag_expected_weights).commit();
                break;

            case R.id.required_stocks:
                getSupportFragmentManager().beginTransaction().replace(R.id.frag_container,frag_required_stock).commit();
                break;


            case R.id.logout:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Confirm");
                builder.setMessage("Are you sure you want to log out?");
                builder.setPositiveButton("Yes", (dialog, which) -> {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("LOGIN_STATUS",false).apply();
                    Intent logout = new Intent(Act_Home.this, Act_Login.class);
                    startActivity(logout);
                    finish();
                });


                builder.setNegativeButton("No", (dialog, which) -> {
                    //Do nothing
                    dialog.dismiss();
                });

                AlertDialog dialog = builder.create();
                dialog.show();

        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}