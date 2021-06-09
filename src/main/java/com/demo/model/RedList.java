package com.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class RedList { //users with over 3 reports
    @Id
    @GeneratedValue
    private long id;
    @OneToOne
    private User user;
    private LocalDateTime dateTime;

    public RedList(User user, LocalDateTime dateTime) {
        this.user = user;
        this.dateTime = dateTime;
    }

    public RedList() {
    }
}
