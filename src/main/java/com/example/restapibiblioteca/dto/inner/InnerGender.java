package com.example.restapibiblioteca.dto.inner;

import com.example.restapibiblioteca.domain.Gender;

public class InnerGender {
    private Long id;
    private String name;

    public InnerGender(Gender gender) {
        this.id = gender.getId();
        this.name = gender.getName();
    }

    public InnerGender() {}

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
