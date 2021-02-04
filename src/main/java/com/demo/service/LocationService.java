package com.demo.service;

import com.demo.model.City;
import com.demo.model.District;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {
//    @Autowired
//    LocationRepository locationRepository;
    @Autowired
    DistrictService districtService;
    @Autowired
    CityService cityService;
    
    public District findDistrictById(long id){
        return districtService.findDistrictById(id);
    }
    public List<City> findCitiesByDistrict(long districtId){
        return cityService.findCitiesByDistrict(districtId);
    }
}
