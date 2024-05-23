package com.ega.books;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.ega.books.domain.Genre;
import com.ega.books.domain.entity.GenreEntity;
import com.ega.books.persistence.repository.GenreRepository;

@SpringBootApplication
public class ApiRestBooksApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiRestBooksApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner initializationData(GenreRepository genreRepository) {
		return args -> {
			for(Genre genre : Genre.values()) {
				if(!genreRepository.existsByName(genre.getName())) {
					genreRepository.save(GenreEntity.builder()
							.name(genre.getName())
							.description(genre.getDescription())
							.examples(genre.getExamples())
							.build());
				}
			}
		};
	}

}

/*API REST para realizar CRUD basico
 *
 * OBJETIVOS DE LA API REST "BOOKS" PARA MI COMO PROGRAMADOR
 * - Practicar la creacion de API REST usando buenas practicas
 * - Practicar las relaciones entre entidades usando Spring Data JPA
 * - Practicar la manipulacion de entidades y el mapeo de unas a otras
 * - Practicar la creacion de Queries personalizadas a traves de los metodos de Native Query y SQL
 * 
 * OBJETIVOS DE LA APLICACION COMO API REST "BOOKS"
 * - Realizar operaciones CRUD sin problemas
 * - Manipular y manejar correctamente las excepciones que se puedan presentar dando los mensajes correctos
 * 
 * FUNCIONAMIENTO BASICO DE LA API REST "BOOKS"
 * a) En los metodos POST y PUT:
 * 	- DTO -> MODEL -> ENTITY
 * b) En los metodos GET:
 * 	- ENTITY -> MODEL -> DTO
 * 
 **/
 