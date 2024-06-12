package com.ega.books.persistence.dao;

import com.ega.books.domain.entity.AuthorEntity;
import com.ega.books.domain.entity.BookEntity;
import com.ega.books.domain.entity.GenreEntity;
import com.ega.books.exception.exceptions.AuthorNotFoundException;
import com.ega.books.exception.exceptions.BookNotFoundException;
import com.ega.books.exception.exceptions.EmptyListFromDatabaseException;
import com.ega.books.exception.exceptions.InvalidGenreException;
import com.ega.books.persistence.repository.AuthorRepository;
import com.ega.books.persistence.repository.BookRepository;
import com.ega.books.persistence.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class DatabaseCenterImpl implements IDatabaseCenter{
	
	private final BookRepository bookRepository;
	private final GenreRepository genreRepository;
	private final AuthorRepository authorRepository;
	
	
	// IMPLEMENTACIONES PARA METODOS DE LIBROS
	
	// BUSCAR LIBRO POR TITULO
	@Override
	public List<BookEntity> findBookByTitle(String bookTitle) {
		List<BookEntity> booksFound = bookRepository.findBookByTitle(bookTitle);
		
		if(booksFound.isEmpty()) {
			throw new EmptyListFromDatabaseException();
		}
		
		return booksFound;
	}

	@Override
	public List<BookEntity> findBooksByAuthorName(String authorName) {
		List<BookEntity> booksFound = bookRepository.findBooksByAuthorName(authorName);

		if(booksFound.isEmpty()) {
			throw new EmptyListFromDatabaseException();
		}

        return booksFound;
	}

	@Override
	public List<BookEntity> findBooksByGenreName(String genreName) {
		List<BookEntity> booksFound = bookRepository.findBooksByGenreName(genreName);

		if(booksFound.isEmpty()) {
			throw new EmptyListFromDatabaseException();
		}
		return booksFound;
	}

	// BUSCAR TODOS LOS LIBROS
	@Override
	public List<BookEntity> findAllBooks() {
		List<BookEntity> allBooks = bookRepository.findAll();
		
		if(allBooks.isEmpty()) {
			throw new EmptyListFromDatabaseException();
		}
		
		return allBooks;
	}

	// GUARDAR UN NUEVO LIBRO
	@Override
	public void saveBook(BookEntity bookEntity) {
		this.validateAndSetGenres(bookEntity);
		this.checkAuthor(bookEntity);
		bookRepository.save(bookEntity);
	}

	// ACTUALIZAR UN NUEVO LIBRO 
	@Override
	public void updateBook(Long id, BookEntity bookEntity) {
		BookEntity bookSaved = bookRepository.findById(id)
				.orElseThrow(BookNotFoundException::new);

		if(bookEntity.getTitle() != null) {
			bookSaved.setTitle(bookEntity.getTitle());
		}
		if(bookEntity.getGenre() != null) {
			this.validateAndSetGenres(bookEntity);
			bookSaved.setGenre(bookEntity.getGenre());
		}
		if(bookEntity.getAuthor() != null) {
			this.checkAuthor(bookEntity);
			bookSaved.setAuthor(bookEntity.getAuthor());
		}
		
		bookRepository.save(bookSaved);
	}

	// BORRAR UN LIBRO EXISTENTE
	@Override
	public void deleteBookById(Long id) {
		if(!bookRepository.existsById(id)) {
			throw new BookNotFoundException();
		}
		
		bookRepository.deleteById(id);
	}
	
	// COMPROBAR LOS GENEROS DE UN LIBRO Y AGREGARLOS
	private void validateAndSetGenres(BookEntity bookEntity) {
		
		Set<GenreEntity> bookGenres = new HashSet<>();
		for(GenreEntity genre : bookEntity.getGenre()) {
			GenreEntity genreSaved = genreRepository.findByNameIgnoreCase(genre.getName());
			if(genreSaved == null) {
				throw new InvalidGenreException();
			} else {
				bookGenres.add(genreSaved);
			}
		}
		bookEntity.setGenre(bookGenres);
	}
	
	// COMPROBAR EL ESTADO DEL AUTOR EN LA BASE DE DATOS
	
	private void checkAuthor(BookEntity bookEntity) {
		AuthorEntity author = authorRepository.findAuthorByName(bookEntity.getAuthor().getFullName());
		
		if(author != null) {
			bookEntity.setAuthor(author);
		}
	}
	
	// METODOS PARA EL REPOSITORIO DE GENERO
	
	// BUSCAR INFORMACION DE LOS GENEROS GUARDADOS
	public List<GenreEntity> getAllGenres() {
		return genreRepository.findAll();
	}
	
	// BUSCAR UN LIBRO POR EL GENERO
	public GenreEntity getGenreByName(String name) {
		GenreEntity genreEntity = genreRepository.findByNameIgnoreCase(name);
		
		if(genreEntity == null) {
			throw new InvalidGenreException();
		}
		
		return genreEntity;
	}

	// IMPLEMENTACION DE METODOS PARA AUTORES
	
	@Override
	public List<AuthorEntity> getAllAuthors() {
		List<AuthorEntity> authorsList = authorRepository.findAll();
		
		if(authorsList.isEmpty()) {
			throw new EmptyListFromDatabaseException();
		}
		
		return authorsList;
	}

	@Override
	public AuthorEntity getAuthorByName(String authorName) {
		AuthorEntity author = authorRepository.findAuthorByName(authorName);
		
		if(author == null) {
			throw new AuthorNotFoundException();
		}
		
		return author;
	}

	@Override
	public void completeAuthorInfo(Long id, AuthorEntity authorEntity) {
		AuthorEntity savedAuthor = authorRepository.findById(id)
				.orElseThrow(AuthorNotFoundException::new);

		savedAuthor.setFullName(authorEntity.getFullName());
		savedAuthor.setBirthday(authorEntity.getBirthday());
		savedAuthor.setPlaceOfBirth(authorEntity.getPlaceOfBirth());
		savedAuthor.setNationality(authorEntity.getNationality());

		authorRepository.save(savedAuthor);
	}
}

