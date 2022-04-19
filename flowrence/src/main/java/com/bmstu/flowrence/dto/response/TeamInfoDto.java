package com.bmstu.flowrence.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TeamInfoDto {

    String name;
    List<UserInfoDto> teamMembers;

}
