package com.radovan.tomala.contactform.service;

import com.radovan.tomala.contactform.dto.RequestDto;
import com.radovan.tomala.contactform.entity.Request;
import com.radovan.tomala.contactform.entity.RequestType;
import com.radovan.tomala.contactform.exception.ContactFormGeneralException;
import com.radovan.tomala.contactform.repository.RequestRepository;
import com.radovan.tomala.contactform.repository.RequestTypeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@DataJpaTest
@Import({RequestService.class})
public class RequestServiceTest {

    @Autowired
    RequestService requestService;

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    RequestRepository requestRepository;

    @Autowired
    RequestTypeRepository requestTypeRepository;


    @Test
    public void correctRequest_create_success() throws ContactFormGeneralException {
        RequestType requestType1 = new RequestType("Type1");
        Long typeId = entityManager.persistAndGetId(requestType1, Long.class);
        RequestDto requestDto = RequestDto.of(typeId,
                "policy",
                "name",
                "surname",
                "body"
        );
        requestService.createRequest(requestDto);
        List<Request> requests = requestRepository.findAll();
        assertEquals(1, requests.size());
        assertEquals(typeId, requests.get(0).getRequestType().getId());
        assertEquals("policy", requests.get(0).getPolicyNumber());
        assertEquals("name", requests.get(0).getName());
        assertEquals("surname", requests.get(0).getSurname());
        assertEquals("body", requests.get(0).getBody());
        RequestType typeAfter = requestTypeRepository.getOne(typeId);
        assertEquals(1, typeAfter.getRequests().size());
        assertEquals("policy", typeAfter.getRequests().get(0).getPolicyNumber());
    }
}
