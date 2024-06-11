package com.example.heritagemicroservice.service;

import java.util.HashMap;
// Interface that defines methods for obtaining location information based on latitude and longitude coordinates.
public interface LocationService {

    // A HashMap containing location information, where keys represent different aspects of location.
    HashMap<String, String> getLocationInfo(double lat, double lon);

}
