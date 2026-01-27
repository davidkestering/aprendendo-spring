package com.javanauta.aprendendospring.infrastructure.exceptions;

public class ConflictException extends RuntimeException{

    public ConflictException(String mensage){
        super(mensage);
    }

    public ConflictException(String mensage, Throwable t){
        super(mensage,t);
    }
}
