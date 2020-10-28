package com.radovan.tomala.contactform.controller;

import com.radovan.tomala.contactform.dto.RequestDto;
import com.radovan.tomala.contactform.exception.ContactFormGeneralException;
import com.radovan.tomala.contactform.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/rest/request", produces = "application/json")
public class RequestController {

    @Autowired
    RequestService requestService;

    @PostMapping
    public RequestDto createRequestType(@RequestBody RequestDto request) throws ContactFormGeneralException {
        return requestService.createRequest(request);
    }
}
