package com.ega.books.exception;

import java.time.LocalDateTime;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

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
						.map(DefaultMessageSourceResolvable::getDefaultMessage)
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
	
	@ExceptionHandler(EmptyListFromDatabaseException.class)
	public ResponseEntity<ErrorResponse> emptyListOfBooksExceptionHandler() {
		ErrorResponse errorResponse = ErrorResponse.builder()
				.code(ErrorCatalog.EMPTY_LIST_FROM_DATABASE.getErrorCode())
				.message(ErrorCatalog.EMPTY_LIST_FROM_DATABASE.getErrorMessage())
				.timestamp(LocalDateTime.now())
				.build();
		
		return new ResponseEntity<>(errorResponse, HttpStatus.NO_CONTENT);
	}
	
	@ExceptionHandler(InvalidGenreException.class)
	public ResponseEntity<ErrorResponse> invalidGenreExceptionHandler(){
		ErrorResponse errorResponse = ErrorResponse.builder()
				.code(ErrorCatalog.INVALID_OR_DOESNT_EXIST_GENRE.getErrorCode())
				.message(ErrorCatalog.INVALID_OR_DOESNT_EXIST_GENRE.getErrorMessage())
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
	
	@ExceptionHandler(AuthorNotFoundException.class)
	public ResponseEntity<ErrorResponse> authorNotFoundException() {
		ErrorResponse errorResponse = ErrorResponse.builder()
				.code(ErrorCatalog.AUTHOR_NOT_FOUND.getErrorCode())
				.message(ErrorCatalog.AUTHOR_NOT_FOUND.getErrorMessage())
				.timestamp(LocalDateTime.now())
				.build();
		
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}
}
