package com.example.restapibiblioteca.api.v1;

import com.example.restapibiblioteca.domain.Gender;
import com.example.restapibiblioteca.dto.request.GenderRequestCreate;
import com.example.restapibiblioteca.dto.request.GenderRequestUpdate;
import com.example.restapibiblioteca.dto.view.AutorView;
import com.example.restapibiblioteca.dto.view.GenderView;
import com.example.restapibiblioteca.service.GenderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Api("Endpoint to manage Genders")
@RestController
@RequestMapping("api/v1/gender")
public class GenderEndpoint{

    private final GenderService service;

    public GenderEndpoint(GenderService service) {
        this.service = service;
    }

    @ApiOperation(value = "Return a list of all Genders", response = GenderView.class)
    @GetMapping("all")
    public ResponseEntity<?> listAll() {
        List<GenderView> genderViewList = service.listAll().stream()
                .map(GenderView::new).collect(Collectors.toList());
        return new ResponseEntity<>(genderViewList, HttpStatus.OK);
    }

    @ApiOperation(value = "Return all Gender inside a pages", response = GenderView.class)
    @GetMapping
    public ResponseEntity<?> list(Pageable pageable) {
        return new ResponseEntity<>(genderPageToGenderViewPage(service.list(pageable)), HttpStatus.OK);
    }

    @ApiOperation(value = "Return a Gender by id number", response = GenderView.class)
    @GetMapping(value = "find/id/{id}")
    public ResponseEntity<?> findById(@ApiParam("A part of gender name to find") @PathVariable Long id) {
        return new ResponseEntity<>(new GenderView(service.findById(id)), HttpStatus.OK);
    }

    @ApiOperation(value = "Return Gender by name inside a page", response = GenderView.class)
    @GetMapping("find/name/{name}")
    public ResponseEntity<?> findByName(@PathVariable String name, Pageable pageable) {
        Page<Gender> genderPage = service.findByName(name, pageable);
        return new ResponseEntity<>(genderPageToGenderViewPage(genderPage), HttpStatus.OK);
    }

    @ApiOperation(value = "Create a new Gender", response = GenderView.class, code = 201)
    @PostMapping
    public ResponseEntity<?> save(@ApiParam("Json request gender body to save") @RequestBody @Valid GenderRequestCreate genderRequest) {
        return new ResponseEntity<>(new GenderView(service.save(genderRequest)), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update a Gender by id number", code = 204)
    @PutMapping
    public ResponseEntity<?> update(@ApiParam("Json request gender body to update") @RequestBody @Valid GenderRequestUpdate genderRequest) {
        service.update(genderRequest);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "Soft delete a Gender", code = 204)
    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@ApiParam("Gender id number to delete") @PathVariable long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

//    @DeleteMapping("all-disable")
//    public ResponseEntity<?> deleteAllDisable() {
//        service.deleteAllDisableGender();
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }

    private Page<GenderView> genderPageToGenderViewPage(Page<Gender> page) {
        List<GenderView> list = page.getContent().stream()
                .map(GenderView::new).collect(Collectors.toList());
        return new PageImpl<>(list);
    }
}
