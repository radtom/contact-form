package com.radovan.tomala.contactform.service;

import com.radovan.tomala.contactform.dto.RequestDto;
import com.radovan.tomala.contactform.dto.ValidationResultDto;

/**
 * Provides methods for validating Requests
 */
public interface RequestValidationService {

    /**
     * Checks of all constraints are met and returns result
     * @param request RequestDto object
     * @return corresponding ValidationResultDto
     */
    public ValidationResultDto validate(RequestDto request);
}
