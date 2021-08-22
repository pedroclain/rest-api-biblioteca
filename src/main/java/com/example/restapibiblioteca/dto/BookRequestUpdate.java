package com.example.restapibiblioteca.dto;

import com.example.restapibiblioteca.domain.Autor;
import com.example.restapibiblioteca.domain.Book;
import com.example.restapibiblioteca.domain.Gender;
import com.example.restapibiblioteca.domain.Publisher;

import java.io.Serializable;
import java.time.LocalDate;

public class BookRequestUpdate implements Serializable {

    private long id;
    private String name;
    private long autorId;
    private long genderId;
    private long publisherId;
    private LocalDate publishDate;

    public Book getBook() {
        return new Book(name, new Autor(autorId),
                new Gender(genderId),
                new Publisher(publisherId),
                publishDate);
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

    public long getAutorId() {
        return autorId;
    }

    public void setAutorId(long autorId) {
        this.autorId = autorId;
    }

    public long getGenderId() {
        return genderId;
    }

    public void setGenderId(long genderId) {
        this.genderId = genderId;
    }

    public long getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(long publisherId) {
        this.publisherId = publisherId;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }
}
