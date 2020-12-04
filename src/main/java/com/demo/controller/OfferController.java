package com.demo.controller;

import com.demo.dto.SaveOfferDto;
import com.demo.dto.SearchOfferDto;
import com.demo.model.Offer;
import com.demo.service.OfferService;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/94mart/offer")
public class OfferController {
    @Autowired
    OfferService offerService;

    @PostMapping()
    public HttpStatus saveOffer(@RequestBody SaveOfferDto offerDto){
        return offerService.saveOffer(offerDto);
    }

    @GetMapping("/{id}")
    public Offer findOfferById(@PathVariable long id){
        return offerService.findById(id);
    }

    @PostMapping("/search")
    public List<Offer> searchOffer(@RequestBody SearchOfferDto filter){
        return offerService.searchOffer(filter);
    }

    @PutMapping("/update/{offerId}")
    public HttpStatus updateOffer(@PathVariable long offerId,@RequestBody SaveOfferDto updateOfferDto){
    return offerService.updateOffer(offerId,updateOfferDto);
    }

    @DeleteMapping("delete/{offerId}")
    public void deleteOffer(@PathVariable long offerId){
        offerService.deleteOffer(offerId);
    }

    @GetMapping()
    public List<Offer> getAllOffers(){
        return offerService.getAllOffers();
    }

    @PutMapping("/admin/reportoffer/{offerId}")
    public void reportOffer(@PathVariable long offerId){
        offerService.reportOffer(offerId);
    }

}
