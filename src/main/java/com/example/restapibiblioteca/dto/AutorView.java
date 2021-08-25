package com.example.restapibiblioteca.dto;

import com.example.restapibiblioteca.domain.Book;
import com.example.restapibiblioteca.domain.Autor;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class AutorView {

    private Long id;
    private String name;
    private List<String> booksNames;

    public AutorView(Autor autor) {
        this.id = autor.getId();
        this.name = autor.getName();
        this.booksNames = autor.getBooks().stream().map(Book::getName).collect(Collectors.toList());
    }

    public AutorView(){}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AutorView autorView = (AutorView) o;
        return Objects.equals(id, autorView.id) && Objects.equals(name, autorView.name) && Objects.equals(booksNames, autorView.booksNames);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, booksNames);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getBooksNames() {
        return booksNames;
    }

    public void setBooksNames(List<String> booksNames) {
        this.booksNames = booksNames;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
