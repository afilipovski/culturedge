package com.example.heritagemicroservice.helpers;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;

public class OsmCultureHeritageLocation {
    @JsonProperty("display_name")
    private String displayName;

    @JsonProperty("address")
    private HashMap<String, String> address;

    public String getDisplayName() {
        return displayName;
    }

    public HashMap<String, String> getAddress() {
        return address;
    }
}
