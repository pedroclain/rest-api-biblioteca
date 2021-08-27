package com.example.restapibiblioteca.api.v1;

import com.example.restapibiblioteca.dto.PublisherRequestCreate;
import com.example.restapibiblioteca.dto.PublisherRequestUpdate;
import com.example.restapibiblioteca.dto.PublisherView;
import com.example.restapibiblioteca.service.PublisherService;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/publisher")
public class PublisherEndpoint{

    private final PublisherService service;

    public PublisherEndpoint(PublisherService service) {
        this.service = service;
    }

    @GetMapping("{id}")
    public ResponseEntity<?> find(@PathVariable Long id) {
        return new ResponseEntity<>(new PublisherView(service.findById(id)), HttpStatus.OK);
    }

    @GetMapping("name/{name}")
    public ResponseEntity<?> find(@PathVariable String name) {
        return new ResponseEntity<>(new PublisherView(service.findByName(name)), HttpStatus.OK);
    }

    @GetMapping("all")
    public ResponseEntity<?> listAll() {
        List<PublisherView> publisherViewList = service.listAll().stream()
                .map(PublisherView::new).collect(Collectors.toList());
        return new ResponseEntity<>(publisherViewList, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> list(Pageable pageable) {
        List<PublisherView> publisherViewList = service.list(pageable).stream()
                .map(PublisherView::new).collect(Collectors.toList());
        PageImpl<PublisherView> publisherViewPage = new PageImpl<>(publisherViewList, pageable, pageable.getPageSize());
        return new ResponseEntity<>(publisherViewPage, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid PublisherRequestCreate publisherRequest) {
        return new ResponseEntity<>(new PublisherView(service.save(publisherRequest)), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody @Valid PublisherRequestUpdate publisherRequest) {
        return new ResponseEntity<>(new PublisherView(service.update(publisherRequest)), HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
