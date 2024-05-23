package com.ega.books.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ega.books.domain.entity.BookEntity;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long>{

}
