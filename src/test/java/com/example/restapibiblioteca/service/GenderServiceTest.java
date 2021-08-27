//package com.example.restapibiblioteca.service;
//
//import com.example.restapibiblioteca.domain.Gender;
//import com.example.restapibiblioteca.dto.GenderRequestCreate;
//import com.example.restapibiblioteca.dto.GenderRequestUpdate;
//import com.example.restapibiblioteca.exception.ResourceNotFound;
//import com.example.restapibiblioteca.repository.GenderRepository;
//import com.example.restapibiblioteca.util.gender.GenderCreator;
//import com.example.restapibiblioteca.util.gender.GenderRequestCreateCreator;
//import com.example.restapibiblioteca.util.gender.GenderRequestUpdateCreator;
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
//public class GenderServiceTest {
//
//    @InjectMocks
//    private GenderService service;
//    @Mock
//    private GenderRepository repositoryMock;
//
//    @BeforeEach
//    void setUp() {
//        Gender createdGender = GenderCreator.createdGender();
//        List<Gender> genderList = GenderCreator.genderList();
//
//        BDDMockito.when(repositoryMock.findAll()).thenReturn(genderList);
//        BDDMockito.when(repositoryMock.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(createdGender));
//        BDDMockito.when(repositoryMock.findAll(ArgumentMatchers.any(Pageable.class))).thenReturn(getPage());
//        BDDMockito.when(repositoryMock.findByName(ArgumentMatchers.anyString())).thenReturn(Optional.of(createdGender));
//        BDDMockito.when(repositoryMock.save(ArgumentMatchers.any(Gender.class))).thenReturn(createdGender);
//        BDDMockito.doNothing().when(repositoryMock).delete(ArgumentMatchers.any(Gender.class));
//    }
//
//    Page<Gender> getPage() {
//        PageRequest pageable = PageRequest.of(0, 20);
//        return new PageImpl<Gender>(GenderCreator.genderList(), pageable, pageable.getPageSize());
//    }
//
//    @Test
//    void findByName_ReturnGenderAsGenderView_WhenSuccessful() {
//        Gender gender = GenderCreator.createdGender();
//
//        Gender serviceGender = assertDoesNotThrow(() -> service.findByName(gender.getName()));
//        assertEquals(gender.getId(), serviceGender.getId());
//        assertEquals(gender.getName(), serviceGender.getName());
//        assertEquals(gender.getBooks(), serviceGender.getBooks());
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
//        assertEquals("Gender "+invalidName+" not found", resourceNotFound.getMessage());
//    }
//
//    @Test
//    void findById_ReturnGenderAsGenderView_WhenSuccessful() {
//        Gender gender = GenderCreator.createdGender();
//        assertDoesNotThrow(() -> service.findById(gender.getId()));
//        Gender serviceGender = service.findById(gender.getId());
//        assertEquals(gender.getId(), serviceGender.getId());
//        assertEquals(gender.getName(), serviceGender.getName());
//        assertEquals(gender.getBooks(), serviceGender.getBooks());
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
//        assertEquals("Gender with id "+invalidId+" not found", resourceNotFound.getMessage());
//    }
//
//    @Test
//    void delete_DeleteGender_WhenSuccessful() {
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
//        assertEquals("Gender with id "+invalidId+" not found", resourceNotFound.getMessage());
//    }
//
//    @Test
//    void save_CreateNewGender_WhenSuccessful() {
//        GenderRequestCreate genderRequestCreate = GenderRequestCreateCreator.create();
//        Gender expectedGender = GenderCreator.createdGender();
//        Gender serviceGender = assertDoesNotThrow(() -> service.save(genderRequestCreate));
//        assertEquals(expectedGender.getId(), serviceGender.getId());
//        assertEquals(expectedGender.getName(), serviceGender.getName());
//        assertEquals(expectedGender.getBooks(), serviceGender.getBooks());
//    }
//
//    @Test
//    void update_RemoveAndCreateNewGender_WhenSuccessful() {
//        GenderRequestUpdate genderRequestUpdate = GenderRequestUpdateCreator.create();
//        Gender expectedGender = GenderCreator.createdGender();
//        Gender serviceGender = assertDoesNotThrow(() -> service.update(genderRequestUpdate));
//        assertEquals(expectedGender.getId(), serviceGender.getId());
//        assertEquals(expectedGender.getName(), serviceGender.getName());
//        assertEquals(expectedGender.getBooks(), serviceGender.getBooks());
//    }
//
//    @Test
//    void update_ReturnResourceNotFoundException_WhenNotExists() {
//        BDDMockito.when(repositoryMock.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.empty());
//
//        GenderRequestUpdate invalidGenderRequestUpdate = new GenderRequestUpdate();
//        invalidGenderRequestUpdate.setId(-1);
//        ResourceNotFound resourceNotFound = assertThrows(ResourceNotFound.class,
//                () -> service.update(invalidGenderRequestUpdate));
//
//        assertEquals("Gender with id "+invalidGenderRequestUpdate.getId()+" not found", resourceNotFound.getMessage());
//    }
//
//    @Test
//    void listAll_ReturnAllGendersAsGenderViewList_WhenSuccessful() {
//        List<Gender> serviceGenderList = assertDoesNotThrow(() -> service.listAll());
//        List<Gender> expectedGenderList = GenderCreator.genderList();
//
//        assertFalse(serviceGenderList.isEmpty());
//        assertFalse(serviceGenderList.size() != expectedGenderList.size());
//        assertEquals(expectedGenderList, serviceGenderList);
//    }
//
//    @Test
//    void listAll_ReturnEmptyGenderViewList_WhenNoGender() {
//        BDDMockito.when(repositoryMock.findAll()).thenReturn(List.of());
//        List<Gender> serviceList = assertDoesNotThrow(() -> service.listAll());
//        assertTrue(serviceList.isEmpty());
//    }
//
//    @Test
//    void list_ReturnGenderListInsidePage_WhenSuccess() {
//        PageRequest pageable = PageRequest.of(0, 20);
//        Page<Gender> expectedGenderPage = getPage();
//        Page<Gender> serviceGenderPage = assertDoesNotThrow(() ->
//                service.list(pageable));
//        assertFalse(serviceGenderPage.isEmpty());
//        assertEquals(expectedGenderPage, serviceGenderPage);
//    }
//
//    @Test
//    void list_ReturnEmptyGenderListInsidePage_WhenNoGender() {
//        BDDMockito.when(repositoryMock.findAll(ArgumentMatchers.any(Pageable.class))).thenReturn(Page.empty());
//        PageRequest pageable = PageRequest.of(0, 20);
//        Page<Gender> serviceGenderPage = assertDoesNotThrow(() ->
//                service.list(pageable));
//        assertTrue(serviceGenderPage.isEmpty());
//    }
//}