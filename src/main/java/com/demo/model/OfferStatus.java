package com.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@Setter
public class OfferStatus {

    @Id
    @GeneratedValue
    private Integer id;

    private String offerStatus;

    @OneToMany(mappedBy = "offerStatus")
    private List<Offer> offers;

    public OfferStatus(Integer id, String offerStatus) {
        this.id = id;
        this.offerStatus = offerStatus;
    }

    public OfferStatus() {

    }

}
