package com.radovan.tomala.contactform.service;

import com.radovan.tomala.contactform.dto.RequestDto;
import com.radovan.tomala.contactform.exception.ContactFormGeneralException;

/**
 * Provides methods for Request manipulation
 */
public interface RequestService {

    /**
     * Creates new Request and saves it to database
     *
     * @param requestDto RequestDto object
     * @return RequestDto object on success
     * @throws ContactFormGeneralException when creation is not successful
     */
    RequestDto createRequest(RequestDto requestDto) throws ContactFormGeneralException;

}
