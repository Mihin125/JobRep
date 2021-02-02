package com.demo.controller;

import com.demo.model.City;
import com.demo.model.District;
import com.demo.model.Location;
import com.demo.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("94mart/location")
public class LocationController {
    @Autowired
    LocationService locationService;

    @GetMapping("{locationId}")
    public Location findLocationById(@PathVariable long locationId){
        return locationService.findLocationById(locationId);
    }
    @GetMapping("/district/{districtId}")
    public District findDistrictById(@PathVariable long districtId){
        return locationService.findDistrictById(districtId);
    }
    @GetMapping("/cities/{districtId}")
    public List<City> findCitiesByDistrict(@PathVariable long districtId){
        return locationService.findCitiesByDistrict(districtId);
    }
}
