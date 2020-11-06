package com.demo.service;

import com.demo.model.Location;
import com.demo.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationService {
    @Autowired
    LocationRepository locationRepository;

    public void saveLocation(Location location){
        locationRepository.save(location);
    }

}
