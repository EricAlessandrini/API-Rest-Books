package com.ega.books.domain.dto;

import java.util.Set;

import jakarta.validation.constraints.Pattern;
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
	
	private Set<String> genre;
}
