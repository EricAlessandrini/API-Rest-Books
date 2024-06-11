package com.ega.books.persistence;

import com.ega.books.DataProviderForTest;
import com.ega.books.domain.entity.BookEntity;
import static org.junit.jupiter.api.Assertions.*;

import static org.assertj.core.api.Assertions.*;

import com.ega.books.domain.entity.GenreEntity;
import com.ega.books.exception.exceptions.EmptyListFromDatabaseException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ega.books.persistence.dao.DatabaseCenterImpl;
import com.ega.books.persistence.repository.*;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DatabaseCenterTest {
	
	@Mock
	private BookRepository bookRepository;
	@Mock
	private GenreRepository genreRepository;
	@Mock
	private AuthorRepository authorRepository;
	
	@InjectMocks
	private DatabaseCenterImpl databaseCenter;
	
	@Test
	@DisplayName("Test findBooksByTitle() Method")
	void databaseCenter_findBookByTitle_test() {
		// Given
		String title = "harry";
		List<BookEntity> booksTest = List.of(DataProviderForTest.returnHarryPotterPiedraFilosofalForTest(),
				DataProviderForTest.returnHarryPotterCamaraSecretaForTest());
		when(bookRepository.findBookByTitle(anyString())).thenReturn(booksTest);

		// When
		List<BookEntity> booksFound = databaseCenter.findBookByTitle(title);

		// Then
		assertNotNull(booksFound);
		assertThat(booksFound).hasSize(2);

		assertEquals(1L, booksFound.get(0).getId());
		assertEquals("Harry Potter y la piedra filosofal", booksFound.get(0).getTitle());
		assertThat(booksFound.get(0).getTitle()).containsIgnoringCase(title);
		assertEquals(2, booksFound.get(0).getGenre().size());
		for(GenreEntity genre : booksFound.get(0).getGenre()) {
			assertThat(genre).isInstanceOf(GenreEntity.class);
			assertThat(genre.getName()).containsAnyOf("Fantasia", "Ciencia Ficcion");
			assertNotNull(genre.getDescription());
			assertNotNull(genre.getExamples());
		}
		assertAll(
				() -> assertEquals(1L, booksFound.get(0).getAuthor().getId()),
				() -> assertEquals("J.K. Rowling", booksFound.get(0).getAuthor().getFullName()),
				() -> assertEquals(LocalDate.of(1965,7,31),
						booksFound.get(0).getAuthor().getBirthday()),
				() -> assertEquals("Yate, Reino Unido", booksFound.get(0).getAuthor()
						.getPlaceOfBirth()),
				() -> assertEquals("British", booksFound.get(0).getAuthor().getNationality())
		);

		assertThat(booksFound.get(1).getTitle()).containsIgnoringCase(title);
		assertEquals(2L, booksFound.get(1).getId());
		assertEquals("Harry Potter y la camara secreta", booksFound.get(1).getTitle());
		assertEquals(2, booksFound.get(1).getGenre().size());
		for(GenreEntity genre : booksFound.get(1).getGenre()) {
			assertThat(genre).isInstanceOf(GenreEntity.class);
			assertThat(genre.getName()).containsAnyOf("Fantasia", "Ciencia Ficcion");
			assertNotNull(genre.getDescription());
			assertNotNull(genre.getExamples());
		}
		assertAll(
				() -> assertEquals(1L, booksFound.get(0).getAuthor().getId()),
				() -> assertEquals("J.K. Rowling", booksFound.get(0).getAuthor()
						.getFullName()),
				() -> assertEquals(LocalDate.of(1965,7,31),
						booksFound.get(0).getAuthor().getBirthday()),
				() -> assertEquals("Yate, Reino Unido", booksFound.get(0).getAuthor()
						.getPlaceOfBirth()),
				() -> assertEquals("British", booksFound.get(0).getAuthor()
						.getNationality())
		);

		verify(bookRepository).findBookByTitle(anyString());
	}

	@Test
	@DisplayName("Test Exception EmptyList")
	void databaseCenter_findBookByTitle_emptyException_test() {
		String title = "Carnival";
		List<BookEntity> booksTest = List.of();
		when(bookRepository.findBookByTitle(anyString())).thenReturn(booksTest);

		assertThrows(EmptyListFromDatabaseException.class,
				() -> databaseCenter.findBookByTitle(title));
	}

	@Test
	@DisplayName("Test findBooksByAuthor")
	void databaseCenter_findBookByAuthor_Test() {
		String author = "Agatha";
		List<BookEntity> booksTest = List.of(DataProviderForTest
				.returnAsesinatoExpresoOrienteForTest());
		when(bookRepository.findBooksByAuthorName(anyString())).thenReturn(booksTest);

		List<BookEntity> booksFound = databaseCenter.findBooksByAuthorName(author);

		assertNotNull(booksFound);
		assertThat(booksFound).isNotEmpty();
		assertThat(booksFound.get(0).getAuthor().getFullName()).contains(author);
		assertEquals(4L, booksFound.get(0).getId());
		assertEquals("Asesinato en el Expreso de Oriente", booksFound.get(0)
				.getTitle());
		for(GenreEntity genre : booksFound.get(0).getGenre()) {
			assertThat(genre).isInstanceOf(GenreEntity.class);
			assertThat(genre.getName()).containsAnyOf("Misterio", "Thriller");
			assertNotNull(genre.getDescription());
			assertNotNull(genre.getExamples());
		}
		assertAll(
				() -> assertEquals(3L, booksFound.get(0).getAuthor().getId()),
				() -> assertEquals("Agatha Christie", booksFound.get(0).getAuthor()
						.getFullName()),
				() -> assertEquals(LocalDate.of(1890,9,15),
						booksFound.get(0).getAuthor().getBirthday()),
				() -> assertEquals("Torquay, Reino Unido", booksFound.get(0)
						.getAuthor().getPlaceOfBirth()),
				() -> assertEquals("British", booksFound.get(0).getAuthor()
						.getNationality())
		);

		verify(bookRepository).findBooksByAuthorName(anyString());
	}

	@Test
	@DisplayName("Test findBooksByGenreName")
	void databaseCenter_findBooksByGenreName_Test() {
		String genre = "thriller";
		List<BookEntity> booksTest = List.of(DataProviderForTest.returnDuneForTest(),
				DataProviderForTest.returnAsesinatoExpresoOrienteForTest());
		when(bookRepository.findBooksByGenreName(anyString())).thenReturn(booksTest);

		List<BookEntity> booksFound = databaseCenter.findBooksByGenreName(genre);

		assertNotNull(booksFound);
		assertThat(booksFound.size()).isGreaterThan(1);
		assertTrue(booksFound.get(0).getGenre().stream()
				.map(GenreEntity::getName)
				.anyMatch(g -> g.equalsIgnoreCase(genre)));

		// First Book
		assertEquals(3L, booksFound.get(0).getId());
		assertEquals("Dune", booksFound.get(0).getTitle());
		for(GenreEntity g : booksFound.get(0).getGenre()) {
			assertNotNull(g);
			assertThat(g).isInstanceOf(GenreEntity.class);
			assertThat(g.getName().toLowerCase())
					.containsAnyOf(genre.toLowerCase(), "ciencia ficcion", "fantasia");
			assertNotNull(g.getDescription());
			assertNotNull(g.getExamples());
		}
		assertAll(
				() -> assertEquals(2L, booksFound.get(0).getAuthor().getId()),
				() -> assertEquals("Frank Herbert", booksFound.get(0)
						.getAuthor().getFullName()),
				() -> assertEquals(LocalDate.of(1920,10,8),
						booksFound.get(0).getAuthor().getBirthday()),
				() -> assertEquals("Tacoma, Washington", booksFound.get(0)
						.getAuthor().getPlaceOfBirth()),
				() -> assertEquals("North American", booksFound.get(0)
						.getAuthor().getNationality())
		);

		// Second Book
		assertEquals(4L, booksFound.get(1).getId());
		assertEquals("Asesinato en el Expreso de Oriente", booksFound.get(1)
				.getTitle());
		for(GenreEntity g : booksFound.get(1).getGenre()) {
			assertNotNull(g);
			assertThat(g).isInstanceOf(GenreEntity.class);
			assertThat(g.getName().toLowerCase())
					.containsAnyOf(genre.toLowerCase(), "misterio");
			assertNotNull(g.getDescription());
			assertNotNull(g.getExamples());
		}
		assertAll(
				() -> assertEquals(3L, booksFound.get(1).getAuthor().getId()),
				() -> assertEquals("Agatha Christie", booksFound.get(1).getAuthor()
						.getFullName()),
				() -> assertEquals(LocalDate.of(1890,9,15),
						booksFound.get(1).getAuthor().getBirthday()),
				() -> assertEquals("Torquay, Reino Unido", booksFound.get(1)
						.getAuthor().getPlaceOfBirth()),
				() -> assertEquals("British", booksFound.get(1).getAuthor()
						.getNationality())
		);

		verify(bookRepository).findBooksByGenreName(anyString());
	}

	@Test
	@DisplayName("Test findAllBooks")
	void databaseCenter_findAllBooks_Test() {
		List<BookEntity> books = List.of(
				DataProviderForTest.returnHarryPotterPiedraFilosofalForTest(),
				DataProviderForTest.returnHarryPotterCamaraSecretaForTest(),
				DataProviderForTest.returnDuneForTest(),
				DataProviderForTest.returnAsesinatoExpresoOrienteForTest()
		);
		when(bookRepository.findAll()).thenReturn(books);

		List<BookEntity> booksFound = databaseCenter.findAllBooks();

		assertNotNull(booksFound);
		assertThat(booksFound).isNotEmpty();
		assertEquals(4, booksFound.size());


	}
}
