package com.example.restapibiblioteca.dto.request;

import com.example.restapibiblioteca.domain.Autor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class AutorRequestCreate implements Serializable {

    @NotEmpty(message = "The name can't be empty")
    @NotNull(message = "The name can't be null")
    @Size(min = 2, message = "The name needs at least two characters")
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
