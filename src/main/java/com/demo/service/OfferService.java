package com.demo.service;

import com.demo.dto.SaveOfferDto;
import com.demo.dto.SearchOfferDto;
import com.demo.model.Offer;
import com.demo.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class OfferService {
    @Autowired
    OfferRepository offerRepository;
    @Autowired
    LocationService locationService;

    public HttpStatus saveOffer(SaveOfferDto offerDto) {
        Offer offer = new Offer();
        offer.setModelName(offerDto.getModelName());
        offer.setCategory(offerDto.getCategory());
        offer.setConditionCategory(offerDto.getConditionCategory());
        offer.setDescription(offerDto.getDescription());
        offer.setPrice(offerDto.getPrice());
        offer.setPhoto(offerDto.getPhoto());
        offer.setContactNumber1(offerDto.getContactNumber1());
        offer.setContactNumber2(offerDto.getContactNumber2());
        offer.setLocation(locationService.findLocationByDistrictAndCity(offerDto.getDistrictId(),offerDto.getCityId()));
        if (offerRepository.save(offer) != null)
            return HttpStatus.OK;
        return HttpStatus.BAD_REQUEST;

    }

    public Offer findById(Long offerId){
        return offerRepository.findById(offerId).orElseThrow(NullPointerException::new);
    }

    public List<Offer> searchOffer(SearchOfferDto filter){
        List<Offer> offers = offerRepository.findAll(new Specification<Offer>() {

            @Override
            public Predicate toPredicate(Root<Offer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {

                List<Predicate> predicates = new ArrayList<>();

                if (filter.getKeyword() != null) {
                    predicates.add(cb.like(root.get("modelName"), filter.getKeyword()+"%"));
                }
                if (filter.getPriceRangeUpper() != 0.0) {
                    predicates.add(cb.lessThanOrEqualTo(root.get("price"),filter.getPriceRangeUpper()));
                }
                if (filter.getPriceRangeLower() != 0.0) {
                    predicates.add(cb.greaterThanOrEqualTo(root.get("price"),filter.getPriceRangeLower()));
                }
                if(filter.getCategory()!= null){
                    predicates.add(cb.equal(root.get("category"),filter.getCategory()));
                }
                if(filter.getConditionCategory()!= null){
                    predicates.add(cb.equal(root.get("conditionCategory"),filter.getConditionCategory()));
                }
                if(filter.getLocationDistrict()!= null){
                    predicates.add(cb.equal(root.get("location"),filter.getLocationDistrict()));
                }
                return cb.and(predicates.toArray(new Predicate[0]));
            }
        });
        return offers;
    }

}
