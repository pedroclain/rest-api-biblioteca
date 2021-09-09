package com.example.restapibiblioteca.exception.handler;

import com.example.restapibiblioteca.exception.ResourceNotFound;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.ValidationException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Optional.ofNullable;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<ExceptionDetails<?>> handleResourceNotFoundException(
            HttpServletRequest request, Exception ex) {
        logger.error("handleResourceNotFoundException{}\n", request.getRequestURI(), ex);

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ExceptionDetails<>("Resource not found", List.of(ex.getMessage())));
    }

    private final Logger logger = LogManager.getLogger();
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
                .body(new ExceptionDetails<>("Missing request parameter", List.of(ofNullable(ex.getMessage()).orElse(""))));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ExceptionDetails<Map<String, String>>> handleMethodArgumentTypeMismatchException(HttpServletRequest request, MethodArgumentTypeMismatchException ex) {
        logger.error("handleMethodArgumentTypeMismatchException {}\n", request.getRequestURI(), ex);

        Map<String, String> details = new HashMap<>();
        details.put("paramName", ex.getName());
        details.put("paramValue", ofNullable(ex.getValue()).map(Object::toString).orElse(""));
        details.put("errorMessage", ex.getMessage());

        return ResponseEntity
                .badRequest()
                .body(new ExceptionDetails<>("Method argument type mismatch", List.of(details)));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionDetails<Map<String, String>>> methodArgumentNotValidException(
            HttpServletRequest req, MethodArgumentNotValidException ex) {

        List<Map<String, String>> details = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(fieldError -> {
            Map<String, String> detail = new HashMap<>();
            detail.put("Object Name", fieldError.getObjectName());
            detail.put("Field", fieldError.getField());
            detail.put("Rejected Value", String.valueOf(fieldError.getRejectedValue()));
            detail.put("Message", fieldError.getDefaultMessage());
            details.add(detail);
        });
        logger.error("methodArgumentNotValidException{}\n", req.getRequestURI(), ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ExceptionDetails<>("Method argument validation failed", details));
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
