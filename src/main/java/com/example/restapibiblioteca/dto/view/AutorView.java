package com.example.restapibiblioteca.dto.view;

import com.example.restapibiblioteca.domain.Book;
import com.example.restapibiblioteca.domain.Autor;
import com.example.restapibiblioteca.dto.inner.InnerBook;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class AutorView {

    private Long id;
    private String name;
    private List<InnerBook> books;

    public AutorView(Autor autor) {
        this.id = autor.getId();
        this.name = autor.getName();
        this.books = autor.getBooks().stream().map(InnerBook::new).collect(Collectors.toList());
    }

    public AutorView(){}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AutorView autorView = (AutorView) o;
        return Objects.equals(id, autorView.id) && Objects.equals(name, autorView.name) && Objects.equals(books, autorView.books);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, books);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<InnerBook> getBooks() {
        return books;
    }

    public void setBooks(List<InnerBook> books) {
        this.books = books;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
