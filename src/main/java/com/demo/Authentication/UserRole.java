package com.demo.Authentication;

import com.demo.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Sets;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

import static com.demo.Authentication.UserPermission.*;

@Entity
@Getter
@Setter
public class UserRole {

    @Id
    @GeneratedValue
    private long id;

    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "roles", cascade =
            {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.REFRESH,
                    CascadeType.PERSIST
            })
    private List<User> users;

}
