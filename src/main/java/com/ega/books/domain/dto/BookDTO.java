package com.ega.books.domain.dto;

import java.util.*;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {

	@Pattern(regexp = "^[a-zA-Z\\s]+$", message = "The title's name is NOT valid. You should only use letters...")
	private String title;
	
	@NotNull(message = "Genre's field CANNOT be empty/invalid")
	private Set<String> genre = new HashSet<>();

	@Pattern(regexp = "^[a-zA-Z\\s]+$", message = "The title's name is NOT valid. You should only use letters...")
	private String author;

}
