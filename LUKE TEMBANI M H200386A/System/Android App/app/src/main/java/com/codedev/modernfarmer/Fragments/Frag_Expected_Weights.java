package com.codedev.modernfarmer.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.codedev.modernfarmer.R;


public class Frag_Expected_Weights extends Fragment {

    private TextView expected_weight, week_slider;
    private SeekBar number_of_weeks;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_frag__expected__weights, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        expected_weight = view.findViewById(R.id.expected_weight);
        number_of_weeks = view.findViewById(R.id.eweight_seekbar);
        week_slider = view.findViewById(R.id.eweight_hint);


        number_of_weeks.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                switch (progress){
                    case 0:
                        expected_weight.setText("Expected Weight: 38g - 45g");
                        week_slider.setText("Week"+ " "+ progress);
                        break;
                    case 1:
                        expected_weight.setText("Expected Weight: 100g - 150g");
                        week_slider.setText("Week"+ " "+ progress);
                        break;

                    case 2:
                        expected_weight.setText("Expected Weight: 200g - 300g");
                        week_slider.setText("Week"+ " "+ progress);
                        break;

                    case 3:
                        expected_weight.setText("Expected Weight: 400g - 500g");
                        week_slider.setText("Week"+ " "+ progress);
                        break;

                    case 4:
                        expected_weight.setText("Expected Weight: 600g - 800g");
                        week_slider.setText("Week"+ " "+ progress);
                        break;


                    case 5:
                        expected_weight.setText("Expected Weight: 900g - 1.2Kg");
                        week_slider.setText("Week"+ " "+ progress);
                        break;

                    case 6:
                        expected_weight.setText("Expected Weight: 1.5Kg - 2.5Kg");
                        week_slider.setText("Week"+ " "+ progress);
                        break;
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}