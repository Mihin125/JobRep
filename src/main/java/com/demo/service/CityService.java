package com.demo.service;

import com.demo.dto.CityDto;
import com.demo.model.City;
import com.demo.model.District;
import com.demo.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {
    @Autowired
    CityRepository cityRepository;
    @Autowired
    DistrictService districtService;

    public List<City> findCitiesByDistrict(long districtId){
        return cityRepository.findByDistrictId(districtId);
    }

    public City findCityByCityName(String cityName){
        return cityRepository.findCityByCityName(cityName);
    }

    public City findCityById(long id){
        return cityRepository.findById(id).orElseThrow(NullPointerException::new);
    }

    public void saveCity(CityDto cityDto){
        District district=districtService.findDistrictByDistrictName(cityDto.getDistrict());
        City city = new City();
        city.setCity(cityDto.getCity());
        city.setDistrict(district);
        cityRepository.save(city);
    }
}
