package com.demo.repository;
import com.demo.model.RedList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RedListRepository extends JpaRepository<RedList,Long> {

    @Query(name = "select * from red_list where email = ?",nativeQuery = true)
    RedList findByEmail(String email);
}
