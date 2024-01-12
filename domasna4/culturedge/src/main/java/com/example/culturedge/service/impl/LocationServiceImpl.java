package com.example.culturedge.service.impl;

import com.example.culturedge.helpers.OsmCultureHeritageLocation;
import com.example.culturedge.service.LocationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@Service
public class LocationServiceImpl implements LocationService {
    @Value("${https://nominatim.openstreetmap.org}")
    private String openStreetMapApiUrl;
    private final RestTemplate restTemplate;

    public LocationServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public HashMap<String, String> getLocationInfo(double lat, double lon) {
        String url = String.format("https:%s/reverse?format=json&lat=%s&lon=%s", openStreetMapApiUrl, lat, lon);
        OsmCultureHeritageLocation response = restTemplate.getForObject(url, OsmCultureHeritageLocation.class);

        HashMap<String, String> result = new HashMap<>();
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
            result.put("address", "");
            result.put("city", "");
        }
        return result;
    }
}
