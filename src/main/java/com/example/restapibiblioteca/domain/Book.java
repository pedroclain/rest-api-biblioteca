package com.example.restapibiblioteca.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
public class Book extends AbstractEntity{

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
