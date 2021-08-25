package com.example.restapibiblioteca.util.book;

import com.example.restapibiblioteca.domain.*;
import com.example.restapibiblioteca.domain.Book;

import java.time.LocalDate;
import java.util.List;

public class BookCreator {

    public static Book newBook() {
        Book book = new Book();
        book.setName("book");
        book.setGender(new Gender("gender"));
        book.setAutor(new Autor("autor"));
        book.setPublisher(new Publisher("publisher"));
        book.setPublishDate(LocalDate.of(2020,1,1));
        return book;
    }

    public static Book createdBook() {
        Book book = new Book();
        book.setId(1L);
        book.setName("book");
        book.setGender(new Gender("gender"));
        book.setAutor(new Autor("autor"));
        book.setPublisher(new Publisher("publisher"));
        book.setPublishDate(LocalDate.of(2020,1,1));
        return book;
    }

    public static List<Book> bookList() {
        Book book = new Book();
        book.setId(1L);
        book.setName("book");
        book.setGender(new Gender("gender"));
        book.setAutor(new Autor("autor"));
        book.setPublisher(new Publisher("publisher"));
        book.setPublishDate(LocalDate.of(2020,1,1));

        Book book2 = new Book();
        book2.setId(2L);
        book.setName("book 2");
        book.setGender(new Gender("gender 2"));
        book.setAutor(new Autor("autor 2"));
        book.setPublisher(new Publisher("publisher 2"));
        book.setPublishDate(LocalDate.of(2020,1,1));
        return List.of(book, book2);
    }

    public static Book toUpdateBook() {
        Book book = new Book();
        book.setId(1L);
        book.setName("book");
        book.setGender(new Gender("gender"));
        book.setAutor(new Autor("autor"));
        book.setPublisher(new Publisher("publisher"));
        book.setPublishDate(LocalDate.of(2020,1,1));
        return book;
    }
}
