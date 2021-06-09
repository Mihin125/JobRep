package com.demo.repository;

import com.demo.model.OfferStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferStatusRepository extends JpaRepository<OfferStatus, Long> {

    @Query(value = "select * from  offer_status where offer_status=?1",nativeQuery = true)
    OfferStatus findOfferStatusByName(String offerStatus);

}
