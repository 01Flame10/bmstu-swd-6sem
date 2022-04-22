package com.bmstu.flowrence.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TaskInfoDto {

    String simpleIdentifier;
    String header;
    String description;

    String type;
    String status;
    String priority;

    UserInfoDto reporter;
    UserInfoDto assignee;
}
