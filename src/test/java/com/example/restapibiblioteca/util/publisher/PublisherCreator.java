package com.example.restapibiblioteca.util.publisher;

import com.example.restapibiblioteca.domain.Book;
import com.example.restapibiblioteca.domain.Publisher;

import java.util.List;

public class PublisherCreator {

    public static Publisher newPublisher() {
        return new Publisher("publisher");
    }

    public static Publisher createdPublisher() {
        Publisher publisher = new Publisher("publisher");
        publisher.setId(1L);
        Book book = new Book();
        book.setName("book");
        publisher.setBooks(List.of(book));
        return publisher;
    }

    public static List<Publisher> publisherList() {
        Publisher publisher1 = new Publisher("publisher");
        publisher1.setId(1L);
        Publisher publisher2 = new Publisher("publisher 2");
        publisher2.setId(2L);
        return List.of(publisher1, publisher2);
    }

    public static Publisher toUpdatePublisher() {
        Publisher publisher = new Publisher("publisher");
        publisher.setId(1L);
        return publisher;
    }
}
