package com.example.restapibiblioteca.dto.inner;

import com.example.restapibiblioteca.domain.Publisher;

public class InnerPublisher {
    private Long id;
    private String name;

    public InnerPublisher(Publisher publisher) {
        this.id = publisher.getId();
        this.name = publisher.getName();
    }

    public InnerPublisher() {}

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
