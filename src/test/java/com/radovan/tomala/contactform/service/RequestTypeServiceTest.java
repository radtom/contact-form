package com.radovan.tomala.contactform.service;

import com.radovan.tomala.contactform.dto.RequestTypeDto;
import com.radovan.tomala.contactform.entity.RequestType;
import com.radovan.tomala.contactform.exception.ContactFormGeneralException;
import com.radovan.tomala.contactform.repository.RequestTypeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@Import({RequestTypeService.class})
public class RequestTypeServiceTest {

    @Autowired
    RequestTypeService requestTypeService;

    @Autowired
    RequestTypeRepository requestTypeRepository;

    @Test
    public void testCreateRequest() throws ContactFormGeneralException {
        String typeName = "Type1";
        RequestTypeDto type = requestTypeService.createRequestType(typeName);
        RequestType testType = requestTypeRepository.getOne(type.getId());
        assertEquals(typeName, testType.getName());

    }
}
