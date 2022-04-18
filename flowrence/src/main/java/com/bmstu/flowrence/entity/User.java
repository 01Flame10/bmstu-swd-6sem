package com.bmstu.flowrence.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User extends BaseEntity {

    String firstName;
    String lastName;
//    TODO: add @UniqueConstraint
    String email;
    String password;

    @ManyToMany(mappedBy = "users", fetch = FetchType.EAGER)
    Set<Team> teams;

    @OneToMany(mappedBy="reporter")
    List<Task> reportedTasks;

    @OneToMany(mappedBy="assignee")
    List<Task> assignedTasks;
}
