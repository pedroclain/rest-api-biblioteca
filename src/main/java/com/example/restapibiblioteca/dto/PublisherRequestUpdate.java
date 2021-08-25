package com.example.restapibiblioteca.dto;

import com.example.restapibiblioteca.domain.Publisher;

import java.io.Serializable;

public class PublisherRequestUpdate implements Serializable {

    private long id;
    private String name;

    public PublisherRequestUpdate(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public PublisherRequestUpdate(){}

    public Publisher getPublisher() {
        return new Publisher(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
