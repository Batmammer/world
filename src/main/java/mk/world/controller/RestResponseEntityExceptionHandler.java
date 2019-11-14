package mk.world.controller;

import mk.world.exception.CountryNotFoundException;
import org.hibernate.exception.JDBCConnectionException;
import org.postgresql.util.PSQLException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler
        extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value
            = { CountryNotFoundException.class })
    protected ResponseEntity<Object> countryNotFound(
            RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(ex, "INVALID_COUNTRY_CODE",
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler(value
            = { PSQLException.class })
    protected ResponseEntity<Object> connectionException(
            RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(ex, "INTERNAL_ERROR",
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler(value
            = { JDBCConnectionException.class })
    protected ResponseEntity<Object> genericDbException(
            RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(ex, "INTERNAL_ERROR",
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
}