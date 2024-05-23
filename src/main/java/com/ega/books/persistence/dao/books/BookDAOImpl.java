package com.ega.books.persistence.dao.books;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.ega.books.domain.entity.BookEntity;
import com.ega.books.domain.entity.GenreEntity;
import com.ega.books.exception.exceptions.BookNotFoundException;
import com.ega.books.exception.exceptions.InvalidGenreException;
import com.ega.books.persistence.repository.BookRepository;
import com.ega.books.persistence.repository.GenreRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class BookDAOImpl implements IBookDAO {

	private final BookRepository bookRepository;
	private final GenreRepository genreRepository;
	
	@Override
	public BookEntity findBookById(Long id) {
		Optional<BookEntity> bookEntity = bookRepository.findById(id);
		
		if(bookEntity.isEmpty()) {
			throw new BookNotFoundException();
		}
		
		return bookEntity.get();
	}

	@Override
	public List<BookEntity> findAllBooks() {
		List<BookEntity> allBooks = bookRepository.findAll();
		return allBooks;
	}

	@Override
	public void saveBook(BookEntity bookEntity) {
		this.setGenres(bookEntity);
		bookRepository.save(bookEntity);
	}

	@Override
	public void updateBook(Long id, BookEntity bookEntity) {
		Optional<BookEntity> bookSaved = bookRepository.findById(id);
		
		this.setGenres(bookEntity);

		if(bookSaved.isEmpty()) {
			throw new BookNotFoundException();
		}
		
		BookEntity book = bookSaved.get();
		if(bookEntity.getTitle() != null) {
			book.setTitle(bookEntity.getTitle());
		}
		if(bookEntity.getGenre() != null) {
			book.setGenre(bookEntity.getGenre());
		}
		
		bookRepository.save(book);
	}

	@Override
	public void deleteBookById(Long id) {
		Optional<BookEntity> bookEntity = bookRepository.findById(id);
		
		if(bookEntity.isEmpty()) {
			throw new BookNotFoundException();
		}
		
		bookRepository.deleteById(id);
	}
	
	// Setear generos en libro en realidad
	private void setGenres(BookEntity bookEntity) {
		Set<GenreEntity> bookGenres = new HashSet<GenreEntity>();
		for(GenreEntity genre : bookEntity.getGenre()) {
			GenreEntity genreSaved = genreRepository.findByName(genre.getName());
			if(genreSaved == null) {
				throw new InvalidGenreException();
			} else {
				bookGenres.add(genreSaved);
			}
		}
		bookEntity.setGenre(bookGenres);
	}

	
}
