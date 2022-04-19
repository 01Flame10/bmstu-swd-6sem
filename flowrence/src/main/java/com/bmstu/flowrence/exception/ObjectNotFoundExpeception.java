package com.bmstu.flowrence.exception;

public class ObjectNotFoundExpeception extends RuntimeException {

    public ObjectNotFoundExpeception(String uuid, Class<? extends Object> objectClass) {
        super(String.format("Object of type %s with identifier %s not found",
                uuid, objectClass.getSimpleName()));
    }
}
