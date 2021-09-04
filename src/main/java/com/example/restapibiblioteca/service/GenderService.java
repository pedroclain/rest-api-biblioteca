package com.example.restapibiblioteca.service;

import com.example.restapibiblioteca.domain.Gender;
import com.example.restapibiblioteca.dto.request.GenderRequestCreate;
import com.example.restapibiblioteca.dto.request.GenderRequestUpdate;
import com.example.restapibiblioteca.exception.ResourceNotFound;
import com.example.restapibiblioteca.repository.BookRepository;
import com.example.restapibiblioteca.repository.GenderRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class GenderService {

    private final GenderRepository genderRepository;
    private final BookRepository bookRepository;

    public GenderService(GenderRepository repository, BookRepository bookRepository) {
        this.genderRepository = repository;
        this.bookRepository = bookRepository;
    }

    public List<Gender> listAll() {
        return genderRepository.findAll();
    }

    public Page<Gender> list(Pageable pageable) {
        return genderRepository.findAll(pageable);
    }

    public Page<Gender> findByName(String name, Pageable pageable) {
        return genderRepository.findByName(name, pageable);
    }


    public Gender findById(long id) {
        return genderRepository.findById(id).orElseThrow(() ->
                new ResourceNotFound(String.format("Gender with id %d not found", id)));
    }
    @Transactional
    public void delete(long id) {
        bookRepository.deleteAllBooksRelatedToGender(id);
        genderRepository.delete(id);
    }
//
//    @Transactional
//    public void deleteAllDisableGender() {
//        List<Gender> disableList = genderRepository.findAllDisableGender();
//        disableList.forEach((g) ->
//                bookRepository.deleteAllDisableBooksRelatedToGender(g.getId()));
//        genderRepository.deleteAllDisableGender();
//    }

    public Gender save(GenderRequestCreate genderRequest) {
        return genderRepository.save(genderRequest.getGender());
    }

    @Transactional
    public void update(GenderRequestUpdate genderRequest) {
        Gender gender = findById(genderRequest.getId());
        genderRepository.update(gender.getId(), gender.getName());
    }
}
