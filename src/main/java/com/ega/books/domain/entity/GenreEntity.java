package com.ega.books.domain.entity;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "genre")
public class GenreEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private String description;
	
	private String examples;
	
	@ManyToMany(mappedBy = "genre", fetch = FetchType.LAZY)
	@JsonIgnore
	private Set<BookEntity> books;
}
