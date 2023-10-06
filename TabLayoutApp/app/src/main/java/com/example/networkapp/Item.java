package com.example.networkapp;

public class Item {

    String  name, display, state, available, roaming, hardwareAddr, iaddr, dnsServer, ownerID, bandwidth, caps, transportType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHardwareAddr() {
        return hardwareAddr;
    }

    public void setHardwareAddr(String hardwareAddr) {
        this.hardwareAddr = hardwareAddr;
    }

    public String getIaddr() {
        return iaddr;
    }

    public void setIaddr(String iaddr) {
        this.iaddr = iaddr;
    }

    public String getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(String ownerID) {
        this.ownerID = ownerID;
    }

    public String getBandwidth() {
        return bandwidth;
    }

    public void setBandwidth(String bandwidth) {
        this.bandwidth = bandwidth;
    }

    public String getCaps() {
        return caps;
    }

    public void setCaps(String caps) {
        this.caps = caps;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public String getRoaming() {
        return roaming;
    }

    public void setRoaming(String roaming) {
        this.roaming = roaming;
    }

    public String getTransportType() {
        return transportType;
    }

    public void setTransportType(String transportType) {
        this.transportType = transportType;
    }

    public String getDnsServer() {
        return dnsServer;
    }

    public void setDnsServer(String dnsServer) {
        this.dnsServer = dnsServer;
    }

    public Item(String name, String display, String state, String available, String roaming, String hardwareAddr, String  iaddr, String dnsServer, String  ownerID, String  bandwidth, String transportType, String caps) {
        this.name = name;
        this.display = display;
        this.state = state;
        this.available = available;
        this.roaming = roaming;
        this.hardwareAddr = hardwareAddr;
        this.iaddr = iaddr;
        this.dnsServer = dnsServer;
        this.ownerID = ownerID;
        this.bandwidth = bandwidth;
        this.transportType = transportType;
        this.caps = caps;
    }

}
