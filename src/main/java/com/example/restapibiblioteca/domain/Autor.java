package com.example.restapibiblioteca.domain;

import javax.persistence.*;


import java.util.List;

@Entity
public class Autor extends AbstractEntity {

    private String name;

    @OneToMany(mappedBy = "autor")
    private List<Book> bookList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }
}
