package com.ega.books.persistence.dao.books;

import java.util.List;

import com.ega.books.domain.entity.BookEntity;

public interface IBookDAO {

	//Buscar por id
	BookEntity findBookById(Long id);
	// Buscar por nombre
	
	// Buscar por autor

	// Buscar todos los libros
	List<BookEntity> findAllBooks();
	
	// Guardar nuevo libro
	void saveBook(BookEntity bookEntity);
	
	// Editar libro
	void updateBook(Long id, BookEntity bookEntity);
	
	// Borrar libro
	void deleteBookById(Long id);
	
}
