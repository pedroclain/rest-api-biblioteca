package com.example.restapibiblioteca.util.publisher;

import com.example.restapibiblioteca.dto.PublisherRequestUpdate;

public class PublisherRequestUpdateCreator {

    public static PublisherRequestUpdate create() {
        PublisherRequestUpdate publisherRequestUpdate = new PublisherRequestUpdate();
        publisherRequestUpdate.setName("publisher");
        publisherRequestUpdate.setId(1);
        return publisherRequestUpdate;
    }
}
