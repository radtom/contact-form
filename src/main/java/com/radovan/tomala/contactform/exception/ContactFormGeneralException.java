package com.radovan.tomala.contactform.exception;

import lombok.Getter;

@Getter
public class ContactFormGeneralException extends Throwable{
    private final String message;

    public ContactFormGeneralException(String message) {
        this.message = message;
    }
}
