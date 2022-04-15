package com.bmstu.flowrence.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.Set;

@Data
@Entity
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserGroup extends BaseEntity {

    String name;

    @ManyToMany
    @JoinTable(name = "user_group_to_user",
            joinColumns = @JoinColumn(name = "uuid"),
            inverseJoinColumns = @JoinColumn(name = "uuid"))
    Set<User> users; // TODO: resolve "Repeated column in mapping for collection: com.bmstu.flowrence.entity.UserGroup.users column: uuid"

}
