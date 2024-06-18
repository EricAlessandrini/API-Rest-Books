package com.ega.books.controller;

import com.ega.books.domain.dto.AuthorDTO;
import com.ega.books.service.author.IAuthorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
@RequiredArgsConstructor
@Tag(
		name = "Authors",
		description = "Controller for Author"
)
public class AuthorController {
	
	private final IAuthorService authorService;
	
	@GetMapping("/authorName")
	@Operation(
			method = "GET",
			summary = "Get author by his name",
			description = "GET method to obtain an author's information",
			parameters = {
					@Parameter(
							name = "authorName",
							in = ParameterIn.QUERY,
							description = "Full or partial name of the author searched",
							required = true
					)
			},
			responses = {
					@ApiResponse(
							description = "Returns the searched author information and a status code",
							responseCode = "302"
					)
			}
	)
	public ResponseEntity<AuthorDTO> getAuthorByName(@RequestParam String authorName) {
		return new ResponseEntity<>(authorService.getAuthorByName(authorName), HttpStatus.FOUND);
	}
	
	@GetMapping
	@Operation(
			method = "GET",
			summary = "Get all authors",
			description = "GET method to obtain a list of all author's information available",
			responses = {
					@ApiResponse(
							description = "Returns a list of authors with their respective information and a status code",
							responseCode = "302"
					)
			}
	)
	public ResponseEntity<List<AuthorDTO>> getAllAuthors() {
		return new ResponseEntity<>(authorService.getAllAuthors(), HttpStatus.FOUND);
	}
	
	@PutMapping("/editAuthorInfo/{id}")
	@Operation(
			method = "PUT",
			summary = "Complete an author's information",
			description = "PUT method to update the information of an author that is saved already",
			parameters = {
					@Parameter(
							name = "id",
							in = ParameterIn.PATH,
							description = "Identifier of the author that needs to be searched",
							required = true
					)
			},
			requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
					description = "Request that contains the updated information of the author",
					required = true,
					content = {
							@Content(
									mediaType = "application/json",
									schema = @Schema(implementation = AuthorDTO.class)
							)
					}
			),
			responses = {
					@ApiResponse(
							description = "Returns a success message along with a status code",
							responseCode = "200"
					)
			}
	)
	public ResponseEntity<String> completeAuthorInfo(@PathVariable("id") Long id,
													 @RequestBody AuthorDTO authorDTO) {
		authorService.completeAuthorInfo(id, authorDTO);
		return new ResponseEntity<>("Los datos del autor han sido actualizados", HttpStatus.OK);
	}
}
