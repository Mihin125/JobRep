package com.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.apache.tomcat.jni.Local;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
public class Offer {
    @Id
    @GeneratedValue
    private long id;

    private String position;

    @ManyToOne
    private  WorkingNature workingNature;

    @ManyToOne
    private Category category;

    @ManyToOne
    private User user;

    private String photo;
    private LocalDateTime postedDate;
    private LocalDateTime expiredDate;

    @ManyToOne
    private OfferStatus offerStatus;

    @ManyToOne
    private District district;

    @JsonIgnore
    @OneToMany(mappedBy = "offer")
    private List<ReportOffer> reports;
}
