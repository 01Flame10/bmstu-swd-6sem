package com.bmstu.flowrence.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserInfoDto {

    String uuid;
    String firstName;
    String lastName;
    String email;
    String password;
    Boolean active;

}
