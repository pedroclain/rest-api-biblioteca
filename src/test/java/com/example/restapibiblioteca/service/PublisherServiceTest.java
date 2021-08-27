//package com.example.restapibiblioteca.service;
//
//import com.example.restapibiblioteca.domain.Publisher;
//import com.example.restapibiblioteca.dto.PublisherRequestCreate;
//import com.example.restapibiblioteca.dto.PublisherRequestUpdate;
//import com.example.restapibiblioteca.exception.ResourceNotFound;
//import com.example.restapibiblioteca.repository.PublisherRepository;
//import com.example.restapibiblioteca.util.publisher.PublisherCreator;
//import com.example.restapibiblioteca.util.publisher.PublisherRequestCreateCreator;
//import com.example.restapibiblioteca.util.publisher.PublisherRequestUpdateCreator;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.ArgumentMatchers;
//import org.mockito.BDDMockito;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@ExtendWith(SpringExtension.class)
//class PublisherServiceTest {
//
//    @InjectMocks
//    private PublisherService service;
//    @Mock
//    private PublisherRepository repositoryMock;
//
//    @BeforeEach
//    void setUp() {
//        Publisher createdPublisher = PublisherCreator.createdPublisher();
//        List<Publisher> publisherList = PublisherCreator.publisherList();
//
//        BDDMockito.when(repositoryMock.findAll()).thenReturn(publisherList);
//        BDDMockito.when(repositoryMock.findAll(ArgumentMatchers.any(Pageable.class))).thenReturn(getPage());
//        BDDMockito.when(repositoryMock.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(createdPublisher));
//        BDDMockito.when(repositoryMock.findByName(ArgumentMatchers.anyString())).thenReturn(Optional.of(createdPublisher));
//        BDDMockito.when(repositoryMock.save(ArgumentMatchers.any(Publisher.class))).thenReturn(createdPublisher);
//        BDDMockito.doNothing().when(repositoryMock).delete(ArgumentMatchers.any(Publisher.class));
//    }
//
//    Page<Publisher> getPage() {
//        PageRequest pageable = PageRequest.of(0, 20);
//        return new PageImpl<Publisher>(PublisherCreator.publisherList(), pageable, pageable.getPageSize());
//    }
//
//    @Test
//    void findByName_ReturnPublisherAsPublisherView_WhenSuccessful() {
//        Publisher publisher = PublisherCreator.createdPublisher();
//
//        Publisher servicePublisher = assertDoesNotThrow(() -> service.findByName(publisher.getName()));
//        assertEquals(publisher.getId(), servicePublisher.getId());
//        assertEquals(publisher.getName(), servicePublisher.getName());
//        assertEquals(publisher.getBooks(), servicePublisher.getBooks());
//    }
//
//    @Test
//    void findByName_ReturnResourceNotFoundException_WhenNotExists() {
//        BDDMockito.when(repositoryMock.findByName(ArgumentMatchers.anyString())).thenReturn(Optional.empty());
//
//        String invalidName = "";
//        ResourceNotFound resourceNotFound = assertThrows(ResourceNotFound.class,
//                () -> service.findByName(invalidName));
//
//        assertEquals("Publisher "+invalidName+" not found", resourceNotFound.getMessage());
//    }
//
//    @Test
//    void findById_ReturnPublisherAsPublisherView_WhenSuccessful() {
//        Publisher publisher = PublisherCreator.createdPublisher();
//        assertDoesNotThrow(() -> service.findById(publisher.getId()));
//        Publisher servicePublisher = service.findById(publisher.getId());
//        assertEquals(publisher.getId(), servicePublisher.getId());
//        assertEquals(publisher.getName(), servicePublisher.getName());
//        assertEquals(publisher.getBooks(), servicePublisher.getBooks());
//    }
//
//    @Test
//    void findById_ReturnResourceNotFoundException_WhenNotExists() {
//        BDDMockito.when(repositoryMock.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.empty());
//
//        long invalidId = -1;
//        ResourceNotFound resourceNotFound = assertThrows(ResourceNotFound.class,
//                () -> service.findById(invalidId));
//
//        assertEquals("Publisher with id "+invalidId+" not found", resourceNotFound.getMessage());
//    }
//
//    @Test
//    void delete_DeletePublisher_WhenSuccessful() {
//        assertDoesNotThrow(() -> service.delete(1L));
//    }
//
//    @Test
//    void delete_ReturnResourceNotFoundException_WhenNotExists() {
//        BDDMockito.when(repositoryMock.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.empty());
//
//        long invalidId = -1;
//        ResourceNotFound resourceNotFound = assertThrows(ResourceNotFound.class,
//                () -> service.delete(invalidId));
//
//        assertEquals("Publisher with id "+invalidId+" not found", resourceNotFound.getMessage());
//    }
//
//    @Test
//    void save_CreateNewPublisher_WhenSuccessful() {
//        PublisherRequestCreate publisherRequestCreate = PublisherRequestCreateCreator.create();
//        Publisher expectedPublisher = PublisherCreator.createdPublisher();
//        Publisher servicePublisher = assertDoesNotThrow(() -> service.save(publisherRequestCreate));
//        assertEquals(expectedPublisher.getId(), servicePublisher.getId());
//        assertEquals(expectedPublisher.getName(), servicePublisher.getName());
//        assertEquals(expectedPublisher.getBooks(), servicePublisher.getBooks());
//    }
//
//    @Test
//    void update_RemoveAndCreateNewPublisher_WhenSuccessful() {
//        PublisherRequestUpdate publisherRequestUpdate = PublisherRequestUpdateCreator.create();
//        Publisher expectedPublisher = PublisherCreator.createdPublisher();
//        Publisher servicePublisher = assertDoesNotThrow(() -> service.update(publisherRequestUpdate));
//        assertEquals(expectedPublisher.getId(), servicePublisher.getId());
//        assertEquals(expectedPublisher.getName(), servicePublisher.getName());
//        assertEquals(expectedPublisher.getBooks(), servicePublisher.getBooks());
//    }
//
//    @Test
//    void update_ReturnResourceNotFoundException_WhenNotExists() {
//        BDDMockito.when(repositoryMock.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.empty());
//
//        PublisherRequestUpdate invalidPublisherRequestUpdate = new PublisherRequestUpdate();
//        invalidPublisherRequestUpdate.setId(-1);
//        ResourceNotFound resourceNotFound = assertThrows(ResourceNotFound.class,
//                () -> service.update(invalidPublisherRequestUpdate));
//
//        assertEquals("Publisher with id "+invalidPublisherRequestUpdate.getId()+" not found", resourceNotFound.getMessage());
//    }
//
//    @Test
//    void listAll_ReturnAllPublishersAsPublisherViewList_WhenSuccessful() {
//        List<Publisher> servicePublisherList = assertDoesNotThrow(() -> service.listAll());
//        List<Publisher> expectedPublisherList = PublisherCreator.publisherList();
//
//        assertFalse(servicePublisherList.isEmpty());
//        assertFalse(servicePublisherList.size() != expectedPublisherList.size());
//        assertEquals(expectedPublisherList, servicePublisherList);
//    }
//
//    @Test
//    void listAll_ReturnEmptyPublisherViewList_WhenNoPublisher() {
//        BDDMockito.when(repositoryMock.findAll()).thenReturn(List.of());
//        List<Publisher> serviceList = assertDoesNotThrow(() -> service.listAll());
//        assertTrue(serviceList.isEmpty());
//    }
//
//    @Test
//    void list_ReturnPublisherListInsidePage_WhenSuccess() {
//        PageRequest pageable = PageRequest.of(0, 20);
//        Page<Publisher> expectedPublisherPage = getPage();
//        Page<Publisher> servicePublisherPage = assertDoesNotThrow(() ->
//                service.list(pageable));
//        assertFalse(servicePublisherPage.isEmpty());
//        assertEquals(expectedPublisherPage, servicePublisherPage);
//    }
//
//    @Test
//    void list_ReturnEmptyPublisherListInsidePage_WhenNoPublisher() {
//        BDDMockito.when(repositoryMock.findAll(ArgumentMatchers.any(Pageable.class))).thenReturn(Page.empty());
//        PageRequest pageable = PageRequest.of(0, 20);
//        Page<Publisher> servicePublisherPage = assertDoesNotThrow(() ->
//                service.list(pageable));
//        assertTrue(servicePublisherPage.isEmpty());
//    }
//}