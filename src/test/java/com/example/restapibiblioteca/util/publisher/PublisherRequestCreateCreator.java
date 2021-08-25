package com.example.restapibiblioteca.util.publisher;

import com.example.restapibiblioteca.dto.PublisherRequestCreate;

public class PublisherRequestCreateCreator {

    public static PublisherRequestCreate create() {
        PublisherRequestCreate publisherRequestCreate = new PublisherRequestCreate();
        publisherRequestCreate.setName("publisher");
        return publisherRequestCreate;
    }
}
