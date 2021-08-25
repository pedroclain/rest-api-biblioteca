package com.example.restapibiblioteca.service;

import com.example.restapibiblioteca.domain.Autor;
import com.example.restapibiblioteca.domain.Book;
import com.example.restapibiblioteca.domain.Gender;
import com.example.restapibiblioteca.domain.Publisher;
import com.example.restapibiblioteca.dto.BookRequestCreate;
import com.example.restapibiblioteca.dto.BookRequestUpdate;
import com.example.restapibiblioteca.exception.ResourceNotFound;
import com.example.restapibiblioteca.repository.BookRepository;
import com.example.restapibiblioteca.util.autor.AutorCreator;
import com.example.restapibiblioteca.util.book.BookCreator;
import com.example.restapibiblioteca.util.book.BookRequestCreateCreator;
import com.example.restapibiblioteca.util.book.BookRequestUpdateCreator;
import com.example.restapibiblioteca.util.gender.GenderCreator;
import com.example.restapibiblioteca.util.publisher.PublisherCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class BookServiceTest {

    @InjectMocks
    private BookService service;
    @Mock
    private BookRepository repositoryMock;
    @Mock
    private AutorService autorServiceMock;
    @Mock
    private GenderService genderServiceMock;
    @Mock
    private PublisherService publisherServiceMock;

    @BeforeEach
    void setUp() {
        Book createdBook = BookCreator.createdBook();
        Autor createdAutor = AutorCreator.createdAutor();
        Gender createdGender = GenderCreator.createdGender();
        Publisher createdPublisher = PublisherCreator.createdPublisher();
        List<Book> bookList = BookCreator.bookList();

        BDDMockito.when(repositoryMock.findAll()).thenReturn(bookList);
        BDDMockito.when(repositoryMock.findAll(ArgumentMatchers.any(Pageable.class))).thenReturn(getPage());
        BDDMockito.when(repositoryMock.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(createdBook));
        BDDMockito.when(repositoryMock.findByName(ArgumentMatchers.anyString())).thenReturn(Optional.of(createdBook));
        BDDMockito.when(repositoryMock.save(ArgumentMatchers.any(Book.class))).thenReturn(createdBook);
        BDDMockito.doNothing().when(repositoryMock).delete(ArgumentMatchers.any(Book.class));
        BDDMockito.when(autorServiceMock.findById(ArgumentMatchers.anyLong())).thenReturn(createdAutor);
        BDDMockito.when(genderServiceMock.findById(ArgumentMatchers.anyLong())).thenReturn(createdGender);
        BDDMockito.when(publisherServiceMock.findById(ArgumentMatchers.anyLong())).thenReturn(createdPublisher);
    }

    Page<Book> getPage() {
        PageRequest pageable = PageRequest.of(0, 20);
        return new PageImpl<>(BookCreator.bookList(), pageable, pageable.getPageSize());
    }

    @Test
    void findByName_ReturnBookAsBookView_WhenSuccessful() {
        Book expectedBook = BookCreator.createdBook();

        Book serviceBook = assertDoesNotThrow(() -> service.findByName(expectedBook.getName()));
        assertNotNull(serviceBook);
        assertEquals(expectedBook, serviceBook);
    }

    @Test
    void findByName_ReturnResourceNotFoundException_WhenNotExists() {
        BDDMockito.when(repositoryMock.findByName(ArgumentMatchers.anyString())).thenReturn(Optional.empty());

        String invalidName = "";
        ResourceNotFound resourceNotFound = assertThrows(ResourceNotFound.class,
                () -> service.findByName(invalidName));

        assertEquals("Book "+invalidName+" not found", resourceNotFound.getMessage());
    }

    @Test
    void findById_ReturnBookAsBookView_WhenSuccessful() {
        Book expectedBook = BookCreator.createdBook();
        assertDoesNotThrow(() -> service.findById(expectedBook.getId()));
        Book serviceBook = service.findById(expectedBook.getId());
        assertNotNull(serviceBook);
        assertEquals(expectedBook, serviceBook);
    }

    @Test
    void findById_ReturnResourceNotFoundException_WhenNotExists() {
        BDDMockito.when(repositoryMock.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.empty());

        long invalidId = -1;
        ResourceNotFound resourceNotFound = assertThrows(ResourceNotFound.class,
                () -> service.findById(invalidId));

        assertEquals("Book with id "+invalidId+" not found", resourceNotFound.getMessage());
    }

    @Test
    void delete_DeleteBook_WhenSuccessful() {
        assertDoesNotThrow(() -> service.delete(1L));
    }

    @Test
    void delete_ReturnResourceNotFoundException_WhenNotExists() {
        BDDMockito.when(repositoryMock.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.empty());

        long invalidId = -1;
        ResourceNotFound resourceNotFound = assertThrows(ResourceNotFound.class,
                () -> service.delete(invalidId));

        assertEquals("Book with id "+invalidId+" not found", resourceNotFound.getMessage());
    }

    @Test
    void save_CreateNewBook_WhenSuccessful() {
        BookRequestCreate bookRequestCreate = BookRequestCreateCreator.create();
        Book expectedBook = BookCreator.createdBook();
        Book serviceBook = assertDoesNotThrow(() -> service.save(bookRequestCreate));
        assertNotNull(serviceBook);
        assertEquals(expectedBook, serviceBook);
    }

    @Test
    void update_RemoveAndCreateNewBook_WhenSuccessful() {
        BookRequestUpdate bookRequestUpdate = BookRequestUpdateCreator.create();
        Book expectedBook = BookCreator.createdBook();
        Book serviceBook = assertDoesNotThrow(() -> service.update(bookRequestUpdate));
        assertNotNull(serviceBook);
        assertEquals(expectedBook, serviceBook);
    }

    @Test
    void update_ReturnResourceNotFoundException_WhenNotExists() {
        BDDMockito.when(repositoryMock.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.empty());

        BookRequestUpdate invalidBookRequestUpdate = new BookRequestUpdate();
        invalidBookRequestUpdate.setId(-1);
        ResourceNotFound resourceNotFound = assertThrows(ResourceNotFound.class,
                () -> service.update(invalidBookRequestUpdate));

        assertEquals("Book with id "+invalidBookRequestUpdate.getId()+" not found", resourceNotFound.getMessage());
    }

    @Test
    void listAll_ReturnAllBooksAsBookViewList_WhenSuccessful() {
        List<Book> serviceBookList = assertDoesNotThrow(() -> service.listAll());
        List<Book> expectedBookList = BookCreator.bookList();

        assertFalse(serviceBookList.isEmpty());
        assertFalse(serviceBookList.size() != expectedBookList.size());
        assertEquals(expectedBookList, serviceBookList);
    }

    @Test
    void listAll_ReturnEmptyBookViewList_WhenNoBook() {
        BDDMockito.when(repositoryMock.findAll()).thenReturn(List.of());
        List<Book> serviceList = assertDoesNotThrow(() -> service.listAll());
        assertTrue(serviceList.isEmpty());
    }

    @Test
    void list_ReturnBookListInsidePage_WhenSuccess() {
        PageRequest pageable = PageRequest.of(0, 20);
        Page<Book> expectedBookPage = getPage();
        Page<Book> serviceBookPage = assertDoesNotThrow(() ->
                service.list(pageable));
        assertFalse(serviceBookPage.isEmpty());
        assertEquals(expectedBookPage, serviceBookPage);
    }

    @Test
    void list_ReturnEmptyBookListInsidePage_WhenNoBook() {
        BDDMockito.when(repositoryMock.findAll(ArgumentMatchers.any(Pageable.class))).thenReturn(Page.empty());
        PageRequest pageable = PageRequest.of(0, 20);
        Page<Book> serviceBookPage = assertDoesNotThrow(() ->
                service.list(pageable));
        assertTrue(serviceBookPage.isEmpty());
    }
}