package com.example.restapibiblioteca.dto.view;

import com.example.restapibiblioteca.domain.Book;
import com.example.restapibiblioteca.domain.Gender;
import com.example.restapibiblioteca.dto.inner.InnerAutor;
import com.example.restapibiblioteca.dto.inner.InnerGender;
import com.example.restapibiblioteca.dto.inner.InnerPublisher;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class BookView implements Serializable {

    private Long id;
    private String name;
    private InnerAutor autor;
    private InnerGender gender;
    private InnerPublisher publisher;
    private LocalDate publishDate;


    public BookView(Book book) {
        this.id = book.getId();
        this.name = book.getName();
        this.autor = new InnerAutor(book.getAutor());
        this.gender = new InnerGender(book.getGender());
        this.publisher = new InnerPublisher(book.getPublisher());
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

    public InnerAutor getAutor() {
        return autor;
    }

    public void setAutor(InnerAutor autor) {
        this.autor = autor;
    }

    public InnerGender getGender() {
        return gender;
    }

    public void setGender(InnerGender gender) {
        this.gender = gender;
    }

    public InnerPublisher getPublisher() {
        return publisher;
    }

    public void setPublisher(InnerPublisher publisher) {
        this.publisher = publisher;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }

}
