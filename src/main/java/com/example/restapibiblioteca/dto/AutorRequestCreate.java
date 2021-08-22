package com.example.restapibiblioteca.dto;

import com.example.restapibiblioteca.domain.Autor;

import java.io.Serializable;

public class AutorRequestCreate implements Serializable {

    private String name;

    public AutorRequestCreate(String name) {
        this.name = name;
    }

    public AutorRequestCreate(){}

    public Autor getAutor() {
        return new Autor(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
