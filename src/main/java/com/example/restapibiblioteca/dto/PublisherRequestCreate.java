package com.example.restapibiblioteca.dto;

import com.example.restapibiblioteca.domain.Publisher;

import java.io.Serializable;

public class PublisherRequestCreate implements Serializable {

    private String name;

    public PublisherRequestCreate(String name) {
        this.name = name;
    }

    public PublisherRequestCreate(){}

    public Publisher getPublisher() {
        return new Publisher(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
