package com.radovan.tomala.contactform.controller;

import com.radovan.tomala.contactform.exception.ContactFormGeneralException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ControllerAdvice {

    @ExceptionHandler(ContactFormGeneralException.class)
    public ResponseEntity<String> handleContactFormGeneralException(ContactFormGeneralException e) {
        log.debug("Contact Form exception caught. Message: {}", e.getMessage());
        return ResponseEntity.badRequest().body(e.getMessage());
    }

}
