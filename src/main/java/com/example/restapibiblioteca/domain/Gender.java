package com.example.restapibiblioteca.domain;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Gender extends AbstractEntity{

    private String name;
    @OneToMany(mappedBy = "gender")
    private List<Book> books;

    public Gender(String name) {
        this.name = name;
        this.books = List.of();
    }

    public Gender() {
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
