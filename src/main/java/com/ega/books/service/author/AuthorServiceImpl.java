package com.ega.books.service.author;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ega.books.domain.dto.AuthorDTO;
import com.ega.books.domain.dto.BookDTO;
import com.ega.books.domain.entity.BookEntity;
import com.ega.books.persistence.dao.IDatabaseCenter;
import com.ega.books.utils.AuthorMapper;
import com.ega.books.utils.BookMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements IAuthorService{

	private final IDatabaseCenter databaseCenter;
	private final AuthorMapper authorMapper;
	private final BookMapper bookMapper;

	@Override
	public AuthorDTO getAuthorByName(String authorName) {
		AuthorDTO author = authorMapper.entityToDTO(databaseCenter.getAuthorByName(authorName));
		return author;
	}

	@Override
	public List<AuthorDTO> getAllAuthors() {
		List<AuthorDTO> authors = databaseCenter.getAllAuthors().stream()
				.map(authorEntity -> authorMapper.entityToDTO(authorEntity))
				.collect(Collectors.toList());
		return authors;
	}

	@Override
	public Set<BookDTO> getBooksByAuthor(String authorName) {
		Set<BookDTO> booksByAuthor = new HashSet<>();
		for(BookEntity book : databaseCenter.getAuthorByName(authorName).getBooksWritten()) {
			BookDTO bookDTO = bookMapper.entityToDTO(book);
			booksByAuthor.add(bookDTO);
		}
				
		return booksByAuthor;
	}

	@Override
	public void completeAuthorInfo(AuthorDTO authorDTO) {
		databaseCenter.completeAuthorInfo(authorMapper.dtoToEntity(authorDTO));
	}
	
	
}
