package com.radovan.tomala.contactform.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="request")
@Getter
@Setter
@NoArgsConstructor
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private RequestType requestType;

    private String policyNumber;

    private String name;

    private String surname;

    @Lob
    private String body;

    public Request(RequestType requestType, String policyNumber, String name, String surname, String body) {
        this.requestType = requestType;
        this.policyNumber = policyNumber;
        this.name = name;
        this.surname = surname;
        this.body = body;
    }
}
