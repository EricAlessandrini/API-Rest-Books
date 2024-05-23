package com.ega.books.service.book;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ega.books.domain.dto.BookDTO;
import com.ega.books.domain.entity.BookEntity;
import com.ega.books.persistence.dao.books.IBookDAO;
import com.ega.books.utils.BookMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements IBookService{
	
	private final IBookDAO dao;
	private final BookMapper bookMapper;

	@Override
	public BookDTO findBookById(Long id) {
		BookEntity entity = dao.findBookById(id);
		return bookMapper.entityToDTO(entity);
	}

	@Override
	public List<BookDTO> findAllBooks() {
		List<BookDTO> allBooks = dao.findAllBooks().stream()
				.map(bookEntity -> bookMapper.entityToDTO(bookEntity))
				.collect(Collectors.toList());
		return allBooks;
	}

	@Override
	public void saveBook(BookDTO bookDTO) {
		BookEntity bookEntity = bookMapper.dtoToEntity(bookDTO);
		dao.saveBook(bookEntity);
	}

	@Override
	public void updateBook(Long id, BookDTO bookDTO) {
		BookEntity bookEntity = bookMapper.dtoToEntity(bookDTO);
		dao.updateBook(id, bookEntity);
	}

	@Override
	public void deleteBook(Long id) {
		dao.deleteBookById(id);
	}
}
