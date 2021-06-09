package com.demo.model;

/*public enum Category {
    APPLICATION_DEVELOPMENT, ADMINISTRATION, ANALYSING_AND_MANAGEMENT,NETWORK_SYSTEMS,
    MEDIA_AND_COMMUNICATION, DIGITAL_MARKETING,GRAPHIC_DESIGNING, COMPUTER_OPERATING_AND_HARDWARE
}*/

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Category {

    @Id
    @GeneratedValue
    private Integer id;

    private String category;

    @JsonIgnore
    @OneToMany(mappedBy = "category")
    private List<Offer> offers;

}
