package com.ega.books.exception;

import com.ega.books.exception.exceptions.DatabaseException;
import com.ega.books.exception.exceptions.MappingException;
import com.ega.books.utils.ErrorCatalog;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalControllerAdvice {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> invalidInputExceptionHandler(MethodArgumentNotValidException exception) {
		BindingResult errors = exception.getBindingResult();

		List<String> errorDetails = errors.getFieldErrors()
				.stream()
				.map(FieldError::getDefaultMessage)
				.toList();

		ErrorResponse errorResponse = ErrorResponse.builder()
				.code(ErrorCatalog.INVALID_INPUT.getErrorCode())
				.message(ErrorCatalog.INVALID_INPUT.getErrorMessage())
				.details(errorDetails)
				.timestamp(LocalDateTime.now())
				.build();
				
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<ErrorResponse> notFoundInDatabaseHandler(DatabaseException e) {
		ErrorResponse errorResponse = ErrorResponse.builder()
				.code(e.getErrorCode())
				.message(e.getErrorMessage())
				.timestamp(LocalDateTime.now())
				.build();
		
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MappingException.class)
	public ResponseEntity<ErrorResponse> impossibleMappingExceptionHandler(MappingException e) {
		ErrorResponse errorResponse = ErrorResponse.builder()
				.code(e.getErrorCode())
				.message(e.getErrorMessage())
				.timestamp(LocalDateTime.now())
				.build();

		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

}
