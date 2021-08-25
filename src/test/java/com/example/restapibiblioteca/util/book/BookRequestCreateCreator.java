package com.example.restapibiblioteca.util.book;

import com.example.restapibiblioteca.dto.BookRequestCreate;

public class BookRequestCreateCreator {

    public static BookRequestCreate create() {
        BookRequestCreate bookRequestCreate = new BookRequestCreate();
        bookRequestCreate.setName("book");
        return bookRequestCreate;
    }
}
