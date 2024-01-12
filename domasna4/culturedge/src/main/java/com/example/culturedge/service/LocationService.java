package com.example.culturedge.service;

import java.util.HashMap;

public interface LocationService {

    HashMap<String, String> getLocationInfo(double lat, double lon);

}
