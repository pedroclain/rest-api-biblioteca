package com.example.restapibiblioteca.dto.request;

import com.example.restapibiblioteca.domain.Gender;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class GenderRequestCreate implements Serializable {

    @NotEmpty(message = "The name can't be empty")
    @NotNull(message = "The name can't be null")
    @Size(min = 2, message = "The name needs at least two characters")
    private String name;

    public GenderRequestCreate(String name) {
        this.name = name;
    }

    public GenderRequestCreate(){}

    public Gender getGender() {
        return new Gender(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
