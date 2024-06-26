package com.ega.books;

import java.time.LocalDate;
import java.util.Set;

import com.ega.books.domain.Genre;
import com.ega.books.domain.dto.AuthorDTO;
import com.ega.books.domain.dto.BookDTO;
import com.ega.books.domain.dto.GenreDTO;
import com.ega.books.domain.entity.*;

public class TestDataProvider {
	
	// Data for Author Entities
	public static AuthorEntity returnJKRowlingForTest() {
		return AuthorEntity.builder()
				.id(1L)
				.fullName("J.K. Rowling")
				.birthday(LocalDate.of(1965, 7, 31))
				.placeOfBirth("Yate, Reino Unido")
				.nationality("British")
				.build();
	}
	
	public static AuthorEntity returnFrankHerbertForTest() {
		return AuthorEntity.builder()
				.id(2L)
				.fullName("Frank Herbert")
				.birthday(LocalDate.of(1920, 10, 8))
				.placeOfBirth("Tacoma, Washington")
				.nationality("North American")
				.build();
	}
	
	public static AuthorEntity returnAgathaChristieForTest() {
		return AuthorEntity.builder()
				.id(3L)
				.fullName("Agatha Christie")
				.birthday(LocalDate.of(1890, 9, 15))
				.placeOfBirth("Torquay, Reino Unido")
				.nationality("British")
				.build();
	}

	public static AuthorEntity returnJRRTolkienForTest() {
		return AuthorEntity.builder()
				.id(4L)
				.fullName("J.R.R. Tolkien")
				.birthday(LocalDate.of(1892,1,3))
				.placeOfBirth("Bloemfontein, South Africa")
				.nationality("British")
				.build();
	}

	public static AuthorEntity returnOnlyNameForTest() {
		return AuthorEntity.builder()
				.id(4L)
				.fullName("J.R.R. Tolkien")
				.build();
	}

	public static AuthorEntity returnOtherInfoAuthorForTest() {
		return AuthorEntity.builder()
				.birthday(LocalDate.of(1892,1,3))
				.placeOfBirth("Bloemfontein, South Africa")
				.nationality("British")
				.build();
	}
	
	// Data for Genre Entities
	public static GenreEntity returnFantasyGenreForTest() {
		return GenreEntity.builder()
				.id(1L)
				.name(Genre.FANTASIA.getName())
				.description(Genre.FANTASIA.getDescription())
				.examples(Genre.FANTASIA.getExamples())
				.build();
	}
	
	public static GenreEntity returnScienceFictionGenreForTest() {
		return GenreEntity.builder().id(2L).name(Genre.CIENCIA_FICCION.getName())
				.description(Genre.CIENCIA_FICCION.getDescription()).examples(Genre.CIENCIA_FICCION.getExamples())
				.build();
	}
	
	public static GenreEntity returnMisteryGenreForTest() {
		return GenreEntity.builder()
				.id(3L)
				.name(Genre.MISTERIO.getName())
				.description(Genre.MISTERIO.getDescription())
				.examples(Genre.MISTERIO.getExamples())
				.build();
	}
	
	public static GenreEntity returnThrillerGenreForTest() {
		return GenreEntity.builder()
				.id(4L)
				.name(Genre.THRILLER.getName())
				.description(Genre.THRILLER.getDescription())
				.examples(Genre.THRILLER.getExamples())
				.build();
	}
	
	// Data for Book Entities
	public static BookEntity returnHarryPotterPiedraFilosofalForTest() {
		Set<GenreEntity> genres = Set.of(
				returnFantasyGenreForTest(), 
				returnScienceFictionGenreForTest()
				);
		
		return BookEntity.builder()
				.id(1L)
				.title("Harry Potter y la piedra filosofal")
				.genre(genres)
				.author(returnJKRowlingForTest())
				.build();
	}
	
	public static BookEntity returnHarryPotterCamaraSecretaForTest() {
		Set<GenreEntity> genres = Set.of(returnFantasyGenreForTest(),
				returnScienceFictionGenreForTest());
		
		return BookEntity.builder()
				.id(2L)
				.title("Harry Potter y la camara secreta")
				.genre(genres)
				.author(returnJKRowlingForTest())
				.build();
	}
	
	public static BookEntity returnDuneForTest() {
		Set<GenreEntity> genres = Set.of(returnFantasyGenreForTest(),
				returnScienceFictionGenreForTest(),
				returnThrillerGenreForTest());
		
		return BookEntity.builder()
				.id(3L)
				.title("Dune")
				.genre(genres)
				.author(returnFrankHerbertForTest())
				.build();
	}
	
	public static BookEntity returnAsesinatoExpresoOrienteForTest() {
		Set<GenreEntity> genres = Set.of(
				returnMisteryGenreForTest(),
				returnThrillerGenreForTest()
		);
		return BookEntity.builder()
				.id(4L)
				.title("Asesinato en el Expreso de Oriente")
				.genre(genres)
				.author(returnAgathaChristieForTest())
				.build();
	}

	public static BookEntity returnMuerteEnElNiloForTest() {
		Set<GenreEntity> genres = Set.of(
				returnThrillerGenreForTest(),
				returnMisteryGenreForTest()
		);

		return BookEntity.builder()
				.id(5L)
				.title("Muerte en el Nilo")
				.genre(genres)
				.author(returnAgathaChristieForTest())
				.build();
	}

	public static BookEntity returnBookEntityOnlyTitleForTest() {
		return BookEntity.builder()
				.title("Caceria en Venecia")
				.build();
	}

