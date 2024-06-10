package com.ega.books.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ega.books.domain.entity.BookEntity;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long>{

	@Query("SELECT b FROM BookEntity b WHERE LOWER(b.title) LIKE LOWER(CONCAT('%', :title, '%'))")
	List<BookEntity> findBookByTitle(@Param("title") String bookTitle);

	@Query("SELECT b FROM BookEntity b JOIN b.author a WHERE LOWER(a.fullName) LIKE LOWER(CONCAT('%', :authorName, '%'))")
	List<BookEntity> findBooksByAuthorName(@Param("authorName") String authorName);
	
}
