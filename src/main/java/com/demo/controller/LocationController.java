package com.demo.controller;

import com.demo.model.District;
import com.demo.model.Location;
import com.demo.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/94mart/location")
public class LocationController {
    @Autowired
    LocationService locationService;

    @GetMapping("{locationId}")
    public Location findLocationById(@PathVariable long locationId){
        return locationService.findLocationById(locationId);
    }

    @GetMapping("/district/{districtId}")
    public District fidnDistrictById(@PathVariable long districtId){
        return locationService.findDistrictById(districtId);
    }
}
