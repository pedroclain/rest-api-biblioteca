package com.example.restapibiblioteca.util.autor;

import com.example.restapibiblioteca.domain.Book;
import com.example.restapibiblioteca.domain.Autor;

import java.util.List;

public class AutorCreator {

    public static Autor newAutor() {
        return new Autor("autor");
    }

    public static Autor createdAutor() {
        Autor autor = new Autor("autor");
        autor.setId(1L);
        Book book = new Book();
        book.setName("book");
        autor.setBooks(List.of(book));
        return autor;
    }

    public static List<Autor> autorList() {
        Autor autor1 = new Autor("autor");
        autor1.setId(1L);
        Autor autor2 = new Autor("autor 2");
        autor2.setId(2L);
        return List.of(autor1, autor2);
    }

    public static Autor toUpdateAutor() {
        Autor autor = new Autor("autor");
        autor.setId(1L);
        return autor;
    }
}
