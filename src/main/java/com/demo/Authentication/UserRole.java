package com.demo.Authentication;

import com.demo.model.User;
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
    @ManyToMany(mappedBy = "roles")
    private List<User> users;


//    ADMIN(Sets.newHashSet(OFFER_READ,OFFER_WRITE,USER_READ,USER_WRITE)),
//    INTERMEDIATE(Sets.newHashSet()),
//    USER(Sets.newHashSet());
//
//    UserRole(Set<UserPermission> permissions) {
//        this.permissions = permissions;
//    }
//
//    private final Set<UserPermission> permissions;
//
//    public Set<UserPermission> getPermissions() {
//        return permissions;
//    }
}
