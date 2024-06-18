package com.ega.books.controller;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.ega.books.domain.dto.GenreDTO;
import com.ega.books.service.genre.IGenreService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/genre")
@RequiredArgsConstructor
@Tag(
		name = "Genre",
		description = "Controller for Genre"
)
public class GenreController {
	
	private final IGenreService genreService;

	@GetMapping
	@Operation(
			method = "GET",
			summary = "Get all genres",
			description = "Method GET to list all genres already saved in the database",
			responses = {
					@ApiResponse(
							description = "List of genres",
							responseCode = "302"
					)
			}
	)
	public ResponseEntity<List<GenreDTO>> getAllGenres() {
		List<GenreDTO> genres = genreService.getAllGenres();
		return new ResponseEntity<>(genres, HttpStatus.FOUND);
	}
	
	@GetMapping("/name")
	@Operation(
			method = "GET",
			summary = "Get one genre",
			description = "Method GET to obtain one genre already saved in the database",
			responses = {
					@ApiResponse(
							description = "Genre",
							responseCode = "302"
					)
			},
			parameters = {
					@Parameter(
							name = "name",
							in = ParameterIn.QUERY,
							description = "Name of the genre searched",
							required = true
					)
			}
	)
	public ResponseEntity<GenreDTO> getGenreByName(@RequestParam String name) {
		GenreDTO genre = genreService.getGenreByName(name);
		return new ResponseEntity<>(genre, HttpStatus.FOUND);
	}
}
