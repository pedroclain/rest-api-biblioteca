package com.example.restapibiblioteca.dto;

import com.example.restapibiblioteca.domain.Book;

import java.time.LocalDate;

public class BookView {

    private Long id;
    private String name;
    private String autor;
    private String gender;
    private String publisher;
    private LocalDate publishDate;

    public BookView(Book book) {
        this.id = book.getId();
        this.name = book.getName();
        this.autor = book.getAutor().getName();
        this.gender = book.getGender().getName();
        this.publisher = book.getPublisher().getName();
        this.publishDate = book.getPublishDate();
    }

    public BookView() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }
}
