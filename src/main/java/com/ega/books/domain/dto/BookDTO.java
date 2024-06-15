package com.ega.books.domain.dto;

import java.util.*;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDTO {

	private Long id;

	@Pattern(regexp = "^[a-zA-Z\\s]+$", message = "The title's name is NOT valid. You should only use letters...")
	private String title;
	
	@NotNull(message = "Genre's field CANNOT be empty/invalid")
	private Set<String> genre;

	@Pattern(regexp = "^[a-zA-Z\\s.]+$", message = "The author's name is NOT valid. You should only use letters...")
	private String author;

}
