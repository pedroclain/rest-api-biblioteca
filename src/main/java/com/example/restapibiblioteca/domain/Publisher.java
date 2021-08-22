package com.example.restapibiblioteca.domain;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Publisher extends AbstractEntity{

    private String name;
    @OneToMany(mappedBy = "publisher")
    private List<Book> books;

    public Publisher(String name) {
        this.name = name;
        this.books = List.of();
    }

    public Publisher(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
