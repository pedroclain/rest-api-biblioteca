package com.example.restapibiblioteca.service;

import com.example.restapibiblioteca.domain.Autor;
import com.example.restapibiblioteca.dto.request.AutorRequestCreate;
import com.example.restapibiblioteca.dto.request.AutorRequestUpdate;
import com.example.restapibiblioteca.exception.ResourceNotFound;
import com.example.restapibiblioteca.repository.AutorRepository;
import com.example.restapibiblioteca.repository.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AutorService {

    private final AutorRepository autorRepository;
    private final BookRepository bookRepository;

    public List<Autor> listAll() {
        return autorRepository.findAll();
    }

    public Page<Autor> list(Pageable pageable) {
        return autorRepository.findAll(pageable);
    }

    public AutorService(AutorRepository autorRepository, BookRepository bookRepository) {
        this.autorRepository = autorRepository;
        this.bookRepository = bookRepository;
    }

    public Page<Autor> findByName(String name, Pageable pageable) {
        return autorRepository.findByName(name, pageable);
    }


    public Autor findById(long id) {
        return autorRepository.findById(id).orElseThrow(() ->
                new ResourceNotFound(String.format("Autor with id %d not found", id)));
    }

    @Transactional
    public void delete(long id) {
        bookRepository.deleteAllBooksRelatedToGender(id);
        autorRepository.delete(id);
    }

//    @Transactional
//    public void deleteAllDisableAutor() {
//        List<Autor> disableList = autorRepository.findAllDisableAutor();
//        disableList.forEach((a) ->
//                bookRepository.deleteAllDisableBooksRelatedToAutor(a.getId()));
//        autorRepository.deleteAllDisableAutor();
//    }

    public Autor save(AutorRequestCreate genderRequest) {
        return autorRepository.save(genderRequest.getAutor());
    }

    @Transactional
    public void update(AutorRequestUpdate genderRequest) {
        autorRepository.update(genderRequest.getId(), genderRequest.getName());
    }
}
