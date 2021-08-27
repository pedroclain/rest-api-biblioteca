package com.example.restapibiblioteca.service;

import com.example.restapibiblioteca.domain.Gender;
import com.example.restapibiblioteca.dto.GenderRequestCreate;
import com.example.restapibiblioteca.dto.GenderRequestUpdate;
import com.example.restapibiblioteca.exception.ResourceNotFound;
import com.example.restapibiblioteca.repository.GenderRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class GenderService {

    private final GenderRepository repository;

    public GenderService(GenderRepository repository) {
        this.repository = repository;
    }

    public Gender findByName(String name) {
        return find(name);
    }

    public Gender findById(long id) {
        return find(id);
    }

    private Gender find(long id) {
        return repository.findById(id).orElseThrow(() ->
                new ResourceNotFound(String.format("Gender with id %d not found", id)));
    }

    private Gender find(String name) {
        return repository.findByName(name).orElseThrow(() ->
                new ResourceNotFound(String.format("Gender %s not found", name)));
    }

    @Transactional
    public void delete(long id) {
        repository.delete(find(id));
    }

    public Gender save(GenderRequestCreate genderRequest) {
        return repository.save(genderRequest.getGender());
    }

    @Transactional
    public Gender update(GenderRequestUpdate genderRequest) {
        delete(genderRequest.getId());
        return repository.save(genderRequest.getGender());
    }

    public List<Gender> listAll() {
        return repository.findAll();
    }

    public Page<Gender> list(Pageable pageable) {
        return repository.findAll(pageable);
    }
}
