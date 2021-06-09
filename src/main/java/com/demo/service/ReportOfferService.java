package com.demo.service;

import com.demo.dto.ReportOfferDto;
import com.demo.model.ReportOffer;
import com.demo.repository.ReportOfferRespositorty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportOfferService {
    @Autowired
    ReportOfferRespositorty reportOfferRespositorty;
    @Autowired
    UserService userService;
    @Autowired
    OfferService offerService;

    public void saveReport(ReportOfferDto reportOfferDto){
        ReportOffer reportOffer = new ReportOffer();
        reportOffer.setDescription(reportOfferDto.getDescription());
        reportOffer.setReportingUser(userService.findById(reportOfferDto.getReportingUserId()));
        reportOffer.setOffer(offerService.findById(reportOfferDto.getOfferId()));
        reportOffer.setDateTime(reportOffer.getDateTime());

        reportOfferRespositorty.save(reportOffer);
    }
    public List<ReportOffer> getReportsByOfferId(long offerId){
        return reportOfferRespositorty.findByOfferId(offerId);
    }
    public void markAsViewed(long id){
        ReportOffer reportOffer = findById(id);
        reportOffer.setViewed(true);
        reportOfferRespositorty.save(reportOffer);
    }

    public ReportOffer findById(long id){
        return reportOfferRespositorty.findById(id).orElseThrow(NullPointerException::new);
    }
    public List<ReportOffer> getAllUnviewed(){
        return reportOfferRespositorty.findByViewed();

    }
    public int getNoOfUnviewed(){
        return getAllUnviewed().size();
    }

}
