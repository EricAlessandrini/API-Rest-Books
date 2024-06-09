package com.ega.books.controller;

import java.util.List;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.ega.books.domain.dto.GenreDTO;
import com.ega.books.service.genre.IGenreService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/genre")
@RequiredArgsConstructor
public class GenreController {
	
	private final IGenreService genreService;

	@GetMapping
	public ResponseEntity<List<GenreDTO>> getAllGenres() {
		List<GenreDTO> genres = genreService.getAllGenres();
		return new ResponseEntity<>(genres, HttpStatus.FOUND);
	}
	
	@GetMapping("/name")
	public ResponseEntity<GenreDTO> getGenreByName(@RequestBody String name) {
		GenreDTO genre = genreService.getGenreByName(name);
		return new ResponseEntity<>(genre, HttpStatus.FOUND);
	}
}
