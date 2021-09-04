package com.example.restapibiblioteca.dto.inner;

import com.example.restapibiblioteca.domain.Autor;
import com.example.restapibiblioteca.domain.Book;

import java.time.LocalDate;

public class InnerBook {
    private Long id;
    private String name;
    private LocalDate publishDate;

    public InnerBook(Book book) {
        this.id = book.getId();
        this.name = book.getName();
        this.publishDate = book.getPublishDate();
    }

    public InnerBook(){}

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

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }
}
