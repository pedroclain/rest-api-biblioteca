package com.example.restapibiblioteca.exception.handler;

import com.example.restapibiblioteca.exception.ResourceNotFound;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.ValidationException;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger = LogManager.getLogger();

    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<ExceptionDetails<?>> handleResourceNotFoundException(
            HttpServletRequest request, Exception ex) {
        logger.error("handleResourceNotFoundException{}\n", request.getRequestURI(), ex);

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ExceptionDetails<>("Resource not found", List.of(ex.getMessage())));
    }
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ExceptionDetails<String>> handleValidationException(HttpServletRequest request, ValidationException ex) {
        logger.error("handleValidationException{}\n", request.getRequestURI(), ex);

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ExceptionDetails<>("Validation exception", List.of(ex.getMessage())));
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ExceptionDetails<String>> handleMissingServletRequestParameterException(HttpServletRequest request, MissingServletRequestParameterException ex) {
        logger.error("handleMissingServletRequestParameterException {}\n", request.getRequestURI(), ex);

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ExceptionDetails<>("Missing request parameter", List.of(ex.getMessage())));
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDetails<?>> handleInternalException(
            HttpServletRequest request, Exception ex) {

        logger.error("handleInternalException{}\n", request.getRequestURI(), ex);

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ExceptionDetails<>("Internal server error", List.of(ex.getMessage())));
    }
}
