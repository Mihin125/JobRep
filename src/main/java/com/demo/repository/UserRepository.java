package com.demo.repository;

import com.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    @Query(value = "select * from user where username =?",nativeQuery = true)
    User findByUsername(String username);

    @Query(value = "select * from user where email = ?",nativeQuery = true)
    User findByEmail(String email);

    @Query(value = "SELECT *\n" +
            "FROM user\n" +
            "INNER JOIN user_roles ON user.id=user_roles.user_id WHERE role_id=1 AND status=1;",nativeQuery = true)
    List<User> findAllUsers();

    @Query(value = "delete from user_reported_offers where user_id=?",nativeQuery = true)
    void deleteFromUserReportedOffers(long id);
}
