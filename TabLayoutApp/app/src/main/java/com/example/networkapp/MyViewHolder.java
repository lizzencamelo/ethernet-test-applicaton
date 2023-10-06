package com.example.networkapp;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.networkapp.R;

public class MyViewHolder extends RecyclerView.ViewHolder {
    TextView  nameView, hardwareAddrView, iaddrView, dnsServerView, ownerIDView, bandwidthView, capsView, displayView, stateView, availableView, roamingView, transportView;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        nameView = itemView.findViewById(R.id.name);
        displayView =  itemView.findViewById(R.id.display);
        stateView =  itemView.findViewById(R.id.state);
        availableView =  itemView.findViewById(R.id.available);
        roamingView =  itemView.findViewById(R.id.roaming);
        hardwareAddrView = itemView.findViewById(R.id.hardwareAddr);
        iaddrView =  itemView.findViewById(R.id.iaddr);
        dnsServerView = itemView.findViewById(R.id.dnsServer);
        ownerIDView =  itemView.findViewById(R.id.ownerID);
        bandwidthView =  itemView.findViewById(R.id.bandwidth);
        transportView =  itemView.findViewById(R.id.transportType);
        capsView = itemView.findViewById(R.id.caps);
    }
}
