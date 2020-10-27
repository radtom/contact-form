package com.radovan.tomala.contactform.dto;

import com.radovan.tomala.contactform.entity.Request;
import lombok.Getter;

@Getter
public class RequestDto {

    private Long requestType;
    private String policyNumber;
    private String name;
    private String surname;
    private String body;

    public static RequestDto of(Long requestType,
                                String policyNumber,
                                String name,
                                String surname,
                                String body){
        RequestDto dto = new RequestDto();
        dto.requestType = requestType;
        dto.policyNumber = policyNumber;
        dto.name = name;
        dto.surname = surname;
        dto.body = body;
        return dto;
    }

    public static RequestDto of(Request request){
        RequestDto dto = new RequestDto();
        dto.requestType = request.getRequestType().getId();
        dto.policyNumber = request.getPolicyNumber();
        dto.name = request.getName();
        dto.surname = request.getSurname();
        dto.body = request.getBody();
        return dto;
    }


}
