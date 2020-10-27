package com.radovan.tomala.contactform.dto;

import com.radovan.tomala.contactform.entity.RequestType;
import lombok.Getter;

@Getter
public class RequestTypeDto {
    private Long id;
    private String name;

    public static RequestTypeDto of(RequestType requestType){
        RequestTypeDto dto = new RequestTypeDto();
        dto.id = requestType.getId();
        dto.name = requestType.getName();
        return dto;
    }
}
