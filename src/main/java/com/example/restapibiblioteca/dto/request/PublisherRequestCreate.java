package com.example.restapibiblioteca.dto.request;

import com.example.restapibiblioteca.domain.Publisher;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class PublisherRequestCreate implements Serializable {

    @NotEmpty(message = "The name can't be empty")
    @NotNull(message = "The name can't be null")
    @Size(min = 2, message = "The name needs at least two characters")
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
