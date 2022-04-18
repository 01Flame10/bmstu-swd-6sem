package com.bmstu.flowrence.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@Entity
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Task extends BaseEntity {

    String simpleIdentifierPrefix;
    String simpleIdentifierNumber;

    String header;
    String description;

    @Enumerated(EnumType.STRING)
    TaskType type;
    @Enumerated(EnumType.STRING)
    TaskStatus status;
    @Enumerated(EnumType.STRING)
    TaskPriority priority;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="reporter_uuid", nullable=false)
    User reporter;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="assignee_uuid")
    User assignee;

    public enum TaskPriority {
        MINOR,
        NORMAL,
        MAJOR,
        BLOCKER
    }

    public enum TaskType {
        STORY,
        DEFECT,
        IMPROVEMENT,
        CHANGE_REQUEST
    }

    public enum TaskStatus {
        IDEA,
        ANALYSIS,
        READY_FOR_DEVELOPMENT,
        IN_DEVELOPMENT,
        READY_FOR_QA,
        IN_QA,
        DONE
    }
}
