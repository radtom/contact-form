package com.radovan.tomala.contactform.service;

import com.radovan.tomala.contactform.dto.RequestTypeDto;
import com.radovan.tomala.contactform.entity.RequestType;
import com.radovan.tomala.contactform.exception.ContactFormGeneralException;
import com.radovan.tomala.contactform.repository.RequestTypeRepository;
import com.radovan.tomala.contactform.service.impl.RequestTypeServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@Import({RequestTypeServiceImpl.class})
public class RequestTypeServiceImplTest {

    @Autowired
    RequestTypeService requestTypeService;

    @Autowired
    RequestTypeRepository requestTypeRepository;

    @Test
    public void correctName_create_success() throws ContactFormGeneralException {
        String typeName = "Type1";
        RequestTypeDto type = requestTypeService.createRequestType(typeName);
        RequestType testType = requestTypeRepository.getOne(type.getId());
        assertEquals(typeName, testType.getName());

    }
}
