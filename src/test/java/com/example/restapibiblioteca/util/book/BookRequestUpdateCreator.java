package com.example.restapibiblioteca.util.book;

import com.example.restapibiblioteca.dto.BookRequestUpdate;

public class BookRequestUpdateCreator {

    public static BookRequestUpdate create() {
        BookRequestUpdate bookRequestUpdate = new BookRequestUpdate();
        bookRequestUpdate.setName("book");
        bookRequestUpdate.setId(1);
        return bookRequestUpdate;
    }
}
