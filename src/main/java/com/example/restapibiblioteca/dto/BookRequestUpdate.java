package com.example.restapibiblioteca.dto;

import com.example.restapibiblioteca.domain.Book;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

public class BookRequestUpdate implements Serializable {

    @NotEmpty(message = "Id can't be empty")
    @NotNull(message = "Id can't be null")
    private long id;
    @NotEmpty(message = "The name can't be empty")
    @NotNull(message = "The name can't be null")
    @Size(min = 2, message = "The name needs at least two characters")
    private String name;
    @NotEmpty(message = "The autor id can't be empty")
    @NotNull(message = "The autor id can't be null")
    private long autorId;
    @NotEmpty(message = "The gender id can't be empty")
    @NotNull(message = "The gender id can't be null")
    private long genderId;
    @NotEmpty(message = "The publisher id can't be empty")
    @NotNull(message = "The publisher id can't be null")
    private long publisherId;
    @NotEmpty(message = "The publish date can't be empty")
    @NotNull(message = "The publish date can't be null")
    private LocalDate publishDate;



    public BookRequestUpdate(){}

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

    public void setPublisherId(long pubisherId) {
        this.publisherId = pubisherId;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }
}
