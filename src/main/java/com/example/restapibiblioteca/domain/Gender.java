package com.example.restapibiblioteca.domain;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Gender{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private boolean deleted = false;
    private String name;
    @OneToMany(mappedBy = "gender")
    private List<Book> books;

    public Gender(String name) {
        this.name = name;
        this.books = List.of();
    }

    public Gender(long id) {
        this.id = id;
    }

    public Gender() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gender gender = (Gender) o;
        return id == gender.id && Objects.equals(name, gender.name) && Objects.equals(books, gender.books);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, books);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
