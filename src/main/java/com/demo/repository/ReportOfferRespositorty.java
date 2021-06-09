package com.demo.repository;

import com.demo.model.ReportOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReportOfferRespositorty extends JpaRepository<ReportOffer,Long> {

    @Query(value = "select * from report_Offer where offer_id = ?",nativeQuery = true)
    List<ReportOffer> findByOfferId(long offerId);

    @Query(value = "select * from report_offer where viewed =0",nativeQuery = true)
    List<ReportOffer> findByViewed();
}
