package com.bmstu.flowrence.dto.request.personal;

import lombok.Getter;

@Getter
public class UserToTeamActionRequestDto extends AbstractPersonalRequestDto {

    private String teamUuid;
}

