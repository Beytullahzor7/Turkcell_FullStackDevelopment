package com.turkcell.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//401: Yetkisiz Giris"
@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class ResourceUnAuthorizedException extends RuntimeException {

    //Parametreli Constructor
    public ResourceUnAuthorizedException(String message) {
        super(message);
    }
}
