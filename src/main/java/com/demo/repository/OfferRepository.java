package com.demo.repository;

import com.demo.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer,Long>, JpaSpecificationExecutor {
    @Query(value = "delete from offer where id = ?",nativeQuery = true)
    void deleteById(long id);

    @Query(value = "select * from offer where user_id =?",nativeQuery = true)
    List<Offer> findOffersByUserId(long userId);
}
