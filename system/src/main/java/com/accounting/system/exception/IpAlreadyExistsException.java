package com.accounting.system.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class IpAlreadyExistsException extends RuntimeException{
    private String message;

    public IpAlreadyExistsException(String message){
        super(message);
    }
}
