package com.ega.books.domain.dto;

import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthorDTO {

	@Pattern(regexp = "^[a-zA-Z\\s]+$", message = "The author's name is NOT valid. You should only use letters...")
	private String fullName;
	
	@Past(message = "The author's birthday must from the past")
	private LocalDate birthday;
	
	@Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Place of Birth field is NOT valid. You should only use letters...")
	private String placeOfBirth;
	
	@Pattern(regexp = "^[a-zA-Z\\s]+$", message = "The country name is NOT valid. You should only use letters...")
	private String nationality;
}
