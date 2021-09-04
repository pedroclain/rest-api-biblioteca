package com.example.restapibiblioteca.dto.view;

import com.example.restapibiblioteca.domain.Book;
import com.example.restapibiblioteca.domain.Gender;
import com.example.restapibiblioteca.dto.inner.InnerBook;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GenderView implements Serializable {

    private Long id;
    private String name;
    private List<InnerBook> books;

    public GenderView(Gender gender) {
        this.id = gender.getId();
        this.name = gender.getName();
        this.books = gender.getBooks().stream().map(InnerBook::new).collect(Collectors.toList());
    }

    public GenderView(){}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GenderView that = (GenderView) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(books, that.books);
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
