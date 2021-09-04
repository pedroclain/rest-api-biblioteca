package com.example.restapibiblioteca.exception;

import java.io.Serializable;

public class ResourceNotFound extends RuntimeException {
    public ResourceNotFound(String msg) {
        super(msg);
    }
}
