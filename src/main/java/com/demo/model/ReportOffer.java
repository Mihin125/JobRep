package com.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class ReportOffer {
    @Id
    @GeneratedValue
    private long id;
    private String description;
    private LocalDateTime dateTime;
    @JsonIgnore
    @ManyToOne
    private Offer offer;
    @OneToOne
    private User reportingUser;
    private boolean viewed;
}
