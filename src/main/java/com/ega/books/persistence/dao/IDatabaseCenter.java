package com.ega.books.persistence.dao;

import java.util.List;

import com.ega.books.domain.entity.AuthorEntity;
import com.ega.books.domain.entity.BookEntity;
import com.ega.books.domain.entity.GenreEntity;

public interface IDatabaseCenter {
	
	// METODOS PARA LIBROS

	// Buscar por nombre
		List<BookEntity> findBookByTitle(String bookTitle);

	// Buscar libros por nombre de autor
		List<BookEntity> findBooksByAuthorName(String authorName);
		
		// Buscar por genero
		List<BookEntity> findBooksByGenreName(String genreName);

		// Buscar todos los libros
		List<BookEntity> findAllBooks();
		
		// Guardar nuevo libro
		void saveBook(BookEntity bookEntity);
		
		// Editar libro
		void updateBook(Long id, BookEntity bookEntity);
		
		// Borrar libro
		void deleteBookById(Long id);
		
	// METODOS PARA GENEROS
		
		// Todos los generos
		List<GenreEntity> getAllGenres();
		
		// Genero por nombre
		GenreEntity getGenreByName(String name);
		
	// METODOS PARA AUTORES
		
		// Todos los autores
		List<AuthorEntity> getAllAuthors();
		
		// Autor por nombre
		AuthorEntity getAuthorByName(String authorName);

		void completeAuthorInfo(Long id, AuthorEntity authorEntity);
		
}
