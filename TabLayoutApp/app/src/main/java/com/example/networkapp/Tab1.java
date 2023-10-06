package com.example.networkapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.LinkAddress;
import android.net.LinkProperties;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.networkapp.R;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

public class Tab1 extends Fragment {
    private static Context context;
    private List<Item> items = new ArrayList<Item>();
    private Button scanBtn;
    private RecyclerView recyclerView;
    private MyAdaptor adaptor;

    @SuppressLint({"NewApi", "MissingInflatedId"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab1, container, false);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        scanBtn = view.findViewById(R.id.scanBtn);
        recyclerView = view.findViewById(R.id.recyclerview);

        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adaptor = new MyAdaptor(new ArrayList<>());
        recyclerView.setAdapter(adaptor);

        scanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scanNetworkInterfaces();
            }
        });
        //scanBtn.setOnClickListener(this);

        context = getActivity().getApplicationContext();
        return view;

    }

    /*@RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    public void onClick(View view) {

                scanNetworkInterfaces();

    }
*/

    @RequiresApi(api = Build.VERSION_CODES.R)
    protected void scanNetworkInterfaces() {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm == null) {
            return;
        }

        Network[] networks = cm.getAllNetworks();
        Log.i("INFO", String.valueOf(networks.length));

        for (int i = 0; i < networks.length; i++) {
            String intfaceName = "",  hardwareAddr = "", iaddr = "", dns="", ownerID = "", bandwidth = "", display="", available="", state="", roaming="", transportType="", caps = "";

            // Network Classes
            NetworkCapabilities netCap = cm.getNetworkCapabilities(networks[i]);
            LinkProperties prop = cm.getLinkProperties(networks[i]);
            NetworkInfo networkInfo = cm.getNetworkInfo(networks[i]);

            // NetworkInfo Details
            if (networkInfo != null) {
                display = networkInfo.getTypeName();
                state = networkInfo.isConnected() ? "Connected\n" : "Disconnected\n";
                available =  networkInfo.isAvailable() ? "Available\n" : "Not Available\n";
                roaming = networkInfo.isRoaming() ? "Roaming\n" : "Not Roaming\n";
            }


            intfaceName = prop.getInterfaceName();

            // Hardware Addresses
            hardwareAddr = (netCap.getTransportInfo() != null) ? "" + netCap.getTransportInfo() + "\n": "N/A\n";

            // IP Addresses
            List<LinkAddress> ipAddresses = prop.getLinkAddresses();
            for (LinkAddress ipAddress : ipAddresses) {
                iaddr += getIpAddresses(prop);
            }
            iaddr += "\n";

            // DNS Servers
            List<InetAddress> dnsServers = prop.getDnsServers();
            for (InetAddress dnsServer : dnsServers) {
                dns += dnsServer.getHostAddress() + "\n";
            }

            ownerID = "" + netCap.getOwnerUid() + "\n";
            bandwidth = "(Upstream/Downstream) " + netCap.getLinkUpstreamBandwidthKbps() + "Kbps/" + netCap.getLinkDownstreamBandwidthKbps() + "Kbps\n";
            transportType = getTransportType(netCap) + "\n";
            caps = "VPN: " + netCap.hasTransport(NetworkCapabilities.TRANSPORT_VPN) + "\n" +
                    "NOT_VPN:" + netCap.hasCapability(NetworkCapabilities.NET_CAPABILITY_NOT_VPN) + "\n" +
                    "NET_CAPABILITY_MMS: " + netCap.hasCapability(NetworkCapabilities.NET_CAPABILITY_MMS) + "\n" + // Indicates this is a network that has the ability to reach the carrier's MMSC for sending and receiving MMS messages.
                    "NET_CAPABILITY_SUPL: " + netCap.hasCapability(NetworkCapabilities.NET_CAPABILITY_SUPL) + "\n" + //Indicates this is a network that has the ability to reach the carrier's SUPL server, used to retrieve GPS information.
                    "NET_CAPABILITY_DUN: " + netCap.hasCapability(NetworkCapabilities.NET_CAPABILITY_DUN) + "\n" +
                    "NET_CAPABILITY_FOTA: " + netCap.hasCapability(NetworkCapabilities.NET_CAPABILITY_FOTA) + "\n" +
                    "NET_CAPABILITY_IMS: " + netCap.hasCapability(NetworkCapabilities.NET_CAPABILITY_IMS) + "\n" +
                    "NET_CAPABILITY_CBS: " + netCap.hasCapability(NetworkCapabilities.NET_CAPABILITY_CBS) + "\n" +
                    "NET_CAPABILITY_WIFI_P2P: " + netCap.hasCapability(NetworkCapabilities.NET_CAPABILITY_WIFI_P2P) + "\n" +
                    "WIFI AWARE: " + netCap.hasTransport(NetworkCapabilities.TRANSPORT_WIFI_AWARE) + "\n" +
                    "NET_CAPABILITY_IA: " + netCap.hasCapability(NetworkCapabilities.NET_CAPABILITY_IA) + "\n" +
                    "NET_CAPABILITY_RCS: " + netCap.hasCapability(NetworkCapabilities.NET_CAPABILITY_RCS) + "\n" +
                    "NET_CAPABILITY_XCAP: " + netCap.hasCapability(NetworkCapabilities.NET_CAPABILITY_XCAP) + "\n" +
                    "NET_CAPABILITY_EIMS: " + netCap.hasCapability(NetworkCapabilities.NET_CAPABILITY_EIMS) + "\n" +
                    "NET_CAPABILITY_NOT_METERED: " + netCap.hasCapability(NetworkCapabilities.NET_CAPABILITY_NOT_METERED) + "\n" +
                    "NET_CAPABILITY_INTERNET: " + netCap.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) + "\n" +
                    "NET_CAPABILITY_NOT_RESTRICTED: " + netCap.hasCapability(NetworkCapabilities.NET_CAPABILITY_NOT_RESTRICTED) + "\n" +
                    "NET_CAPABILITY_TRUSTED: " + netCap.hasCapability(NetworkCapabilities.NET_CAPABILITY_TRUSTED) + "\n" +
                    "NET_CAPABILITY_VALIDATED: " + netCap.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED) + "\n" +
                    "NET_CAPABILITY_NOT_ROAMING: " + netCap.hasCapability(NetworkCapabilities.NET_CAPABILITY_NOT_ROAMING) + "\n\n";

            items.add(new Item(intfaceName, display, state, available, roaming, hardwareAddr, iaddr, dns, ownerID, bandwidth, transportType,caps));
            adaptor.setNetworkList(items);
        }
    }

    private String getIpAddresses(LinkProperties linkProperties) {
        if (linkProperties != null) {
            List<InetAddress> addresses = new ArrayList<>();
            for (LinkAddress linkAddress : linkProperties.getLinkAddresses()) {
                addresses.add(linkAddress.getAddress());
            }
            if (!addresses.isEmpty()) {
                StringBuilder ipAddresses = new StringBuilder();
                for (InetAddress address : addresses) {
                    ipAddresses.append(address.getHostAddress()).append(", ");
                }
                return removeTrailingComma(ipAddresses.toString());
            }
        }
        return "N/A";
    }

    private String removeTrailingComma(String input) {
        if (input.endsWith(", ")) {
            return input.substring(0, input.length() - 2);
        }
        return input;
    }

    private String getTransportType(NetworkCapabilities capabilities) {
        if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
            return "Wi-Fi";
        } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
            return "Cellular";
        } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
            return "Ethernet";
        } else {
            return "Unknown";
        }
    }
}
