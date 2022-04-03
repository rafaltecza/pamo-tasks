package com.example.tipper;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView currentHeight;
    TextView currentWeight;
    TextView currentOutput;
    ImageView incrementWeight, decrementWeight;
    SeekBar seekBarForHeight;
    Button calculateBMI;

    int intWeight = 60;
    int intAge = 18;
    int currentProgress;

    String stringProgress = "170";
    String stringWeight = "60";
    String stringAge = "18";

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        currentOutput = findViewById(R.id.output);
        currentWeight = findViewById(R.id.currentweight);
        currentHeight = findViewById(R.id.currentheight);
        incrementWeight = findViewById(R.id.incremetweight);
        decrementWeight = findViewById(R.id.decrementweight);
        calculateBMI = findViewById(R.id.calculatebmi);
        seekBarForHeight = findViewById(R.id.seekbarforheight);

        seekBarForHeight.setMax(300);
        seekBarForHeight.setProgress(170);
        seekBarForHeight.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                currentProgress=progress;
                stringProgress =String.valueOf(currentProgress);
                currentHeight.setText(stringProgress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });


        incrementWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intWeight = intWeight + 1;
                stringWeight = String.valueOf(intWeight);
                currentWeight.setText(stringWeight);
            }
        });

        decrementWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intWeight = intWeight -1;
                stringWeight = String.valueOf(intWeight);
                currentWeight.setText(stringWeight);
            }
        });

        calculateBMI.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                if(stringProgress.equals("0")) {

                    Toast.makeText(getApplicationContext(),
                            "Najpierw ustaw wzrost",
                            Toast.LENGTH_SHORT).show();

                } else if(intAge == 0 || intAge < 0) {

                    Toast.makeText(getApplicationContext(),
                            "Wiek jest nieprawidłowy",
                            Toast.LENGTH_SHORT).show();

                }  else if(intWeight ==0|| intWeight <0) {

                    Toast.makeText(getApplicationContext(),
                            "Waga jest nieprawidłowa",
                            Toast.LENGTH_SHORT).show();

                } else {

                    float height = Integer.parseInt(stringProgress);
                    int weight = Integer.parseInt(stringWeight);
                    height = height / 100;
                    float intBMI = weight / (height * height);

                    System.out.println(intBMI);
                    double round = Math.round(intBMI * 100.0) / 100.0;
                    String suffix = "(" + round + ")";

                    if(intBMI < 16) {
                        currentOutput.setText("Całkowite wyniszczenie " + suffix);
                    } else if(intBMI < 16.9 && intBMI > 16) {
                        currentOutput.setText("Poważna niedowaga " + suffix);
                    } else if(intBMI < 18.4 && intBMI > 17) {
                        currentOutput.setText("Umiarkowana niedowaga " + suffix);
                    } else if(intBMI < 24.9 && intBMI > 18.5) {
                        currentOutput.setText("Prawidłowa masa " + suffix);
                    } else if(intBMI < 29.9 && intBMI > 25) {
                        currentOutput.setText("Otuszczenie " + suffix);
                    } else if(intBMI < 34.9 && intBMI > 30) {
                        currentOutput.setText("Otyłość " + suffix);
                    } else {
                        currentOutput.setText("Oj za dużo");
                    }
                    currentOutput.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}