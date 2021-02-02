package com.demo.repository;

import com.demo.Authentication.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRoleRepository extends JpaRepository<UserRole,Long> {
    @Query(value ="select * from User_role where name =?",nativeQuery = true)
    public UserRole findUserRoleByName(String name);
}
