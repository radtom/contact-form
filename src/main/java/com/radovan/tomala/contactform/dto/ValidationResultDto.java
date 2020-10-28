package com.radovan.tomala.contactform.dto;

import com.radovan.tomala.contactform.enums.ValidationResult;
import lombok.Getter;

@Getter
public class ValidationResultDto {
    private ValidationResult result;
    private String errorMessage;

    public static ValidationResultDto fail(String errorMessage){
        ValidationResultDto result = new ValidationResultDto();
        result.result = ValidationResult.FAILED;
        result.errorMessage = errorMessage;
        return result;
    }

    public static ValidationResultDto success(){
        ValidationResultDto result = new ValidationResultDto();
        result.result = ValidationResult.VALIDATED;
        return result;
    }
}
