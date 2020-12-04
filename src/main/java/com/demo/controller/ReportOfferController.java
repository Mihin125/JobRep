package com.demo.controller;

import com.demo.dto.ReportOfferDto;
import com.demo.model.ReportOffer;
import com.demo.service.ReportOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/94mart/report-offer")
public class ReportOfferController {
    @Autowired
    ReportOfferService reportOfferService;

    @GetMapping("/{offerId}")
    public List<ReportOffer> getReportsByOfferId(@PathVariable long offerId){
        return reportOfferService.getReportsByOfferId(offerId);
    }
    @PostMapping
    public void saveReportOffer(@RequestBody ReportOfferDto reportOfferDto){
        reportOfferService.saveReport(reportOfferDto);
    }
    @PutMapping("/markViewed/{reportOfferId}")
    public void markAsViewed(@PathVariable long reportOfferId){
        reportOfferService.markAsViewed(reportOfferId);
    }

    @GetMapping("/not-viewed")
    public List<ReportOffer> getAllNotViewed(){
        return reportOfferService.getAllUnviewed();
    }

    @GetMapping("/not-viewed-number")
    public int getNoOfNotViewed(){
        return reportOfferService.getNoOfUnviewed();
    }

}

