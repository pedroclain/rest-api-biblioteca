package com.example.restapibiblioteca.dto;

import com.example.restapibiblioteca.domain.Gender;

import java.io.Serializable;

public class GenderRequestUpdate implements Serializable {

    private long id;
    private String name;

    public GenderRequestUpdate(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public GenderRequestUpdate(){}

    public Gender getGender() {
        return new Gender(name);
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
