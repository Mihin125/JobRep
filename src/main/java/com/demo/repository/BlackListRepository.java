package com.demo.repository;

import com.demo.model.BlackList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BlackListRepository extends JpaRepository<BlackList,Long> {

    @Query(name = "select * from black_list where email = ?",nativeQuery = true)
    BlackList findByEmail(String email);
}
