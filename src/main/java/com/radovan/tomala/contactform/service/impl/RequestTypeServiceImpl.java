package com.radovan.tomala.contactform.service.impl;

import com.radovan.tomala.contactform.dto.RequestTypeDto;
import com.radovan.tomala.contactform.entity.RequestType;
import com.radovan.tomala.contactform.exception.ContactFormGeneralException;
import com.radovan.tomala.contactform.repository.RequestTypeRepository;
import com.radovan.tomala.contactform.service.RequestTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class RequestTypeServiceImpl implements RequestTypeService {

    @Autowired
    RequestTypeRepository requestTypeRepository;

    public RequestTypeDto createRequestType(String name) throws ContactFormGeneralException {
        if(name != null && !name.equals("")){
            try{
                RequestType requestType = new RequestType(name);
                return RequestTypeDto.of(requestTypeRepository.save(requestType));
            }
            catch (DataIntegrityViolationException e){
                log.error(e.getMessage());
                throw new ContactFormGeneralException("Request Types cannot have duplicate names");
            }

        }
        log.error("Name cannot be blank");
        throw new ContactFormGeneralException("Name cannot be blank");
    }

    public List<RequestTypeDto> getAll(){
        List<RequestType> requests = requestTypeRepository.findAll();
        return requests.stream().map(RequestTypeDto::of).collect(Collectors.toList());
    }
}
