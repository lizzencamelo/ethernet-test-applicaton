package com.example.networkapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.networkapp.R;

import java.util.ArrayList;
import java.util.List;

public class MyAdaptor extends RecyclerView.Adapter<MyViewHolder> {

    Context context;
    List<Item> items;

    public MyAdaptor(Context context, List<Item> items) {
        this.context = context;
        this.items = items;
    }
    public <E> MyAdaptor(ArrayList<E> es) {
    }

    public void setNetworkList(List<Item> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        return new MyViewHolder(view);
        //return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //Binding data to views
        holder.nameView.setText(items.get(position).getName());
        holder.displayView.setText(items.get(position).getDisplay());
        holder.stateView.setText(items.get(position).getState());
        holder.availableView.setText(items.get(position).getAvailable());
        holder.roamingView.setText(items.get(position).getRoaming());
        holder.hardwareAddrView.setText(items.get(position).getHardwareAddr());
        holder.iaddrView.setText(items.get(position).getIaddr());
        holder.dnsServerView.setText(items.get(position).getDnsServer());
        holder.ownerIDView.setText(items.get(position).getOwnerID());
        holder.bandwidthView.setText(items.get(position).getBandwidth());
        holder.transportView.setText(items.get(position).getTransportType());
        holder.capsView.setText(items.get(position).getCaps());
    }

    @Override
    public int getItemCount() {
        return items != null ? items.size() : 0;
    }
}
