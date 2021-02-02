package com.demo.service;

import com.demo.model.City;
import com.demo.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {
    @Autowired
    CityRepository cityRepository;

    public List<City> findCitiesByDistrict(long districtId){
        return cityRepository.findByDistrictId(districtId);
    }

    public City findCityByCityName(String cityName){
        return cityRepository.findCityByCityName(cityName);
    }

}
