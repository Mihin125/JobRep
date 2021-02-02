package com.demo.controller;

import com.demo.dto.CityDto;
import com.demo.dto.DistrictDto;
import com.demo.model.City;
import com.demo.model.District;
import com.demo.service.CityService;
import com.demo.service.DistrictService;
import com.demo.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("94mart/location")
public class LocationController {
    @Autowired
    LocationService locationService;
    @Autowired
    DistrictService districtService;
    @Autowired
    CityService cityService;

//    @GetMapping("{locationId}")
//    public Location findLocationById(@PathVariable long locationId){
//        return locationService.findLocationById(locationId);
//    }
    @GetMapping("/district/{districtId}")
    public District findDistrictById(@PathVariable long districtId){
        return locationService.findDistrictById(districtId);
    }
    @GetMapping("/cities/{districtId}")
    public List<City> findCitiesByDistrict(@PathVariable long districtId){
        return locationService.findCitiesByDistrict(districtId);
    }
    @PostMapping("/admin/district/save")
    public void saveDistrict(@RequestBody DistrictDto districtDto){
        districtService.saveDistrict(districtDto);
    }
    @PostMapping("/admin/city/save")
    public void saveCity(@RequestBody CityDto citydto){
        cityService.saveCity(citydto);
    }
}
