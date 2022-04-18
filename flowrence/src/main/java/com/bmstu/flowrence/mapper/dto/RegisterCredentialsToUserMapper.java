package com.bmstu.flowrence.mapper.dto;

import com.bmstu.flowrence.dto.request.UserRegisterCredentialsDto;
import com.bmstu.flowrence.entity.User;
import com.bmstu.flowrence.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface RegisterCredentialsToUserMapper extends BaseMapper<User, UserRegisterCredentialsDto> {

    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "password", source = "password")
    User mapSourceToDestination(UserRegisterCredentialsDto source);
}
