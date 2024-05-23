package com.ega.books.domain.entity;

import java.util.Set;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "books")
public class BookEntity {

	@Id
	@GeneratedValue(strategy  = GenerationType.IDENTITY)
	private Long id;
	
	private String title;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "books_genre", 
	joinColumns = @JoinColumn(name = "book_id"),
	inverseJoinColumns = @JoinColumn(name = "genre_id"))
	private Set<GenreEntity> genre;
	
}
