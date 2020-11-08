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
@RequestMapping("imania/api/offer")
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
}
