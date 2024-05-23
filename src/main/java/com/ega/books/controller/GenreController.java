package com.ega.books.controller;

import java.util.List;

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
	public List<GenreDTO> getAllGenres() {
		return genreService.getAllGenres();
	}
	
	@GetMapping("/{name}")
	public GenreDTO getGenreByName(@PathVariable("name") String name) {
		return genreService.getGenreByName(name);
	}
}
