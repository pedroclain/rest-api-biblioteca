package com.example.restapibiblioteca.dto;

import com.example.restapibiblioteca.domain.Autor;

import java.io.Serializable;

public class AutorRequestUpdate implements Serializable {

    private long id;
    private String name;

    public AutorRequestUpdate(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public AutorRequestUpdate(){}

    public Autor getAutor() {
        return new Autor(name);
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
