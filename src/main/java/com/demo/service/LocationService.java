package com.demo.service;

import com.demo.model.City;
import com.demo.model.District;
import com.demo.model.Location;
import com.demo.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {
    @Autowired
    LocationRepository locationRepository;
    @Autowired
    DistrictService districtService;
    @Autowired
    CityService cityService;

    public void saveLocation(Location location){
        locationRepository.save(location);
    }

    public Location findLocationByDistrictAndCity(long districtId,long citId){
        return locationRepository.findByDistrictaAndCity(districtId,citId);
    }

    public Location findLocationById(long id){
        return locationRepository.findById(id).orElseThrow(NullPointerException::new);
    }

    public District findDistrictById(long id){
        return districtService.findDistrictById(id);
    }
    public List<City> findCitiesByDistrict(long districtId){
        return cityService.findCitiesByDistrict(districtId);
    }
}
