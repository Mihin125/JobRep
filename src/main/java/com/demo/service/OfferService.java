package com.demo.service;

import com.demo.Exceptions.OrderNotFoundException;
import com.demo.Exceptions.UserNotFoundException;
import com.demo.dto.OfferResponseDto;
import com.demo.dto.SaveOfferDto;
import com.demo.dto.SearchOfferDto;
import com.demo.model.Offer;
import com.demo.model.OfferStatus;
import com.demo.repository.OfferRepository;
import com.demo.repository.OfferStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfferService {
    @Autowired
    OfferRepository offerRepository;
    @Autowired
    CategoryService categoryService;
    @Autowired
    WorkingNatureService workingNatureService;
    @Autowired
    UserService userService;
    @Autowired
    RedListService redListService;
    @Autowired
    DistrictService districtService;
    @Autowired
    OfferStatusRepository offerStatusRepository;

    @Transactional(rollbackFor = UserNotFoundException.class)
    public void saveOffer(SaveOfferDto offerDto) {
        Offer offer = new Offer();
        offer.setCategory(categoryService.findCategoryByName(offerDto.getCategory()));
        offer.setWorkingNature(workingNatureService.findWorkingNatureByName(offerDto.getWorkingNature()));
        offer.setPosition(offerDto.getPositions());
        offer.setPhoto(offerDto.getPhoto());
        offer.setDistrict(districtService.findDistrictByDistrictName(offerDto.getDistrict()));
        offer.setUser(userService.findById(offerDto.getUser()));
        offer.setPostedDate(offerDto.getPostedDate());
        offer.setExpiredDate(offerDto.getExpiredDate());
        offer.setOfferStatus(new OfferStatus(1, "PENDING"));
        offerRepository.save(offer);
    }

    public Offer findById(Long offerId){
        Offer offer = offerRepository.findOfferById(offerId);
        if (offer == null) {
            throw new OrderNotFoundException("No Such Offer");
        }
        return offer;
    }



    public List<Offer> searchOffer(SearchOfferDto filter){
        List<Offer> offers = offerRepository.findAll(new Specification<Offer>() {

            @Override
            public Predicate toPredicate(Root<Offer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {

                List<Predicate> predicates = new ArrayList<>();

                if (filter.getKeyword() != null) {
                    predicates.add(cb.like(root.get("position"), filter.getKeyword()+"%"));
                }
                if (filter.getCompanyName() != null) {
                    predicates.add(cb.like(root.get("companyName"), filter.getKeyword()+"%"));
                }
                if(filter.getCategory()!= null){
                    predicates.add(cb.equal(root.get("category"),filter.getCategory()));
                }
                if(filter.getWorkingNature()!= null){
                    predicates.add(cb.equal(root.get("workingNature"),filter.getWorkingNature()));
                }
                if(filter.getDistrict()!= null){
                    predicates.add(cb.equal(root.get("district"),districtService.findDistrictByDistrictName(filter.getDistrict())));
                }
                return cb.and(predicates.toArray(new Predicate[0]));
            }
        });
        return null;
        // return offers.stream().sorted(Comparator.comparing(Offer::getPostedDate).reversed()).collect(Collectors.toList());
    }

    @Transactional
    public void deleteOfferById(long offerId){
        offerRepository.delete(findById(offerId));
    }

    @Transactional
    public void acceptOffer(long offerId){
        Offer offer = findById(offerId);
        offer.setOfferStatus(new OfferStatus(4, "ACCEPTED"));
        offerRepository.save(offer);
    }

    @Transactional
    public void rejectOffer(long offerId){
        Offer offer = findById(offerId);
        offer.setOfferStatus(new OfferStatus(3, "REJECTED"));
        offerRepository.save(offer);
    }

    public List<Offer> getAllOffers(){
        return offerRepository.findAll();
    }

    public void reportOffer(long offerId){
        Offer offer = findById(offerId);
        /*if(offer.getOfferStatus()!=OfferStatus.REPORTED) {
            offer.setOfferStatus(OfferStatus.REPORTED);
            List<Offer> reports = offer.getUser().getReportedOffers();
            reports.add(offer);
            offer.getUser().setReportedOffers(reports);
            if (reports.size()==3)redListService.save(new RedList(offer.getUser(), LocalDateTime.now()));
            offerRepository.save(offer);
        }*/
    }

    public List<OfferResponseDto> getOffersByUserId(long userId, String offerStatus) {

        long offerStatusId = offerStatusRepository.findOfferStatusByName(offerStatus).getId();
        List<Offer> offersByUserId = offerRepository.findOffersByUserId(userId, offerStatusId);
        String companyName = userService.findById(userId).getCompanyName();
        return getResponseOffersList(offersByUserId);
    }

    public List<OfferResponseDto> getResponseOffersList(List<Offer> offersList) {

        List<OfferResponseDto> offerResponseDtoList = new ArrayList<>();
        offersList.forEach((offer) -> {
            OfferResponseDto offerResponseDto = new OfferResponseDto(offer.getUser().getCompanyName(), offer.getWorkingNature().getWorkingNature(), offer.getDistrict().getDistrictName(), offer.getCategory().getCategory(), offer.getPosition(), offer.getPostedDate()
                    , offer.getExpiredDate(), offer.getId());
            offerResponseDtoList.add(offerResponseDto);
        });

        return offerResponseDtoList;

    }

    public List<OfferResponseDto> getOffersByOfferStatus(String offerStatus) {

        long offerStatusId = offerStatusRepository.findOfferStatusByName(offerStatus).getId();
        List<Offer> offersByOfferStatus = offerRepository.findOffersByOfferStatus(offerStatusId);
        return getResponseOffersList(offersByOfferStatus);

    }

    public HttpStatus expireOffer(){
        List<Offer> offers = getAllOffers();
        for (Offer offer: offers) {
            if (offer.getExpiredDate().isBefore(LocalDateTime.now())){
                deleteOfferById(offer.getId());
                continue;
            }
            Duration duration = Duration.between( offer.getPostedDate(), LocalDateTime.now());
            if (duration.toDays()>60) deleteOfferById(offer.getId());
        }
        return HttpStatus.OK;
    }

}
