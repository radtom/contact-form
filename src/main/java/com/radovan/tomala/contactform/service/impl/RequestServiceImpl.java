package com.radovan.tomala.contactform.service.impl;

import com.radovan.tomala.contactform.dto.RequestDto;
import com.radovan.tomala.contactform.dto.ValidationResultDto;
import com.radovan.tomala.contactform.entity.Request;
import com.radovan.tomala.contactform.entity.RequestType;
import com.radovan.tomala.contactform.enums.ValidationResult;
import com.radovan.tomala.contactform.exception.ContactFormGeneralException;
import com.radovan.tomala.contactform.repository.RequestRepository;
import com.radovan.tomala.contactform.repository.RequestTypeRepository;
import com.radovan.tomala.contactform.service.RequestService;
import com.radovan.tomala.contactform.service.RequestValidationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;

@Component
@Slf4j
public class RequestServiceImpl implements RequestService {

    @Autowired
    RequestRepository requestRepository;

    @Autowired
    RequestTypeRepository requestTypeRepository;

    @Autowired
    RequestValidationService validationService;

    public RequestDto createRequest(RequestDto requestDto) throws ContactFormGeneralException {
        ValidationResultDto validation = validationService.validate(requestDto);
        if(validation.getResult().equals(ValidationResult.VALIDATED)){
            try{
                RequestType requestType = requestTypeRepository.getOne(requestDto.getRequestType());
                Request request = new Request(
                        requestType,
                        requestDto.getPolicyNumber(),
                        requestDto.getName(),
                        requestDto.getSurname(),
                        requestDto.getBody()
                );
                requestType.addRequest(request);
                return RequestDto.of(requestRepository.save(request));
            }
            catch (EntityNotFoundException e){
                log.error("Request type with id {} not found", requestDto.getRequestType());
                throw new ContactFormGeneralException("Request type with id " + requestDto.getRequestType()+" not found");
            }
        }
        log.error("Incorrect request: {}", validation.getErrorMessage());
        throw new ContactFormGeneralException(validation.getErrorMessage());
    }
}
