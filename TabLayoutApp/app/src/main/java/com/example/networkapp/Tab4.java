package com.example.networkapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.networkapp.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Tab4 extends Fragment {
    private Button startButton;
    private TextView dumpsysOutput;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab4, container, false);
        startButton = view.findViewById(R.id.startBtn);
        dumpsysOutput = view.findViewById(R.id.captureOutput);
        ScrollView scrollView = view.findViewById(R.id.scrollView);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    startCapture();
            }
        });

        return view;
    }

    private void startCapture() {
        try {
            Process process = Runtime.getRuntime().exec("dumpsys");
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder output = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }
            process.waitFor();

            dumpsysOutput.setText(output.toString());

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}