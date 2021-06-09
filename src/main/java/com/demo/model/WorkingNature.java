package com.demo.model;

/*public enum WorkingNature {
    FULL_TIME, PART_TIME,TEMPORARY,INTERNSHIP
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
public class WorkingNature {

    @Id
    @GeneratedValue
    private Integer id;

    private String workingNature;

    @JsonIgnore
    @OneToMany(mappedBy = "workingNature")
    private List<Offer> offers;

}
