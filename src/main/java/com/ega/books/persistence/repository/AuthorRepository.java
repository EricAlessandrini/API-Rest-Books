package com.ega.books.persistence.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ega.books.domain.entity.AuthorEntity;

@Repository
public interface AuthorRepository extends JpaRepository<AuthorEntity, Long>{

	@Query("SELECT a FROM AuthorEntity a WHERE LOWER(a.fullName) LIKE LOWER(CONCAT('%', :name, '%'))")
	AuthorEntity findAuthorByName(@Param("name") String name);
	
}
