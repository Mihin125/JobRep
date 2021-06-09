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

    @Query(value = "SELECT * FROM offer WHERE user_id =?1 AND offer_status_id=?2",nativeQuery = true)
    List<Offer> findOffersByUserId(long userId, long offerStatusId);

    @Query(value = "SELECT * FROM offer WHERE offer_status_id=?1",nativeQuery = true)
    List<Offer> findOffersByOfferStatus(long offerStatusId);

    @Query(value = "DELETE FROM offer WHERE user_id =?1 AND offer_status_id=?2",nativeQuery = true)
    List<Offer> deleteOffersByUserId(long userId, long offerStatusId);

    @Query(value = "SELECT * FROM offer WHERE id=?1", nativeQuery = true)
    Offer findOfferById(long offerId);

}
