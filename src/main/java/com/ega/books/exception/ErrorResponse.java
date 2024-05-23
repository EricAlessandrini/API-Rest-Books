package com.ega.books.exception;

import java.time.LocalDateTime;
import java.util.List;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorResponse {

	private String code;
	private String message;
	private List<String> details;
	private LocalDateTime timestamp;
	
}
