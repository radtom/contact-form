package com.radovan.tomala.contactform.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name="RequestType")
@Getter
@Setter
@NoArgsConstructor
public class RequestType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique=true)
    private String name;

    @OneToMany(mappedBy = "requestType", cascade = CascadeType.ALL)
    List<Request> requests = new LinkedList<>();

    public RequestType(String name) {
        this.name = name;
    }

    public void addRequest(Request request){
        requests.add(request);
    }
}
