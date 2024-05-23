package com.ega.books.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ega.books.exception.exceptions.*;

@RestControllerAdvice
public class GlobalControllerAdvice {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> invalidInputExceptionHandler(MethodArgumentNotValidException exception) {
		BindingResult errors = exception.getBindingResult();
		ErrorResponse errorResponse = ErrorResponse.builder()
				.code(ErrorCatalog.INVALID_INPUT.getErrorCode())
				.message(ErrorCatalog.INVALID_INPUT.getErrorMessage())
				.details(errors.getFieldErrors()
						.stream()
						.map(fieldError -> fieldError.getDefaultMessage())
						.toList())
				.timestamp(LocalDateTime.now())
				.build();
				
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(BookNotFoundException.class)
	public ResponseEntity<ErrorResponse> notFoundInDatabaseHandler() {
		ErrorResponse errorResponse = ErrorResponse.builder()
				.code(ErrorCatalog.BOOK_NOT_FOUND.getErrorCode())
				.message(ErrorCatalog.BOOK_NOT_FOUND.getErrorMessage())
				.timestamp(LocalDateTime.now())
				.build();
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(InvalidGenreException.class)
	public ResponseEntity<ErrorResponse> invalidGenreExceptionHandler(){
		ErrorResponse errorResponse = ErrorResponse.builder()
				.code(ErrorCatalog.INVALID_GENRE.getErrorCode())
				.message(ErrorCatalog.INVALID_GENRE.getErrorMessage())
				.timestamp(LocalDateTime.now())
				.build();
		
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ImpossibleMappingException.class)
	public ResponseEntity<ErrorResponse> ImpossibleMappingExceptionHandler() {
		ErrorResponse errorResponse = ErrorResponse.builder()
				.code(ErrorCatalog.IMPOSSIBLE_MAPPING.getErrorCode())
				.message(ErrorCatalog.IMPOSSIBLE_MAPPING.getErrorMessage())
				.timestamp(LocalDateTime.now())
				.build();
		
		return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
	}
	
}
