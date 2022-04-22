package com.bmstu.flowrence.mapper.dto;

import com.bmstu.flowrence.dto.response.TaskInfoDto;
import com.bmstu.flowrence.entity.Task;
import com.bmstu.flowrence.mapper.BaseMapper;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(uses = UserToUserInfoMapper.class,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface TaskToTaskInfoMapper extends BaseMapper<Task, TaskInfoDto> {

    @Override
    TaskInfoDto mapSourceToDestination(Task source);
}
