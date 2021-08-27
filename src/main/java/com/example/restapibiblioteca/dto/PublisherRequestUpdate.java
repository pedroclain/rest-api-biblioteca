package com.example.restapibiblioteca.dto;

import com.example.restapibiblioteca.domain.Publisher;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class PublisherRequestUpdate implements Serializable {

    @NotEmpty(message = "Id can't be empty")
    @NotNull(message = "Id can't be null")
    private long id;
    @NotEmpty(message = "The name can't be empty")
    @NotNull(message = "The name can't be null")
    @Size(min = 2, message = "The name needs at least two characters")
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
