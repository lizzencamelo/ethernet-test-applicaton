package com.example.networkapp;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.networkapp.R;

import org.pcap4j.core.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Tab3 extends Fragment {
    private PcapHandle handle;
    private Button startButton;
    private Button stopButton;
    private TextView outputTextView;
    private EditText etInt;
    private Process process = null;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab3, container, false);

        outputTextView = view.findViewById(R.id.outputTextView);
        etInt = view.findViewById(R.id.intInput);
        ScrollView scrollView = view.findViewById(R.id.scrollView);

        startButton = view.findViewById(R.id.startBtn);
        stopButton = view.findViewById(R.id.stopBtn);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startTcpdump();
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopTcpdump();
            }
        });

        return view;
    }



    public void startTcpdump() {
        new TcpDump().execute();
    }

    private class TcpDump extends AsyncTask<Void, String, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            String interfce = etInt.getText().toString();
            try {
                ProcessBuilder processBuilder = new ProcessBuilder("su", "-c", "tcpdump", "-i", interfce);
                processBuilder.redirectErrorStream(true);

                process = processBuilder.start();
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                StringBuilder output = new StringBuilder();
                Log.i("INFO", "After Runtime execution. Process : " + process);

                String line;
                while ((line = reader.readLine()) != null) {
                    //Log.i("INFO", line);
                    publishProgress(line);
                    //output.append(line).append("\n");
                }

                int exitCode = process.waitFor();
                output.append("Process exited with code ").append(exitCode);

            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
                //outputTextView.setText("Error: " + e.getMessage());
            }

            return null;
        }
        @Override
        protected void onProgressUpdate(String... values) {
            String dumpResult = values[0];
            outputTextView.append(dumpResult + "\n");
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            outputTextView.append("\n\n");
        }
    }

    public void stopTcpdump()
    {
        try {
            if (process != null) {
                process.destroy(); // Terminate the process

                // Optionally, you can wait for the process to exit
                int exitCode = process.waitFor();
                Log.i("INFO", "Process exited with code: " + exitCode);

                process = null;    // Set it to null to indicate it's no longer running
                // Optionally update UI or show a message here
            } else {
                Log.i("INFO", "Process is already null. Nothing to stop.");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            outputTextView.setText("Error: " + e.getMessage());
        }
    }

}


