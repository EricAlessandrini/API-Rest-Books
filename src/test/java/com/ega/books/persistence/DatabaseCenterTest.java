package com.ega.books.persistence;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ega.books.persistence.dao.DatabaseCenterImpl;
import com.ega.books.persistence.repository.AuthorRepository;
import com.ega.books.persistence.repository.BookRepository;
import com.ega.books.persistence.repository.GenreRepository;

@ExtendWith(MockitoExtension.class)
public class DatabaseCenterTest {
	
	@Mock
	private BookRepository bookRepository;
	@Mock
	private GenreRepository genreRepository;
	@Mock
	private AuthorRepository authorRepository;
	
	@InjectMocks
	private DatabaseCenterImpl databaseCenter;
	
	@Test
	void databaseCenter_findBookByTitle_test() {
		// Given
	}

}
