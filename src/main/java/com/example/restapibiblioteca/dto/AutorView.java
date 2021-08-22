package com.example.restapibiblioteca.dto;

import com.example.restapibiblioteca.domain.Book;
import com.example.restapibiblioteca.domain.Autor;

import java.util.List;
import java.util.stream.Collectors;

public class AutorView {

    private Long id;
    private String name;
    private List<String> books;

    public AutorView(Autor autor) {
        this.id = autor.getId();
        this.name = autor.getName();
        this.books = autor.getBookList().stream().map(Book::getName).collect(Collectors.toList());
    }

    public AutorView(){}

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
