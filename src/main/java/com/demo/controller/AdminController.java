package com.demo.controller;

import com.demo.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/94mart/admin")
public class AdminController {

    @Autowired
    OfferService offerService;

    @PutMapping("accept-offer/{offerId}")
    public void acceptOffer(@PathVariable long offerId){
        offerService.acceptOffer(offerId);
    }
    @PutMapping("reject-offer/{offerId}")
    public void rejectOffer(@PathVariable long offerId){
        offerService.rejectOffer(offerId);
    }
    @DeleteMapping("delete-Offer/{offerId}")
    public void deleteOffer(@PathVariable long offerId){
        offerService.deleteOffer(offerId);
    }

}
