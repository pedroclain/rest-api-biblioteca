package com.example.restapibiblioteca.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;
    private String name;

    @ManyToOne
    @JoinColumn
    private Autor autor;
    @ManyToOne
    @JoinColumn
    private Gender gender;
    @ManyToOne
    @JoinColumn
    private Publisher publisher;
    private LocalDate publishDate;

    public Book(String name, Autor autor, Gender gender, Publisher publisher, LocalDate publishDate) {
        this.name = name;
        this.autor = autor;
        this.gender = gender;
        this.publisher = publisher;
        this.publishDate = publishDate;
    }

    public Book() {}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id && Objects.equals(name, book.name) && Objects.equals(autor, book.autor) && Objects.equals(gender, book.gender) && Objects.equals(publisher, book.publisher) && Objects.equals(publishDate, book.publishDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, autor, gender, publisher, publishDate);
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

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }
}
