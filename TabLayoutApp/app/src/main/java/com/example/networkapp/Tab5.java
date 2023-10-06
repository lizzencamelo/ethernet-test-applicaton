package com.example.networkapp;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.example.networkapp.R;
public class Tab5 extends Fragment {
    private Button startIperfButton;
    private Button stopIperfButton;
    private TextView resultTextView, tvServerStarted;
    private Process process = null;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab5, container, false);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        startIperfButton = view.findViewById(R.id.startIperfButton);
        stopIperfButton = view.findViewById(R.id.stopIperfButton);
        resultTextView = view.findViewById(R.id.resultTextView);
        tvServerStarted = view.findViewById(R.id.serverStartedTextView);
        ScrollView scrollView = view.findViewById(R.id.scrollView);

        startIperfButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startIperfServer();
                tvServerStarted.setText("Server started.");
            }
        });

        stopIperfButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Call a method to stop the iPerf server (you need to implement this)
                stopIperfServer();
                tvServerStarted.setText("Server stopped.");
            }
        });


        return view;
    }

    public void startIperfServer() {
        new StartServerTask().execute();
    }

    private class StartServerTask extends AsyncTask<Void, String, Void> {
        @Override
        protected Void doInBackground(Void... params) {

            try {
                System.out.println("Before executing iperf -s");

                String command = "iperf3 -s"; // Replace with your desired iperf server command
                process = Runtime.getRuntime().exec(command);

                System.out.println("After executing iperf -s");

                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                System.out.println(reader);
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                System.out.println("Before reading buffer");

                while ((line = reader.readLine()) != null) {
                    //Log.i("INFO", line);
                    publishProgress(line);
                    //output.append(line).append("\n");
                }

                int exitCode = process.waitFor();
                //reader.close();

            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
                //resultTextView.setText("Error: " + e.getMessage());
            }

            return null;
        }
        @Override
        protected void onProgressUpdate(String... values) {
            String result = values[0];
            resultTextView.append(result + "\n");
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            resultTextView.append("\n\n");
        }
    }

    private void stopIperfServer() {
        if (process != null) {
            process.destroy(); // Terminate the process
            process = null;    // Set it to null to indicate it's no longer running
            // Optionally update UI or show a message here
        }
    }
}