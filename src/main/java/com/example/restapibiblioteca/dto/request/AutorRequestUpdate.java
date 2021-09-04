package com.example.restapibiblioteca.dto.request;

import com.example.restapibiblioteca.domain.Autor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class AutorRequestUpdate implements Serializable {

    @NotNull(message = "Id can't be null")
    private Long id;
    @NotEmpty(message = "The name can't be empty")
    @NotNull(message = "The name can't be null")
    @Size(min = 2, message = "The name needs at least two characters")
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
