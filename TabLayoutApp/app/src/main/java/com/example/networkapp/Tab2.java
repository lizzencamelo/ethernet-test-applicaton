package com.example.networkapp;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.networkapp.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Tab2 extends Fragment implements View.OnClickListener {

    private boolean isPinging = false;
    private View view;
    private Button btn;
    private TextView tvData;
    private EditText etIP;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.fragment_tab2, container, false);
        btn = view.findViewById(R.id.startPingButton);
        etIP = view.findViewById(R.id.etIP);

        btn.setOnClickListener(this::onClick);
        tvData = view.findViewById(R.id.tvData);
        tvData.setMovementMethod(new ScrollingMovementMethod());
        return view;
    }

    public void startPing(View view) {
        if (!isPinging) {
            isPinging = true;
            new PingTask().execute();
        }
    }

    @Override
    public void onClick(View view) {
        startPing(view);
    }

    private class PingTask extends AsyncTask<Void, String, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            String ipAddress = etIP.getText().toString();
            //String pingCmd = "ping -c 4 " + ipAddress; // Adjust the number of pings as needed
                try {
                    //Runtime runtime = Runtime.getRuntime();
                    //Process process = runtime.exec(pingCmd);
                    Process process;
                    if (ipAddress.contains(":")) {
                        // IPv6 address
                        process = Runtime.getRuntime().exec("/system/bin/ping6 -c 8 " + ipAddress); // Ping 4 times
                    } else {
                        // IPv4 address
                        process = Runtime.getRuntime().exec("/system/bin/ping -c 8 " + ipAddress); // Ping 4 times
                    }
                    BufferedReader inputStream = new BufferedReader(new InputStreamReader(process.getInputStream()));

                    String line;
                    while ((line = inputStream.readLine()) != null) {
                        publishProgress(line);
                    }
                    process.waitFor();
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }

            return null;
        }
        @Override
        protected void onProgressUpdate(String... values) {
            String pingResult = values[0];
            tvData.append(pingResult + "\n");
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            tvData.append("\n\n");
            isPinging = false;
        }
    }
}