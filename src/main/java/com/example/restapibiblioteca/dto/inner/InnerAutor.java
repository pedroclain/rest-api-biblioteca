package com.example.restapibiblioteca.dto.inner;

import com.example.restapibiblioteca.domain.Autor;

public class InnerAutor {
    private Long id;
    private String name;

    public InnerAutor(Autor autor) {
        this.id = autor.getId();
        this.name = autor.getName();
    }

    public InnerAutor() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
