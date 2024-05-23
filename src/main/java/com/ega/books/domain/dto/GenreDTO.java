package com.ega.books.domain.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GenreDTO {

	private String name;
	private String description;
	private String examples;
	
}
