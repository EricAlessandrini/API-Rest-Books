package com.ega.books.exception;

import com.ega.books.exception.exceptions.DatabaseException;
import com.ega.books.exception.exceptions.MappingException;
import com.ega.books.utils.ErrorCatalog;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GlobalControllerAdviceTest {

    @Mock
    private BindingResult bindingResult;

    @InjectMocks
    private GlobalControllerAdvice globalControllerAdvice;

    @BeforeEach
    void setup() {
        this.globalControllerAdvice = new GlobalControllerAdvice();
    }

    @Test
    void testNotFoundInDatabaseExceptionSuccessful() {
        DatabaseException e = new DatabaseException(
                ErrorCatalog.BOOK_NOT_FOUND.getErrorCode(),
                ErrorCatalog.BOOK_NOT_FOUND.getErrorMessage()
        );

        ResponseEntity<ErrorResponse> response = globalControllerAdvice.notFoundInDatabaseHandler(e);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("API-002", Objects.requireNonNull(response.getBody()).getCode());
        assertEquals("Book doesn't exist in our DB", response.getBody().getMessage());
        assertNull(response.getBody().getDetails());
        assertThat(response.getBody().getTimestamp()).isNotNull();
    }

    @Test
    void testInvalidInputExceptionHandlerSuccessful() {
        FieldError fieldError = new FieldError("bookDTO", "title", "Invalid title");
        when(bindingResult.getFieldErrors()).thenReturn(List.of(fieldError));

        MethodArgumentNotValidException ex = new MethodArgumentNotValidException(null, bindingResult);

        ResponseEntity<ErrorResponse> response = globalControllerAdvice.invalidInputExceptionHandler(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("API-001", Objects.requireNonNull(response.getBody()).getCode());
        assertEquals("One or more fields are invalid", response.getBody().getMessage());
        assertThat(response.getBody().getDetails()).isNotNull();
        assertThat(response.getBody().getDetails().get(0)).contains(fieldError.getField());
        assertThat(response.getBody().getDetails().get(0)).contains(fieldError.getDefaultMessage());
        assertThat(response.getBody().getTimestamp()).isNotNull();
    }

    @Test
    void impossibleMappingExceptionHandler() {
        MappingException ex = new MappingException(
                ErrorCatalog.IMPOSSIBLE_MAPPING.getErrorCode(),
                ErrorCatalog.IMPOSSIBLE_MAPPING.getErrorMessage()
        );

        ResponseEntity<ErrorResponse> response = globalControllerAdvice.impossibleMappingExceptionHandler(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("API-005", Objects.requireNonNull(response.getBody()).getCode());
        assertEquals("Mapping becomes impossible because there is no object to map", response.getBody().getMessage());
        assertThat(response.getBody().getDetails()).isNull();
        assertThat(response.getBody().getTimestamp()).isNotNull();
        assertThat(response.getBody().getTimestamp()).isNotNull();
    }
}
