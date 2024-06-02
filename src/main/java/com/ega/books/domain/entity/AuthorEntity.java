package com.ega.books.domain.entity;

import java.time.LocalDate;
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
@Table(name = "author")
public class AuthorEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "full_name")
	private String fullName;
	
	@Temporal(TemporalType.DATE)
	private LocalDate birthday;
	
	@Column(name = "place_of_birth")
	private String placeOfBirth;
	
	private String nationality;
	
	@OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
	@Column(name = "book_written")
	@JsonIgnore
	private Set<BookEntity> booksWritten;
}
