package com.example.heritagemicroservice.helpers;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;

// Represents the location information retrieved from OpenStreetMap for cultural heritage sites.
public class OsmCultureHeritageLocation {
    @JsonProperty("display_name")
    private String displayName;

    @JsonProperty("address")
    private HashMap<String, String> address;

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public HashMap<String, String> getAddress() {
        return address;
    }

    public void setAddress(HashMap<String, String> address) {
        this.address = address;
    }
}
