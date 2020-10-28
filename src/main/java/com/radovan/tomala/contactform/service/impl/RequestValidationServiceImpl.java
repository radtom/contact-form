package com.radovan.tomala.contactform.service.impl;

import com.radovan.tomala.contactform.dto.RequestDto;
import com.radovan.tomala.contactform.dto.ValidationResultDto;
import com.radovan.tomala.contactform.service.RequestValidationService;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class RequestValidationServiceImpl implements RequestValidationService {
    @Override
    public ValidationResultDto validate(RequestDto request) {
        Pattern specialChars = Pattern.compile("[`!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?~]", Pattern.CASE_INSENSITIVE);
        if(request == null) return ValidationResultDto.fail("Request cannot be null");
        if(isBlank(request.getName()))
            return ValidationResultDto.fail("Name cannot be blank");
        if(isBlank(request.getSurname()))
            return ValidationResultDto.fail("Surname cannot be blank");
        if(isBlank(request.getPolicyNumber()))
            return ValidationResultDto.fail("Policy number cannot be blank");
        if(isBlank(request.getBody()))
            return ValidationResultDto.fail("Request Body cannot be blank");
        Matcher policy = specialChars.matcher(request.getPolicyNumber());
        if(policy.find())
            return ValidationResultDto.fail("Policy number cannot contain special characters");
        if(!request.getName().matches("[a-zA-Z]+"))
            return ValidationResultDto.fail("Name can only contain letters");
        if(!request.getSurname().matches("[a-zA-Z]+"))
            return ValidationResultDto.fail("Surname can only contain letters");
        return ValidationResultDto.success();
    }

    private boolean isBlank(String str){
        return str == null || str.equals("");
    }
}


