package com.radovan.tomala.contactform.service;

import com.radovan.tomala.contactform.dto.RequestTypeDto;
import com.radovan.tomala.contactform.exception.ContactFormGeneralException;

import java.util.List;

/**
 * Provides methods for RequestType manipulation
 */
public interface RequestTypeService {

    /**
     * Creates new Request Type and saves it to database
     *
     * @param name name of new Request Type
     * @return RequestTypeDto of created Request Type
     * @throws ContactFormGeneralException when creation is not succesful
     */
    RequestTypeDto createRequestType(String name) throws ContactFormGeneralException;

    /**
     * Gets list of all Request Types from database
     * @return List of all Request Types
     */
    List<RequestTypeDto> getAll();


}
