package com.example.restapibiblioteca.exception.handler;

import java.util.List;

public class ExceptionDetails<T> {
    private String message;
    private List<T> details;

    public ExceptionDetails(String message, List<T> details) {
        this.message = message;
        this.details = details;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<T> getDetails() {
        return details;
    }

    public void setDetails(List<T> details) {
        this.details = details;
    }
}
