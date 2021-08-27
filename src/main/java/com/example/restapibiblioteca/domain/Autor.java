package com.example.restapibiblioteca.domain;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @OneToMany(mappedBy = "autor")
    private List<Book> books;

    public Autor(String name) {
        this.name = name;
        this.books = List.of();
    }

    public Autor(long id) {
        this.id = id;
    }

    public Autor() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Autor autor = (Autor) o;
        return id == autor.id && Objects.equals(name, autor.name) && Objects.equals(books, autor.books);
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

    public void setBooks(List<Book> bookList) {
        this.books = bookList;
    }
}
