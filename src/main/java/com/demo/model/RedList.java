package com.demo.model;

import lombok.Getter;
import lombok.Setter;
import org.apache.tomcat.jni.Local;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class RedList {
    @Id
    @GeneratedValue
    private long id;
    private String email;
    private LocalDateTime dateTime;

    public RedList() {
    }

    public RedList(String email) {
        this.email = email;
    }

    public RedList(long id, String email, LocalDateTime dateTime) {
        this.id = id;
        this.email = email;
        this.dateTime = dateTime;
    }
}
