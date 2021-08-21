package com.example.restapibiblioteca.dto;

import com.example.restapibiblioteca.domain.Book;
import com.example.restapibiblioteca.domain.Gender;

import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class GenderView {

    private Long id;
    private String name;
    private List<String> books;

    public GenderView(Gender gender) {
        this.id = gender.getId();
        this.name = gender.getName();
        this.books = gender.getBooks().stream().map(Book::getName).collect(Collectors.toList());
    }

    public GenderView(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getBooks() {
        return books;
    }

    public void setBooks(List<String> books) {
        this.books = books;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
