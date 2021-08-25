package com.example.restapibiblioteca.dto;

import com.example.restapibiblioteca.domain.Book;

import java.io.Serializable;
import java.time.LocalDate;

public class BookRequestCreate implements Serializable {

    private String name;
    private long autorId;
    private long genderId;
    private long publisherId;
    private LocalDate publishDate;

    public BookRequestCreate(String name) {
        this.name = name;
    }

    public BookRequestCreate(){}

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
