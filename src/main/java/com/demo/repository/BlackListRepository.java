package com.demo.repository;

import com.demo.Authentication.BlackList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface BlackListRepository extends JpaRepository<BlackList,Integer> {

    @Query(value = "select * from black_list where black_Listed_Token=?",nativeQuery = true)
    BlackList findByBlackListedToken(String token);

    @Modifying
    @Transactional
    @Query(value = "delete from black_list where username=?",nativeQuery = true)
    void deleteByUsername(String username);
}
