package com.codedev.modernfarmer.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codedev.modernfarmer.Functions.PoultryParameters;
import com.codedev.modernfarmer.R;
import com.google.android.material.textfield.TextInputEditText;

public class Frag_Required_Stock extends Fragment {

    private TextView starter_amount, growers_amount, finisher_amount, stresspack, feeders, drinkers, room_size;
    private TextInputEditText required_birds;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_frag__required__stock, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        starter_amount = view.findViewById(R.id.starter_feed_amount);
        growers_amount = view.findViewById(R.id.grower_feed_amount);
        finisher_amount = view.findViewById(R.id.finisher_feed_amount);
        stresspack = view.findViewById(R.id.stresspack_amount);
        feeders = view.findViewById(R.id.feeders_amount);
        drinkers = view.findViewById(R.id.drinkers_amount);
        room_size = view.findViewById(R.id.room_size);
        required_birds = view.findViewById(R.id.num_required_birds);
        final int max = 1000000;

        InputFilter inputFilter = (source, start, end, dest, dstart, dend) -> {
            try {
                // Convert the input to an integer
                int input = Integer.parseInt(dest.toString() + source.toString());

                // Combine the destination text with the newly entered text
                String inputText = dest + source.toString();
                // Check if the input is empty and handle it as valid
                if (inputText.isEmpty()) {
                    return null; // Accept the input
                }

                // Check if the input is within the allowed range
                if (input <= max) {
                    return null; // Accept the input
                }
            } catch (NumberFormatException ignored) {
            }
            return ""; // Reject the input
        };

        required_birds.setFilters(new InputFilter[]{inputFilter});

        required_birds.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s)

            {
                if(s.toString().trim().isEmpty()){
                    PoultryParameters.Parameters parameters = PoultryParameters.calculateParams(0);
                    starter_amount.setText("Starter:"+ " "+ parameters.starterAmount + "Kg");
                    growers_amount.setText("Growers:"+ " "+ parameters.growersAmount+ "Kg");
                    finisher_amount.setText("Finisher:"+ " "+ parameters.finisherAmount+ "Kg");
                    stresspack.setText("Stress Pack:"+ " "+ parameters.stresspack + "Kg");
                    feeders.setText("Feeders:"+ " " + parameters.feeders);
                    drinkers.setText("Drinkers:"+ " " + parameters.drinkers);
                    room_size.setText("Room Size:"+ " " + parameters.roomSize + " sq ft");
                }else{

                    PoultryParameters.Parameters parameters = PoultryParameters.calculateParams(Integer.parseInt(s.toString().trim()));
                    starter_amount.setText("Starter:"+ " "+ parameters.starterAmount + "Kg");
                    growers_amount.setText("Growers:"+ " "+ parameters.growersAmount+ "Kg");
                    finisher_amount.setText("Finisher:"+ " "+ parameters.finisherAmount+ "Kg");
                    stresspack.setText("Stress Pack:"+ " "+ parameters.stresspack + "Kg");
                    feeders.setText("Feeders:"+ " " + parameters.feeders);
                    drinkers.setText("Drinkers:"+ " " + parameters.drinkers);
                    room_size.setText("Room Size:"+ " " + parameters.roomSize + " sq ft");
                }


            }
        });

    }
}