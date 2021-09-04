package com.example.restapibiblioteca.api.v1;

import com.example.restapibiblioteca.domain.Publisher;
import com.example.restapibiblioteca.dto.request.PublisherRequestCreate;
import com.example.restapibiblioteca.dto.request.PublisherRequestUpdate;
import com.example.restapibiblioteca.dto.view.AutorView;
import com.example.restapibiblioteca.dto.view.PublisherView;
import com.example.restapibiblioteca.service.PublisherService;
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

@Api(value = "Endpoint to manage Publisher")
@RestController
@RequestMapping("api/v1/publisher")
public class PublisherEndpoint{

    private final PublisherService service;

    public PublisherEndpoint(PublisherService service) {
        this.service = service;
    }

    @ApiOperation(value = "Return a list of all Publisher", response = PublisherView.class)
    @GetMapping("all")
    public ResponseEntity<?> listAll() {
        List<PublisherView> publisherViewList = service.listAll().stream()
                .map(PublisherView::new).collect(Collectors.toList());
        return new ResponseEntity<>(publisherViewList, HttpStatus.OK);
    }

    @ApiOperation(value = "Return all Publisher inside a pages", response = PublisherView.class)
    @GetMapping
    public ResponseEntity<?> list(Pageable pageable) {
        return new ResponseEntity<>(publisherPageToPublisherViewPage(service.list(pageable)), HttpStatus.OK);
    }

    @ApiOperation(value = "Return a Publisher by id number", response = PublisherView.class)
    @GetMapping("find/id/{id}")
    public ResponseEntity<?> findById(@ApiParam("Publisher id number to find") @PathVariable Long id) {
        return new ResponseEntity<>(new PublisherView(service.findById(id)), HttpStatus.OK);
    }

    @ApiOperation(value = "Return Publisher by name inside a page", response = PublisherView.class)
    @GetMapping("find/name/{name}")
    public ResponseEntity<?> findByName(@ApiParam("A part of publisher name to find") @PathVariable String name, Pageable pageable) {
        return new ResponseEntity<>(publisherPageToPublisherViewPage(service.findByName(name, pageable)), HttpStatus.OK);
    }

    @ApiOperation(value = "Create a new Publisher", response = PublisherView.class, code = 201)
    @PostMapping
    public ResponseEntity<?> save(@ApiParam("Json request publisher body to save") @RequestBody @Valid PublisherRequestCreate publisherRequest) {
        return new ResponseEntity<>(new PublisherView(service.save(publisherRequest)), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update a Publisher by id number", code = 204)
    @PutMapping
    public ResponseEntity<?> update(@ApiParam("Json request publisher body to update") @RequestBody @Valid PublisherRequestUpdate publisherRequest) {
        return new ResponseEntity<>(new PublisherView(service.update(publisherRequest)), HttpStatus.OK);
    }

    @ApiOperation(value = "Soft delete a Publisher", code = 204)
    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@ApiParam("Publisher id number to delete") @PathVariable long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

//    @DeleteMapping("all-disable")
//    public ResponseEntity<?> deleteAllDisable() {
//        service.deleteAllDisablePublisher();
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }

    private Page<PublisherView> publisherPageToPublisherViewPage(Page<Publisher> page) {
        List<PublisherView> list = page.getContent().stream()
                .map(PublisherView::new).collect(Collectors.toList());
        return new PageImpl<>(list);
    }
}
