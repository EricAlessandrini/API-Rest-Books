package com.ega.books.service.author;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ega.books.domain.dto.AuthorDTO;
import com.ega.books.persistence.dao.IDatabaseCenter;
import com.ega.books.utils.AuthorMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements IAuthorService{

	private final IDatabaseCenter databaseCenter;
	private final AuthorMapper authorMapper;

	@Override
	public AuthorDTO getAuthorByName(String authorName) {
        return authorMapper.entityToDTO(databaseCenter.getAuthorByName(authorName));
	}

	@Override
	public List<AuthorDTO> getAllAuthors() {
        return databaseCenter.getAllAuthors().stream()
				.map(authorMapper::entityToDTO)
				.collect(Collectors.toList());
	}

	@Override
	public void completeAuthorInfo(Long id, AuthorDTO authorDTO) {
		databaseCenter.completeAuthorInfo(id, authorMapper.dtoToEntity(authorDTO));
	}
}
