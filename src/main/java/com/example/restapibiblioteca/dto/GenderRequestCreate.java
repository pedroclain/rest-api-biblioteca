package com.example.restapibiblioteca.dto;

import com.example.restapibiblioteca.domain.Gender;

import java.io.Serializable;

public class GenderRequestCreate implements Serializable {

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
