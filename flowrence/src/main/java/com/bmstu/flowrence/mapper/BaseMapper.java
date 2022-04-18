package com.bmstu.flowrence.mapper;

public interface BaseMapper <S, D> {

    D mapSourceToDestination(S source);
}
