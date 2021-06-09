package com.demo.controller;

import com.demo.dto.OfferResponseDto;
import com.demo.dto.SaveOfferDto;
import com.demo.dto.SearchOfferDto;
import com.demo.model.Offer;
import com.demo.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/career/offer")
public class OfferController {
    @Autowired
    OfferService offerService;

    @PostMapping("/user/add")
    public ResponseEntity<?> saveOffer(@RequestBody SaveOfferDto offerDto) {
        offerService.saveOffer(offerDto);
        return ResponseEntity.ok("OFFER_SAVED");
    }

    @GetMapping("/{id}")
    public Offer findOfferById(@PathVariable long id) {
        return offerService.findById(id);
    }

    @PostMapping("/search")
    public List<Offer> searchOffer(@RequestBody SearchOfferDto filter) {
        return offerService.searchOffer(filter);
    }

    @GetMapping("/user/get")
    public ResponseEntity<?> getOffersByUserId(@RequestParam long userId, @RequestParam String offerStatus) {

        List<OfferResponseDto> offerResponseDtoList = offerService.getOffersByUserId(userId, offerStatus);
        return ResponseEntity.status(HttpStatus.OK)
                .body(offerResponseDtoList);

    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllOffers(@RequestParam String offerStatus) {

        return ResponseEntity.ok().body(offerService.getOffersByOfferStatus(offerStatus));

    }

    @DeleteMapping("/user/delete")
    public ResponseEntity<?> deleteOffer(@RequestParam long offerId) {
        offerService.deleteOfferById(offerId);
        return ResponseEntity.ok("OFFER_DELETED");
    }

    @GetMapping()
    public List<Offer> getAllOffers() {
        return offerService.getAllOffers();
    }

    @PutMapping("/report-offer/{offerId}")
    public void reportOffer(@PathVariable long offerId) {
        offerService.reportOffer(offerId);
    }

    @DeleteMapping("/expire-offer")
    public HttpStatus expireOffer(){return offerService.expireOffer();}
}
