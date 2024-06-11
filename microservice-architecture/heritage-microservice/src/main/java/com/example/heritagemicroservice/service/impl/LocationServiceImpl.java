package com.example.heritagemicroservice.service.impl;

import com.example.heritagemicroservice.helpers.OsmCultureHeritageLocation;
import com.example.heritagemicroservice.service.LocationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

// Implementation of the LocationService interface for obtaining location information.
@Service
public class LocationServiceImpl implements LocationService {

    // Base URL for OpenStreetMap API, retrieved from application properties.
    @Value("${https://nominatim.openstreetmap.org}")
    private String openStreetMapApiUrl;
    private final RestTemplate restTemplate;

    public LocationServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // Method to get location information based on latitude and longitude coordinates.
    @Override
    public HashMap<String, String> getLocationInfo(double lat, double lon) {
        String url = String.format("https:%s/reverse?format=json&lat=%s&lon=%s", openStreetMapApiUrl, lat, lon);

        // Make a GET request to the API and deserialize the response into OsmCultureHeritageLocation object.
        OsmCultureHeritageLocation response = restTemplate.getForObject(url, OsmCultureHeritageLocation.class);

        HashMap<String, String> result = new HashMap<>();

        // Extract relevant information from the API response and populate the result HashMap.
        if (response != null && response.getDisplayName() != null && response.getAddress() != null) {
            result.put("address", response.getDisplayName());
            if (response.getAddress().get("city") != null) {
                result.put("city", response.getAddress().get("city"));
            } else if (response.getAddress().get("village") != null) {
                result.put("city", response.getAddress().get("village"));
            }
            else {
                result.put("city", response.getAddress().get("town"));
            }
        } else {
            // If no relevant information found, set empty values.
            result.put("address", "");
            result.put("city", "");
        }
        return result;
    }
}
