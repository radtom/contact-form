package com.radovan.tomala.contactform.controller;

import com.radovan.tomala.contactform.dto.RequestTypeDto;
import com.radovan.tomala.contactform.exception.ContactFormGeneralException;
import com.radovan.tomala.contactform.service.RequestTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/request-type", produces = "application/json")
public class RequestTypeController {

    @Autowired
    RequestTypeService requestTypeService;

    @GetMapping
    public List<RequestTypeDto> getAllRequestTypes(){
        return requestTypeService.getAll();
    }

    @PostMapping
    public RequestTypeDto createRequestType(@RequestBody String name) throws ContactFormGeneralException {
        return requestTypeService.createRequestType(name);
    }
}
