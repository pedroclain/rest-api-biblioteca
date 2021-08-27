package com.example.restapibiblioteca.service;

import com.example.restapibiblioteca.domain.Autor;
import com.example.restapibiblioteca.dto.AutorRequestCreate;
import com.example.restapibiblioteca.dto.AutorRequestUpdate;
import com.example.restapibiblioteca.exception.ResourceNotFound;
import com.example.restapibiblioteca.util.autor.AutorCreator;
import com.example.restapibiblioteca.util.autor.AutorRequestCreateCreator;
import com.example.restapibiblioteca.util.autor.AutorRequestUpdateCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class AutorServiceTest {

//    @InjectMocks
    @Autowired
    private AutorService service;
//    @Mock
//    private AutorRepository repositoryMock;
    private final String BASE_URL = "http://localhost:8080/autor/";

    @BeforeEach
    void setUp() {
//        Autor createdAutor = AutorCreator.createdAutor();
//        List<Autor> autorList = AutorCreator.autorList();
//
//        BDDMockito.when(repositoryMock.findAll()).thenReturn(autorList);
//        BDDMockito.when(repositoryMock.findAll(ArgumentMatchers.any(Pageable.class))).thenReturn(getPage());
//        BDDMockito.when(repositoryMock.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(createdAutor));
//        BDDMockito.when(repositoryMock.findByName(ArgumentMatchers.anyString())).thenReturn(Optional.of(createdAutor));
//        BDDMockito.when(repositoryMock.save(ArgumentMatchers.any(Autor.class))).thenReturn(createdAutor);
//        BDDMockito.doNothing().when(repositoryMock).delete(ArgumentMatchers.any(Autor.class));
    }

    Page<Autor> getPage() {
        PageRequest pageable = PageRequest.of(0, 20);
        return new PageImpl<Autor>(AutorCreator.autorList(), pageable, pageable.getPageSize());
    }

    @Test
    void test() {
        AutorRequestCreate requestCreate = AutorRequestCreateCreator.create();
        Autor expectedAutor = AutorCreator.createdAutor();
        Autor serviceAutor= service.save(requestCreate);
        assertEquals(expectedAutor.getName(), serviceAutor.getName());
    }

    @Test
    void findByName_ReturnAutorAsAutorView_WhenSuccessful() {
        Autor autor = AutorCreator.createdAutor();

        Autor serviceAutor = assertDoesNotThrow(() -> service.findByName(autor.getName()));
        assertEquals(autor.getId(), serviceAutor.getId());
        assertEquals(autor.getName(), serviceAutor.getName());
        assertEquals(autor.getBooks(), serviceAutor.getBooks());
    }

    @Test
    void findByName_ReturnResourceNotFoundException_WhenNotExists() {
//        BDDMockito.when(repositoryMock.findByName(ArgumentMatchers.anyString())).thenReturn(Optional.empty());

        String invalidName = "";
        ResourceNotFound resourceNotFound = assertThrows(ResourceNotFound.class,
                () -> service.findByName(invalidName));

        assertEquals("Autor "+invalidName+" not found", resourceNotFound.getMessage());
    }

    @Test
    void findById_ReturnAutorAsAutorView_WhenSuccessful() {
        Autor autor = AutorCreator.createdAutor();
        assertDoesNotThrow(() -> service.findById(autor.getId()));
        Autor serviceAutor = service.findById(autor.getId());
        assertEquals(autor.getId(), serviceAutor.getId());
        assertEquals(autor.getName(), serviceAutor.getName());
        assertEquals(autor.getBooks(), serviceAutor.getBooks());
    }

    @Test
    void findById_ReturnResourceNotFoundException_WhenNotExists() {
//        BDDMockito.when(repositoryMock.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.empty());

        long invalidId = -1;
        ResourceNotFound resourceNotFound = assertThrows(ResourceNotFound.class,
                () -> service.findById(invalidId));

        assertEquals("Autor with id "+invalidId+" not found", resourceNotFound.getMessage());
    }

    @Test
    void delete_DeleteAutor_WhenSuccessful() {
        assertDoesNotThrow(() -> service.delete(1L));
    }

    @Test
    void delete_ReturnResourceNotFoundException_WhenNotExists() {
//        BDDMockito.when(repositoryMock.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.empty());

        long invalidId = -1;
        ResourceNotFound resourceNotFound = assertThrows(ResourceNotFound.class,
                () -> service.delete(invalidId));

        assertEquals("Autor with id "+invalidId+" not found", resourceNotFound.getMessage());
    }

    @Test
    void save_CreateNewAutor_WhenSuccessful() {
        AutorRequestCreate autorRequestCreate = AutorRequestCreateCreator.create();
        Autor expectedAutor = AutorCreator.createdAutor();
        Autor serviceAutor = assertDoesNotThrow(() -> service.save(autorRequestCreate));
        assertEquals(expectedAutor.getId(), serviceAutor.getId());
        assertEquals(expectedAutor.getName(), serviceAutor.getName());
        assertEquals(expectedAutor.getBooks(), serviceAutor.getBooks());
    }

    @Test
    void update_RemoveAndCreateNewAutor_WhenSuccessful() {
        AutorRequestUpdate autorRequestUpdate = AutorRequestUpdateCreator.create();
        Autor expectedAutor = AutorCreator.createdAutor();
        Autor serviceAutor = assertDoesNotThrow(() -> service.update(autorRequestUpdate));
        assertEquals(expectedAutor.getId(), serviceAutor.getId());
        assertEquals(expectedAutor.getName(), serviceAutor.getName());
        assertEquals(expectedAutor.getBooks(), serviceAutor.getBooks());
    }

    @Test
    void update_ReturnResourceNotFoundException_WhenNotExists() {
//        BDDMockito.when(repositoryMock.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.empty());

        AutorRequestUpdate invalidAutorRequestUpdate = new AutorRequestUpdate();
        invalidAutorRequestUpdate.setId(-1);
        ResourceNotFound resourceNotFound = assertThrows(ResourceNotFound.class,
                () -> service.update(invalidAutorRequestUpdate));

        assertEquals("Autor with id "+invalidAutorRequestUpdate.getId()+" not found", resourceNotFound.getMessage());
    }

    @Test
    void listAll_ReturnAllAutorsAsAutorViewList_WhenSuccessful() {
        List<Autor> serviceAutorList = assertDoesNotThrow(() -> service.listAll());
        List<Autor> expectedAutorList = AutorCreator.autorList();

        assertFalse(serviceAutorList.isEmpty());
        assertFalse(serviceAutorList.size() != expectedAutorList.size());
        assertEquals(expectedAutorList, serviceAutorList);
    }

    @Test
    void listAll_ReturnEmptyAutorViewList_WhenNoAutor() {
//        BDDMockito.when(repositoryMock.findAll()).thenReturn(List.of());
        List<Autor> serviceList = assertDoesNotThrow(() -> service.listAll());
        assertTrue(serviceList.isEmpty());
    }

    @Test
    void list_ReturnAutorListInsidePage_WhenSuccess() {
        PageRequest pageable = PageRequest.of(0, 20);
        Page<Autor> expectedAutorPage = getPage();
        Page<Autor> serviceAutorPage = assertDoesNotThrow(() ->
                service.list(pageable));
        assertFalse(serviceAutorPage.isEmpty());
        assertEquals(expectedAutorPage, serviceAutorPage);
    }

    @Test
    void list_ReturnEmptyAutorListInsidePage_WhenNoAutor() {
//        BDDMockito.when(repositoryMock.findAll(ArgumentMatchers.any(Pageable.class))).thenReturn(Page.empty());
        PageRequest pageable = PageRequest.of(0, 20);
        Page<Autor> serviceAutorPage = assertDoesNotThrow(() ->
                service.list(pageable));
        assertTrue(serviceAutorPage.isEmpty());
    }
}