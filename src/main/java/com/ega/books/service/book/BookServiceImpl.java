package com.ega.books.service.book;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ega.books.domain.dto.BookDTO;
import com.ega.books.domain.entity.BookEntity;
import com.ega.books.persistence.dao.IDatabaseCenter;
import com.ega.books.utils.BookMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements IBookService{
	
	private final IDatabaseCenter dbCenter;
	private final BookMapper bookMapper;
	
	@Override
	public List<BookDTO> findBookByTitle(String bookTitle) {
        return dbCenter.findBookByTitle(bookTitle).stream()
				.map(bookMapper::entityToDTO)
				.collect(Collectors.toList());
	}

	@Override
	public List<BookDTO> findBooksByAuthorName(String authorName) {
		return dbCenter.findBooksByAuthorName(authorName)
				.stream()
				.map(bookMapper::entityToDTO)
				.collect(Collectors.toList());
	}

	@Override
	public List<BookDTO> findAllBooks() {
		return dbCenter.findAllBooks().stream()
				.map(bookMapper::entityToDTO)
				.collect(Collectors.toList());
	}

	@Override
	public void saveBook(BookDTO bookDTO) {
		BookEntity bookEntity = bookMapper.dtoToEntity(bookDTO);
		dbCenter.saveBook(bookEntity);
	}

	@Override
	public void updateBook(Long id, BookDTO bookDTO) {
		BookEntity bookEntity = bookMapper.dtoToEntity(bookDTO);
		dbCenter.updateBook(id, bookEntity);
	}

	@Override
	public void deleteBook(Long id) {
		dbCenter.deleteBookById(id);
	}

}
