package com.ega.books.exception;

import lombok.Getter;

@Getter
public enum ErrorCatalog {

	// Errores en los datos del DTO
	INVALID_INPUT("API-001", "One or more fields are invalid"),
	// El libro que buscan no existe
	BOOK_NOT_FOUND("API-002", "Book doesn't exist in DB"),
	// El Id que pasaron es invalido
	INVALID_ID("API-003", "The id provided is not valid"),
	// El genero de libro ingresado no es valido
	INVALID_GENRE("API-004", "Invalid book's genre"),
	// El mapeo es imposible porque no hay objeto que mapear
	IMPOSSIBLE_MAPPING("API-005", "Impossible to map due to abscense of objects to map"),
	// La operacion que se intento por alguna razon no se pudo completar
	UNEXPECTED_ERROR("API-00X", "Something unexpectedly went wrong");
	
	private String errorCode;
	private String errorMessage;
	
	ErrorCatalog(String errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
	
}
