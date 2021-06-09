package com.demo.repository;

import com.demo.model.WorkingNature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkingNatureRepository extends JpaRepository<WorkingNature, Long> {

    @Query(value = "select * from  working_nature where working_nature=?1",nativeQuery = true)
    WorkingNature findWorkingNatureByName(String workingNature);
}
