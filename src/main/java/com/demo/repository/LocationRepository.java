package com.demo.repository;

import com.demo.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LocationRepository extends JpaRepository<Location,Long> {
    @Query(value = "select * from location where city_id = ?1 and district_id =?2",nativeQuery = true)
    Location findByDistrictaAndCity(long distrcitId,long cityId);
}
