package com.example.restapibiblioteca.service;

import com.example.restapibiblioteca.domain.Book;
import com.example.restapibiblioteca.domain.Gender;
import com.example.restapibiblioteca.dto.GenderRequestCreate;
import com.example.restapibiblioteca.dto.GenderRequestUpdate;
import com.example.restapibiblioteca.dto.GenderView;
import com.example.restapibiblioteca.exception.ResourceNotFound;
import com.example.restapibiblioteca.repository.GenderRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GenderService {

    private final GenderRepository repository;

    public GenderService(GenderRepository repository) {
        this.repository = repository;
    }

    public GenderView findByName(String name) {
        return new GenderView(find(name));
    }

    public GenderView findById(long id) {
        return new GenderView(find(id));
    }

    private Gender find(long id) {
        return repository.findById(id).orElseThrow(() ->
                new ResourceNotFound("Gender with id "+id+" not found"));
    }

    private Gender find(String name) {
        return repository.findByName(name).orElseThrow(() ->
                new ResourceNotFound("Gender "+name+" not found"));
    }

    @Transactional
    public void delete(long id) {
        repository.delete(find(id));
    }

    public GenderView save(GenderRequestCreate genderRequest) {
        return new GenderView(repository.save(genderRequest.getGender()));
    }

    @Transactional
    public GenderView update(GenderRequestUpdate genderRequest) {
        delete(genderRequest.getId());
        return new GenderView(repository.save(genderRequest.getGender()));
    }

    public List<GenderView> list() {
        return repository.findAll().stream().map(GenderView::new).collect(Collectors.toList());
    }

    public List<GenderView> list(Pageable pageable) {
        return repository.findAll(pageable).get().map(GenderView::new)
                .collect(Collectors.toList());
    }
}
