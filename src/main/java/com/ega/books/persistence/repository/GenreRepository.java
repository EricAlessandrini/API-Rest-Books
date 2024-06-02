package com.ega.books.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ega.books.domain.entity.GenreEntity;

@Repository
public interface GenreRepository extends JpaRepository<GenreEntity, Long> {

	GenreEntity findByNameIgnoreCase(String genreName);
	boolean existsByNameIgnoreCase(String genreName);
	
}
