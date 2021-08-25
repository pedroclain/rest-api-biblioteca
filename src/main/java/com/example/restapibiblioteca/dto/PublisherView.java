package com.example.restapibiblioteca.dto;

import com.example.restapibiblioteca.domain.Book;
import com.example.restapibiblioteca.domain.Publisher;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PublisherView {

    private Long id;
    private String name;
    private List<String> booksNames;

    public PublisherView(Publisher publisher) {
        this.id = publisher.getId();
        this.name = publisher.getName();
        this.booksNames = publisher.getBooks().stream().map(Book::getName).collect(Collectors.toList());
    }

    public PublisherView(){}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PublisherView that = (PublisherView) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(booksNames, that.booksNames);
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
