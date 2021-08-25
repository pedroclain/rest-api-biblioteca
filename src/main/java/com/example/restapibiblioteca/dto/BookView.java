package com.example.restapibiblioteca.dto;

import com.example.restapibiblioteca.domain.Book;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class BookView implements Serializable {

    private Long id;
    private String name;
    private String autorName;
    private String genderName;
    private String publisherName;
    private LocalDate publishDate;


    public BookView(Book book) {
        this.id = book.getId();
        this.name = book.getName();
        this.autorName = book.getAutor().getName();
        this.genderName = book.getGender().getName();
        this.publisherName = book.getPublisher().getName();
        this.publishDate = book.getPublishDate();
    }

    public BookView(){}


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookView that = (BookView) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

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

    public String getAutorName() {
        return autorName;
    }

    public void setAutorName(String autorName) {
        this.autorName = autorName;
    }

    public String getGenderName() {
        return genderName;
    }

    public void setGenderName(String genderName) {
        this.genderName = genderName;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }
}
