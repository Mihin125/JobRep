package com.demo.service;

import com.demo.model.OfferStatus;
import com.demo.repository.OfferStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OfferStatusService {

    @Autowired
    OfferStatusRepository offerStatusRepository;

    public OfferStatus findOfferStatusByName(String offerStatus) {
        return offerStatusRepository.findOfferStatusByName(offerStatus);
    }

}
