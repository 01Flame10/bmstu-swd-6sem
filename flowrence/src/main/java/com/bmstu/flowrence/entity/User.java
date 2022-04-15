package com.bmstu.flowrence.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import java.util.Set;

@Data
@Entity
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User extends BaseEntity {

    String username;
    String firstName;
    String lastName;
    String email;
    String password;

    @ManyToMany(mappedBy = "users", fetch = FetchType.EAGER)
    Set<UserGroup> groups;

}