	public static BookEntity returnBookEntityNullTitleForTest() {
		Set<GenreEntity> genres = Set.of(
				TestDataProvider.returnFantasyGenreForTest(),
				TestDataProvider.returnScienceFictionGenreForTest(),
				TestDataProvider.returnThrillerGenreForTest()
		);
		return BookEntity.builder()
				.title(null)
				.genre(genres)
				.author(returnJRRTolkienForTest())
				.build();
	}

	public static BookEntity returnBookEntityNullGenreForTest() {
		return BookEntity.builder()
				.title("Lord Of The Rings")
				.genre(null)
				.author(returnJRRTolkienForTest())
				.build();
	}

	public static BookEntity returnBookEntityNullAuthorForTest() {
		Set<GenreEntity> genres = Set.of(
				TestDataProvider.returnFantasyGenreForTest(),
				TestDataProvider.returnScienceFictionGenreForTest(),
				TestDataProvider.returnThrillerGenreForTest()
		);
		return BookEntity.builder()
				.title("Lord Of The Rings")
				.genre(genres)
				.author(null)
				.build();
	}

	// Data for Author DTO
	public static AuthorDTO returnJKRowlingDtoForTest() {
		return AuthorDTO.builder()
				.id(1L)
				.fullName("J.K. Rowling")
				.birthday(LocalDate.of(1965, 7, 31))
				.placeOfBirth("Yate, Reino Unido")
				.nationality("British")
				.build();
	}

	public static AuthorDTO returnFrankHerbertDtoForTest() {
		return AuthorDTO.builder()
				.id(2L)
				.fullName("Frank Herbert")
				.birthday(LocalDate.of(1920, 10, 8))
				.placeOfBirth("Tacoma, Washington")
				.nationality("North American")
				.build();
	}

	public static AuthorDTO returnAgathaChristieDtoForTest() {
		return AuthorDTO.builder()
				.id(3L)
				.fullName("Agatha Christie")
				.birthday(LocalDate.of(1890, 9, 15))
				.placeOfBirth("Torquay, Reino Unido")
				.nationality("British")
				.build();
	}

	public static AuthorDTO returnJRRTolkienDtoForTest() {
		return AuthorDTO.builder()
				.id(4L)
				.fullName("J.R.R. Tolkien")
				.birthday(LocalDate.of(1892,1,3))
				.placeOfBirth("Bloemfontein, South Africa")
				.nationality("British")
				.build();
	}

	// Data for Genre DTO
	public static GenreDTO returnFantasyGenreDtoForTest() {
		return GenreDTO.builder()
				.name(Genre.FANTASIA.getName())
				.description(Genre.FANTASIA.getDescription())
				.examples(Genre.FANTASIA.getExamples())
				.build();
	}

	public static GenreDTO returnScienceFictionGenreDtoForTest() {
		return GenreDTO.builder()
				.name(Genre.CIENCIA_FICCION.getName())
				.description(Genre.CIENCIA_FICCION.getDescription())
				.examples(Genre.CIENCIA_FICCION.getExamples())
				.build();
	}

	public static GenreDTO returnMisteryGenreDtoForTest() {
		return GenreDTO.builder()
				.name(Genre.MISTERIO.getName())
				.description(Genre.MISTERIO.getDescription())
				.examples(Genre.MISTERIO.getExamples())
				.build();
	}

	public static GenreDTO returnThrillerGenreDtoForTest() {
		return GenreDTO.builder()
				.name(Genre.THRILLER.getName())
				.description(Genre.THRILLER.getDescription())
				.examples(Genre.THRILLER.getExamples())
				.build();
	}

	// Data for Book DTO
	public static BookDTO returnHarryPotterPiedraFilosofalDtoForTest() {
		Set<String> genres = Set.of(
				returnFantasyGenreDtoForTest().getName(),
				returnScienceFictionGenreDtoForTest().getName()
		);

		return BookDTO.builder()
				.id(1L)
				.title("Harry Potter y la piedra filosofal")
				.genre(genres)
				.author(returnJKRowlingDtoForTest().getFullName())
				.build();
	}

	public static BookDTO returnHarryPotterCamaraSecretaDtoForTest() {
		Set<String> genres = Set.of(
				returnFantasyGenreDtoForTest().getName(),
				returnScienceFictionGenreDtoForTest().getName()
		);

		return BookDTO.builder()
				.id(2L)
				.title("Harry Potter y la camara secreta")
				.genre(genres)
				.author(returnJKRowlingDtoForTest().getFullName())
				.build();
	}

	public static BookDTO returnDuneDtoForTest() {
		Set<String> genres = Set.of(
				returnFantasyGenreDtoForTest().getName(),
				returnScienceFictionGenreDtoForTest().getName(),
				returnThrillerGenreDtoForTest().getName()
		);

		return BookDTO.builder()
				.id(3L)
				.title("Dune")
				.genre(genres)
				.author(returnFrankHerbertDtoForTest().getFullName())
				.build();
	}

	public static BookDTO returnAsesinatoExpresoOrienteDtoForTest() {
		Set<String> genres = Set.of(
				returnMisteryGenreDtoForTest().getName(),
				returnThrillerGenreDtoForTest().getName()
		);
		return BookDTO.builder()
				.id(4L)
				.title("Asesinato en el Expreso de Oriente")
				.genre(genres)
				.author(returnAgathaChristieDtoForTest().getFullName())
				.build();
	}
}
