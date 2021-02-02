package com.demo.repository;

import com.demo.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CityRepository extends JpaRepository<City,Long> {

    @Query(value = "select * from city where district_id =?",nativeQuery = true)
    List<City> findByDistrictId(long districtId);

    @Query(value = "select * from city where city = ?",nativeQuery = true)
    City findCityByCityName(String cityName);
}
