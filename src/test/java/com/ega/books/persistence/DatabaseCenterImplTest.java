package com.ega.books.persistence;

import com.ega.books.TestDataProvider;
import com.ega.books.domain.entity.AuthorEntity;
import com.ega.books.domain.entity.BookEntity;
import com.ega.books.domain.entity.GenreEntity;
import com.ega.books.exception.exceptions.AuthorNotFoundException;
import com.ega.books.exception.exceptions.BookNotFoundException;
import com.ega.books.exception.exceptions.EmptyListFromDatabaseException;
import com.ega.books.exception.exceptions.InvalidGenreException;
import com.ega.books.persistence.dao.DatabaseCenterImpl;
import com.ega.books.persistence.repository.AuthorRepository;
import com.ega.books.persistence.repository.BookRepository;
import com.ega.books.persistence.repository.GenreRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DatabaseCenterImplTest {
	
	@Mock
	private BookRepository bookRepository;
	@Mock
	private GenreRepository genreRepository;
	@Mock
	private AuthorRepository authorRepository;
	
	@InjectMocks
	private DatabaseCenterImpl databaseCenter;
	
	@Test
	@DisplayName("Test Find Books By Title - Success")
	void testFindBooksByTitleSuccessful() {
		String title = "harry";
		List<BookEntity> booksTest = List.of(
				TestDataProvider.returnHarryPotterPiedraFilosofalForTest(),
				TestDataProvider.returnHarryPotterCamaraSecretaForTest()
		);
		when(bookRepository.findBookByTitle(anyString())).thenReturn(booksTest);

		List<BookEntity> booksFound = databaseCenter.findBookByTitle(title);

		assertNotNull(booksFound);
		assertThat(booksFound).hasSize(2);

		assertEquals(1L,
				booksFound.get(0).getId());
		assertEquals("Harry Potter y la piedra filosofal",
				booksFound.get(0).getTitle());
		assertEquals(2,
				booksFound.get(0).getGenre().size());
		for(GenreEntity genre : booksFound.get(0).getGenre()) {
			assertThat(genre).isInstanceOf(GenreEntity.class);
			assertThat(genre.getName()).containsAnyOf("Fantasia", "Ciencia Ficcion");
			assertNotNull(genre.getDescription());
			assertNotNull(genre.getExamples());
		}
		assertAll(
				() -> assertEquals(1L,
						booksFound.get(0).getAuthor().getId()),
				() -> assertEquals("J.K. Rowling",
						booksFound.get(0).getAuthor().getFullName()),
				() -> assertEquals(LocalDate.of(1965,7,31),
						booksFound.get(0).getAuthor().getBirthday()),
				() -> assertEquals("Yate, Reino Unido",
						booksFound.get(0).getAuthor().getPlaceOfBirth()),
				() -> assertEquals("British",
						booksFound.get(0).getAuthor().getNationality())
		);

		assertThat(booksFound.get(1).getTitle()).containsIgnoringCase(title);
		assertEquals(2L,
				booksFound.get(1).getId());
		assertEquals("Harry Potter y la camara secreta",
				booksFound.get(1).getTitle());
		assertEquals(2, booksFound.get(1).getGenre().size());
		for(GenreEntity genre : booksFound.get(1).getGenre()) {
			assertThat(genre).isInstanceOf(GenreEntity.class);
			assertThat(genre.getName()).containsAnyOf("Fantasia", "Ciencia Ficcion");
			assertNotNull(genre.getDescription());
			assertNotNull(genre.getExamples());
		}
		assertAll(
				() -> assertEquals(1L,
						booksFound.get(0).getAuthor().getId()),
				() -> assertEquals("J.K. Rowling",
						booksFound.get(0).getAuthor().getFullName()),
				() -> assertEquals(LocalDate.of(1965,7,31),
						booksFound.get(0).getAuthor().getBirthday()),
				() -> assertEquals("Yate, Reino Unido",
						booksFound.get(0).getAuthor().getPlaceOfBirth()),
				() -> assertEquals("British",
						booksFound.get(0).getAuthor().getNationality())
		);

		verify(bookRepository).findBookByTitle(anyString());
	}

	@Test
	@DisplayName("Test Find Books By Title - Empty Exception Thrown")
	void testFindBooksByTitleAndThrowsEmptyException() {
		String title = "Carnival";
		List<BookEntity> booksTest = List.of();
		when(bookRepository.findBookByTitle(anyString())).thenReturn(booksTest);

		assertThrows(EmptyListFromDatabaseException.class,
				() -> databaseCenter.findBookByTitle(title));
	}

	@Test
	@DisplayName("Test Find Books by Author - Success")
	void testFindBooksByAuthorNameSuccessful() {
		String author = "Agatha";
		List<BookEntity> booksTest = List.of(TestDataProvider
				.returnAsesinatoExpresoOrienteForTest());
		when(bookRepository.findBooksByAuthorName(anyString()))
				.thenReturn(booksTest);

		List<BookEntity> booksFound = databaseCenter.findBooksByAuthorName(author);

		assertNotNull(booksFound);
		assertThat(booksFound).isNotEmpty();
		assertThat(booksFound.get(0).getAuthor().getFullName())
				.contains(author);
		assertEquals(4L,
				booksFound.get(0).getId());
		assertEquals("Asesinato en el Expreso de Oriente",
				booksFound.get(0).getTitle());
		for(GenreEntity genre : booksFound.get(0).getGenre()) {
			assertThat(genre).isInstanceOf(GenreEntity.class);
			assertThat(genre.getName()).containsAnyOf("Misterio", "Thriller");
			assertNotNull(genre.getDescription());
			assertNotNull(genre.getExamples());
		}
		assertAll(
				() -> assertEquals(3L,
						booksFound.get(0).getAuthor().getId()),
				() -> assertEquals("Agatha Christie",
						booksFound.get(0).getAuthor().getFullName()),
				() -> assertEquals(LocalDate.of(1890,9,15),
						booksFound.get(0).getAuthor().getBirthday()),
				() -> assertEquals("Torquay, Reino Unido",
						booksFound.get(0).getAuthor().getPlaceOfBirth()),
				() -> assertEquals("British",
						booksFound.get(0).getAuthor().getNationality())
		);

		verify(bookRepository).findBooksByAuthorName(anyString());
	}

	@Test
	@DisplayName("Test Find Books By Author - Empty Exception Thrown")
	void testFindBooksByAuthorAndThrowsEmptyException() {
		String authorName = "Garcia";
		List<BookEntity> booksEmpty = List.of();
		when(bookRepository.findBooksByAuthorName(anyString()))
				.thenReturn(booksEmpty);

		assertThrows(EmptyListFromDatabaseException.class,
				() -> databaseCenter.findBooksByAuthorName(authorName));

		verify(bookRepository).findBooksByAuthorName(anyString());
	}

	@Test
	@DisplayName("Test Find Books By Genre - Success")
	void testFindBooksByGenreNameSuccessful() {
		String genre = "thriller";
		List<BookEntity> booksTest = List.of(TestDataProvider.returnDuneForTest(),
				TestDataProvider.returnAsesinatoExpresoOrienteForTest());
		when(bookRepository.findBooksByGenreName(anyString()))
				.thenReturn(booksTest);

		List<BookEntity> booksFound = databaseCenter.findBooksByGenreName(genre);

		assertNotNull(booksFound);
		assertThat(booksFound.size()).isGreaterThan(1);
		assertTrue(booksFound.get(0).getGenre().stream()
				.map(GenreEntity::getName)
				.anyMatch(g -> g.equalsIgnoreCase(genre)));

		// First Book
		assertEquals(3L,
				booksFound.get(0).getId());
		assertEquals("Dune",
				booksFound.get(0).getTitle());
		for(GenreEntity g : booksFound.get(0).getGenre()) {
			assertNotNull(g);
			assertThat(g).isInstanceOf(GenreEntity.class);
			assertThat(g.getName().toLowerCase())
					.containsAnyOf(genre.toLowerCase(), "ciencia ficcion", "fantasia");
			assertNotNull(g.getDescription());
			assertNotNull(g.getExamples());
		}
		assertAll(
				() -> assertEquals(2L,
						booksFound.get(0).getAuthor().getId()),
				() -> assertEquals("Frank Herbert",
						booksFound.get(0).getAuthor().getFullName()),
				() -> assertEquals(LocalDate.of(1920,10,8),
						booksFound.get(0).getAuthor().getBirthday()),
				() -> assertEquals("Tacoma, Washington",
						booksFound.get(0).getAuthor().getPlaceOfBirth()),
				() -> assertEquals("North American",
						booksFound.get(0).getAuthor().getNationality())
		);

		// Second Book
		assertEquals(4L,
				booksFound.get(1).getId());
		assertEquals("Asesinato en el Expreso de Oriente",
				booksFound.get(1).getTitle());
		for(GenreEntity g : booksFound.get(1).getGenre()) {
			assertNotNull(g);
			assertThat(g.getName().toLowerCase())
					.containsAnyOf(genre.toLowerCase(), "misterio");
			assertNotNull(g.getDescription());
			assertNotNull(g.getExamples());
		}

		assertAll(
				() -> assertEquals(3L,
						booksFound.get(1).getAuthor().getId()),
				() -> assertEquals("Agatha Christie",
						booksFound.get(1).getAuthor().getFullName()),
				() -> assertEquals(LocalDate.of(1890,9,15),
						booksFound.get(1).getAuthor().getBirthday()),
				() -> assertEquals("Torquay, Reino Unido",
						booksFound.get(1).getAuthor().getPlaceOfBirth()),
				() -> assertEquals("British",
						booksFound.get(1).getAuthor().getNationality())
		);

		verify(bookRepository, times(1)).findBooksByGenreName(anyString());
	}

	@Test
	@DisplayName("Test Find Books By Genre - Empty Exception Thrown")
	void testFindBooksByGenreNameAndThrowEmptyException() {
		String genreName = "Comedia";
		List<BookEntity> booksEmpty = List.of();
		when(bookRepository.findBooksByGenreName(anyString()))
				.thenReturn(booksEmpty);

		assertThrows(EmptyListFromDatabaseException.class,
				() -> databaseCenter.findBooksByGenreName(genreName));
	}

	@Test
	@DisplayName("Test Find All Books - Success")
	void testFindAllBooksSuccessful() {
		List<BookEntity> books = List.of(
				TestDataProvider.returnHarryPotterPiedraFilosofalForTest(),
				TestDataProvider.returnHarryPotterCamaraSecretaForTest(),
				TestDataProvider.returnDuneForTest(),
				TestDataProvider.returnAsesinatoExpresoOrienteForTest()
		);
		when(bookRepository.findAll()).thenReturn(books);

		List<BookEntity> booksFound = databaseCenter.findAllBooks();

		assertNotNull(booksFound);
		assertThat(booksFound).isNotEmpty();
		assertEquals(4,
				booksFound.size());
		assertThat(booksFound).hasOnlyElementsOfType(BookEntity.class);

		// Book One
		assertEquals(1L,
				booksFound.get(0).getId());
		assertEquals("Harry Potter y la piedra filosofal",
				booksFound.get(0).getTitle());
		assertThat(booksFound.get(0).getGenre())
				.isNotEmpty();

		for(GenreEntity genre : booksFound.get(0).getGenre()) {
			assertNotNull(genre);
			assertThat(genre.getName().toLowerCase())
					.containsAnyOf("fantasia", "ciencia ficcion");
			assertNotNull(genre.getDescription());
			assertNotNull(genre.getExamples());
		}

		assertAll(
				() -> assertEquals(1L,
						booksFound.get(0).getAuthor().getId()),
				() -> assertEquals("J.K. Rowling",
						booksFound.get(0).getAuthor().getFullName()),
				() -> assertEquals(LocalDate.of(1965,7,31),
						booksFound.get(0).getAuthor().getBirthday()),
				() -> assertEquals("Yate, Reino Unido",
						booksFound.get(0).getAuthor().getPlaceOfBirth()),
				() -> assertEquals("British",
						booksFound.get(0).getAuthor().getNationality())
		);

		// Book Two
		assertEquals(2L,
				booksFound.get(1).getId());
		assertEquals("Harry Potter y la camara secreta",
				booksFound.get(1).getTitle());
		assertThat(booksFound.get(1).getGenre()).isNotEmpty();

		for(GenreEntity genre : booksFound.get(1).getGenre()) {
			assertThat(genre).isInstanceOf(GenreEntity.class);
			assertThat(genre.getName().toLowerCase())
					.containsAnyOf("fantasia", "ciencia ficcion");
			assertNotNull(genre.getDescription());
			assertNotNull(genre.getExamples());
		}

		assertAll(
				() -> assertEquals(1L,
						booksFound.get(0).getAuthor().getId()),
				() -> assertEquals("J.K. Rowling",
						booksFound.get(0).getAuthor().getFullName()),
				() -> assertEquals(LocalDate.of(1965,7,31),
						booksFound.get(0).getAuthor().getBirthday()),
				() -> assertEquals("Yate, Reino Unido",
						booksFound.get(0).getAuthor().getPlaceOfBirth()),
				() -> assertEquals("British",
						booksFound.get(0).getAuthor().getNationality())
		);

		// Book Three
		assertEquals(3L,
				booksFound.get(2).getId());
		assertEquals("Dune",
				booksFound.get(2).getTitle());
		assertThat(booksFound.get(2).getGenre()).isNotEmpty();

		for(GenreEntity g : booksFound.get(2).getGenre()) {
			assertNotNull(g);
			assertThat(g.getName().toLowerCase())
					.containsAnyOf("fantasia", "ciencia ficcion", "thriller");
			assertNotNull(g.getDescription());
			assertNotNull(g.getExamples());
		}

		assertAll(
				() -> assertEquals(2L,
						booksFound.get(2).getAuthor().getId()),
				() -> assertEquals("Frank Herbert",
						booksFound.get(2).getAuthor().getFullName()),
				() -> assertEquals(LocalDate.of(1920,10,8),
						booksFound.get(2).getAuthor().getBirthday()),
				() -> assertEquals("Tacoma, Washington",
						booksFound.get(2).getAuthor().getPlaceOfBirth()),
				() -> assertEquals("North American",
						booksFound.get(2).getAuthor().getNationality())
		);

		// Book Four
		assertEquals(4L,
				booksFound.get(3).getId());
		assertEquals("Asesinato en el Expreso de Oriente",
				booksFound.get(3).getTitle());
		assertThat(booksFound.get(3).getGenre()).isNotEmpty();

		for(GenreEntity g : booksFound.get(3).getGenre()) {
			assertNotNull(g);
			assertThat(g.getName().toLowerCase())
					.containsAnyOf("misterio", "thriller");
			assertNotNull(g.getDescription());
			assertNotNull(g.getExamples());
		}

		assertAll(
				() -> assertEquals(3L,
						booksFound.get(3).getAuthor().getId()),
				() -> assertEquals("Agatha Christie",
						booksFound.get(3).getAuthor().getFullName()),
				() -> assertEquals(LocalDate.of(1890,9,15),
						booksFound.get(3).getAuthor().getBirthday()),
				() -> assertEquals("Torquay, Reino Unido",
						booksFound.get(3).getAuthor().getPlaceOfBirth()),
				() -> assertEquals("British",
						booksFound.get(3).getAuthor().getNationality())
		);

		verify(bookRepository).findAll();
	}

	@Test
	@DisplayName("Test Find All Books - Empty Exception Thrown")
	void testFindAllBooksAndThrowsEmptyException() {
		List<BookEntity> booksEmpty = List.of();
		when(bookRepository.findAll()).thenReturn(booksEmpty);

		assertThrows(EmptyListFromDatabaseException.class,
				() -> databaseCenter.findAllBooks());
	}

	@Test
	@DisplayName("Test Save Book - Successful")
	void testSaveBookSuccessful() {
		BookEntity bookToSave = TestDataProvider.returnMuerteEnElNiloForTest();
		when(genreRepository.findByNameIgnoreCase("Misterio"))
				.thenReturn(TestDataProvider.returnMisteryGenreForTest());
		when(genreRepository.findByNameIgnoreCase("Thriller"))
				.thenReturn(TestDataProvider.returnThrillerGenreForTest());

		databaseCenter.saveBook(bookToSave);

		ArgumentCaptor<BookEntity> bookArgumentCaptor = ArgumentCaptor
				.forClass(BookEntity.class);
		verify(bookRepository).save(bookArgumentCaptor.capture());

		assertEquals(5L,
				bookArgumentCaptor.getValue().getId());
		assertEquals("Muerte en el Nilo",
				bookArgumentCaptor.getValue().getTitle());
		assertThat(bookArgumentCaptor.getValue().getGenre())
				.isNotEmpty();

		for(GenreEntity genre : bookArgumentCaptor.getValue().getGenre()) {
			assertNotNull(genre);
			assertThat(genre.getName().toLowerCase())
					.containsAnyOf("misterio", "thriller");
			assertNotNull(genre.getDescription());
			assertNotNull(genre.getExamples());
		}

		assertAll(
				() -> assertEquals(3L,
						bookArgumentCaptor.getValue().getAuthor().getId()),
				() -> assertEquals("Agatha Christie",
						bookArgumentCaptor.getValue().getAuthor().getFullName()),
				() -> assertEquals(LocalDate.of(1890,9,15),
						bookArgumentCaptor.getValue().getAuthor().getBirthday()),
				() -> assertEquals("Torquay, Reino Unido",
						bookArgumentCaptor.getValue().getAuthor().getPlaceOfBirth()),
				() -> assertEquals("British",
						bookArgumentCaptor.getValue().getAuthor().getNationality())
		);
	}

	@Test
	@DisplayName("Test Update Book - Success")
	void testUpdateBookSuccessful() {
		Long id = 5L;
		Optional<BookEntity> bookSaved = Optional.of(TestDataProvider
				.returnMuerteEnElNiloForTest());
		when(bookRepository.findById(anyLong())).thenReturn(bookSaved);
		BookEntity bookToUpdate = TestDataProvider.returnBookEntityOnlyTitleForTest();

		databaseCenter.updateBook(id, bookToUpdate);

		ArgumentCaptor<BookEntity> bookArgumentCaptor = ArgumentCaptor
				.forClass(BookEntity.class);
		verify(bookRepository).save(bookArgumentCaptor.capture());

		assertNotNull(bookArgumentCaptor);
		assertEquals(5L,
				bookArgumentCaptor.getValue().getId());
		assertEquals("Caceria en Venecia",
				bookArgumentCaptor.getValue().getTitle());
		assertThat(bookArgumentCaptor.getValue().getGenre()).isNotEmpty();

		for(GenreEntity genre : bookArgumentCaptor.getValue().getGenre()) {
			assertNotNull(genre);
			assertThat(genre.getName().toLowerCase())
					.containsAnyOf("misterio", "thriller");
			assertNotNull(genre.getDescription());
			assertNotNull(genre.getExamples());
		}

		assertAll(
				() -> assertEquals(3L,
						bookArgumentCaptor.getValue().getAuthor().getId()),
				() -> assertEquals("Agatha Christie",
						bookArgumentCaptor.getValue().getAuthor().getFullName()),
				() -> assertEquals(LocalDate.of(1890,9,15),
						bookArgumentCaptor.getValue().getAuthor().getBirthday()),
				() -> assertEquals("Torquay, Reino Unido",
						bookArgumentCaptor.getValue().getAuthor().getPlaceOfBirth()),
				() -> assertEquals("British",
						bookArgumentCaptor.getValue().getAuthor().getNationality())
		);

		verify(bookRepository).save(any(BookEntity.class));
	}

	@Test
	@DisplayName("Test Update Book - Not Found Exception Thrown")
	void testUpdateBookWithEmptyAndThrowsNotFoundException() {
		Long id = 1L;
		Optional<BookEntity> bookTest = Optional.empty();
		when(bookRepository.findById(anyLong())).thenReturn(bookTest);
		BookEntity bookForUpdate = TestDataProvider.returnDuneForTest();

		assertThrows(BookNotFoundException.class,
				() -> databaseCenter.updateBook(id, bookForUpdate));
	}

	@Test
	@DisplayName("Test Update Book - Null Title")
	void testUpdateBookWithNullTitleAndSetPreviousValue() {
		Long id = 10L;
		BookEntity bookToUpdate = TestDataProvider.returnBookEntityNullTitleForTest();
		Optional<BookEntity> bookFromDatabase = Optional.of(
				TestDataProvider.returnMuerteEnElNiloForTest()
		);
		when(bookRepository.findById(anyLong())).thenReturn(bookFromDatabase);
		when(genreRepository.findByNameIgnoreCase("Fantasia"))
				.thenReturn(TestDataProvider.returnFantasyGenreForTest());
		when(genreRepository.findByNameIgnoreCase("Ciencia Ficcion"))
				.thenReturn(TestDataProvider.returnFantasyGenreForTest());
		when(genreRepository.findByNameIgnoreCase("Thriller"))
				.thenReturn(TestDataProvider.returnFantasyGenreForTest());


		ArgumentCaptor<BookEntity> bookArgument = ArgumentCaptor
				.forClass(BookEntity.class);
		databaseCenter.updateBook(id, bookToUpdate);
		verify(bookRepository).save(bookArgument.capture());

		assertNotNull(bookArgument);
		assertEquals(5L,
				bookArgument.getValue().getId());
		assertEquals("Muerte en el Nilo",
				bookArgument.getValue().getTitle());
		assertThat(bookArgument.getValue().getGenre()).isNotEmpty();

		for(GenreEntity genre : bookArgument.getValue().getGenre()) {
			assertNotNull(genre);
			assertThat(genre.getName().toLowerCase())
					.containsAnyOf("ciencia ficcion", "thriller", "fantasia");
			assertNotNull(genre.getDescription());
			assertNotNull(genre.getExamples());
		}

		assertAll(
				() -> assertEquals(4L,
						bookArgument.getValue().getAuthor().getId()),
				() -> assertEquals("J.R.R. Tolkien",
						bookArgument.getValue().getAuthor().getFullName()),
				() -> assertEquals(LocalDate.of(1892,1,3),
						bookArgument.getValue().getAuthor().getBirthday()),
				() -> assertEquals("Bloemfontein, South Africa",
						bookArgument.getValue().getAuthor().getPlaceOfBirth()),
				() -> assertEquals("British",
						bookArgument.getValue().getAuthor().getNationality())
		);
	}

	@Test
	@DisplayName("Test Update Book - Null Genre")
	void testUpdateBookWithNullGenreAndSetPreviousValue() {
		Long id = 10L;
		BookEntity bookToUpdate = TestDataProvider.returnBookEntityNullGenreForTest();
		Optional<BookEntity> bookFromDatabase = Optional.of(
				TestDataProvider.returnMuerteEnElNiloForTest()
		);
		when(bookRepository.findById(anyLong())).thenReturn(bookFromDatabase);

		ArgumentCaptor<BookEntity> bookArgument = ArgumentCaptor
				.forClass(BookEntity.class);
		databaseCenter.updateBook(id, bookToUpdate);
		verify(bookRepository).save(bookArgument.capture());

		assertNotNull(bookArgument);
		assertEquals(5L,
				bookArgument.getValue().getId());
		assertEquals("Lord Of The Rings",
				bookArgument.getValue().getTitle());
		assertThat(bookArgument.getValue().getGenre()).isNotEmpty();

		for(GenreEntity genre : bookArgument.getValue().getGenre()) {
			assertNotNull(genre);
			assertThat(genre.getName().toLowerCase())
					.containsAnyOf("misterio", "thriller");
			assertNotNull(genre.getDescription());
			assertNotNull(genre.getExamples());
		}

		assertAll(
				() -> assertEquals(4L,
						bookArgument.getValue().getAuthor().getId()),
				() -> assertEquals("J.R.R. Tolkien",
						bookArgument.getValue().getAuthor().getFullName()),
				() -> assertEquals(LocalDate.of(1892,1,3),
						bookArgument.getValue().getAuthor().getBirthday()),
				() -> assertEquals("Bloemfontein, South Africa",
						bookArgument.getValue().getAuthor().getPlaceOfBirth()),
				() -> assertEquals("British",
						bookArgument.getValue().getAuthor().getNationality())
		);
	}

	@Test
	@DisplayName("Test Update Book - Null Author")
	void testUpdateBookWithNullAuthorAndSetPreviousValue() {
		Long id = 10L;
		BookEntity bookToUpdate = TestDataProvider.returnBookEntityNullAuthorForTest();
		Optional<BookEntity> bookFromDatabase = Optional.of(
				TestDataProvider.returnMuerteEnElNiloForTest()
		);
		when(bookRepository.findById(anyLong())).thenReturn(bookFromDatabase);
		when(genreRepository.findByNameIgnoreCase("Fantasia"))
				.thenReturn(TestDataProvider.returnFantasyGenreForTest());
		when(genreRepository.findByNameIgnoreCase("Ciencia Ficcion"))
				.thenReturn(TestDataProvider.returnFantasyGenreForTest());
		when(genreRepository.findByNameIgnoreCase("Thriller"))
				.thenReturn(TestDataProvider.returnFantasyGenreForTest());

		ArgumentCaptor<BookEntity> bookArgument = ArgumentCaptor
				.forClass(BookEntity.class);
		databaseCenter.updateBook(id, bookToUpdate);
		verify(bookRepository).save(bookArgument.capture());

		assertNotNull(bookArgument);
		assertEquals(5L,
				bookArgument.getValue().getId());
		assertEquals("Lord Of The Rings",
				bookArgument.getValue().getTitle());
		assertThat(bookArgument.getValue().getGenre()).isNotEmpty();

		for(GenreEntity genre : bookArgument.getValue().getGenre()) {
			assertNotNull(genre);
			assertThat(genre.getName().toLowerCase())
					.containsAnyOf("ciencia ficcion", "thriller", "fantasia");
			assertNotNull(genre.getDescription());
			assertNotNull(genre.getExamples());
		}

		assertAll(
				() -> assertEquals(3L,
						bookArgument.getValue().getAuthor().getId()),
				() -> assertEquals("Agatha Christie",
						bookArgument.getValue().getAuthor().getFullName()),
				() -> assertEquals(LocalDate.of(1890,9,15),
						bookArgument.getValue().getAuthor().getBirthday()),
				() -> assertEquals("Torquay, Reino Unido",
						bookArgument.getValue().getAuthor().getPlaceOfBirth()),
				() -> assertEquals("British",
						bookArgument.getValue().getAuthor().getNationality())
		);
	}

	@Test
	@DisplayName("Test Delete By ID - Success")
	void testDeleteByIdSuccessful() {
		Long id = 5L;
		when(bookRepository.existsById(anyLong())).thenReturn(true);

		databaseCenter.deleteBookById(id);

		verify(bookRepository).deleteById(anyLong());
	}

	@Test
	@DisplayName("Test Delete By ID - Not Found Exception Thrown")
	void testDeleteByIdAndThrowsBookNotFoundException() {
		Long id = 10L;
		when(bookRepository.existsById(anyLong())).thenReturn(false);

		assertThrows(BookNotFoundException.class,
				() -> databaseCenter.deleteBookById(id));

		verify(bookRepository).existsById(anyLong());
	}

	@Test
	@DisplayName("Get All Genres - Success")
	void testGetAllGenresSuccessful() {
		List<GenreEntity> genresAvailable = List.of(
				TestDataProvider.returnFantasyGenreForTest(),
				TestDataProvider.returnScienceFictionGenreForTest(),
				TestDataProvider.returnMisteryGenreForTest(),
				TestDataProvider.returnThrillerGenreForTest()
		);
		when(genreRepository.findAll()).thenReturn(genresAvailable);

		List<GenreEntity> genresFound = databaseCenter.getAllGenres();

		// Genre One
		assertEquals(1L,
				genresFound.get(0).getId());
		assertEquals("Fantasia",
				genresFound.get(0).getName());
		assertEquals("Historias que incluyen elementos mágicos o sobrenaturales, a menudo ambientadas en mundos imaginarios",
				genresFound.get(0).getDescription());
		assertEquals("The Lord of the Rings, Harry Potter, A Song of Iec and Fire",
				genresFound.get(0).getExamples());

		// Genre Two
		assertEquals(2L,
				genresFound.get(1).getId());
		assertEquals("Ciencia Ficcion",
				genresFound.get(1).getName());
		assertEquals("Historias que exploran escenarios futuros o alternativos, a menudo centradas en avances tecnológicos o científicos",
				genresFound.get(1).getDescription());
		assertEquals("Dune, Neuromancer, The Martian",
				genresFound.get(1).getExamples());

		// Genre Three
		assertEquals(3L,
				genresFound.get(2).getId());
		assertEquals("Misterio",
				genresFound.get(2).getName());
		assertEquals("Historias centradas en la resolución de un crimen o un enigma, a menudo con un detective como protagonista",
				genresFound.get(2).getDescription());
		assertEquals("The Da Vinci Code, Murder on the Orient Express, Gone Girl",
				genresFound.get(2).getExamples());

		// Genre Four
		assertEquals(4L,
				genresFound.get(3).getId());
		assertEquals("Thriller",
				genresFound.get(3).getName());
		assertEquals("Historias llenas de suspenso y tensión, a menudo con temas de peligro y persecución",
				genresFound.get(3).getDescription());
		assertEquals("The Girl with the Dragon Tattoo, The Silence of the Lamb, Gone Girl",
				genresFound.get(3).getExamples());

		verify(genreRepository).findAll();
	}

	@Test
	@DisplayName("Get Genre By Name - Success")
	void testGetGenreByNameSuccess() {
		String genreName = "misterio";
		GenreEntity genreTest = TestDataProvider.returnMisteryGenreForTest();
		when(genreRepository.findByNameIgnoreCase(anyString()))
				.thenReturn(genreTest);

		GenreEntity genreFound = databaseCenter.getGenreByName(genreName);

		assertEquals(3L,
				genreFound.getId());
		assertEquals("Misterio",
				genreFound.getName());
		assertEquals("Historias centradas en la resolución de un crimen o un enigma, a menudo con un detective como protagonista",
				genreFound.getDescription());
		assertEquals("The Da Vinci Code, Murder on the Orient Express, Gone Girl",
				genreFound.getExamples());
	}

	@Test
	@DisplayName("Get Genre By Name - Invalid Genre Exception Thrown")
	void testGetGenreByNameWithNullAndThrowsInvalidGenreException() {
		String genreName = "Comedia";
		when(genreRepository.findByNameIgnoreCase(anyString()))
				.thenReturn(null);

		assertThrows(InvalidGenreException.class,
				() -> databaseCenter.getGenreByName(genreName));

		verify(genreRepository).findByNameIgnoreCase(anyString());
	}

	@Test
	@DisplayName("Get All Authors - Success")
	void testGetAllAuthorsSuccessful() {
		List<AuthorEntity> authorsAvailable = List.of(
				TestDataProvider.returnJKRowlingForTest(),
				TestDataProvider.returnFrankHerbertForTest(),
				TestDataProvider.returnAgathaChristieForTest()
		);
		when(authorRepository.findAll()).thenReturn(authorsAvailable);

		List<AuthorEntity> authorsFound = databaseCenter.getAllAuthors();

		assertNotNull(authorsFound);
		assertThat(authorsFound).isNotEmpty();

		// Author One
		assertNotNull(authorsFound.get(0));
		assertEquals(1L,
				authorsFound.get(0).getId());
		assertEquals("J.K. Rowling",
				authorsFound.get(0).getFullName());
		assertEquals(LocalDate.of(1965,7,31),
				authorsFound.get(0).getBirthday());
		assertEquals("Yate, Reino Unido",
				authorsFound.get(0).getPlaceOfBirth());
		assertEquals("British",
				authorsFound.get(0).getNationality());

		// Author Two
		assertNotNull(authorsFound.get(1));
		assertEquals(2L,
				authorsFound.get(1).getId());
		assertEquals("Frank Herbert",
				authorsFound.get(1).getFullName());
		assertEquals(LocalDate.of(1920, 10, 8),
				authorsFound.get(1).getBirthday());
		assertEquals("Tacoma, Washington",
				authorsFound.get(1).getPlaceOfBirth());
		assertEquals("North American",
				authorsFound.get(1).getNationality());

		// Author Three
		assertNotNull(authorsFound.get(2));
		assertEquals(3L,
				authorsFound.get(2).getId());
		assertEquals("Agatha Christie",
				authorsFound.get(2).getFullName());
		assertEquals(LocalDate.of(1890, 9, 15),
				authorsFound.get(2).getBirthday());
		assertEquals("Torquay, Reino Unido",
				authorsFound.get(2).getPlaceOfBirth());
		assertEquals("British",
				authorsFound.get(2).getNationality());
	}

	@Test
	@DisplayName("Get All Authors - Empty List Exception Thrown")
	void testGetAllAuthorsWithEmptyAndThrowsEmptyListException() {
		List<AuthorEntity> booksEmpty = List.of();
		when(authorRepository.findAll())
				.thenReturn(booksEmpty);

		assertThrows(EmptyListFromDatabaseException.class,
				() -> databaseCenter.getAllAuthors());

		verify(authorRepository).findAll();
	}

	@Test
	@DisplayName("Get Author By Name - Success")
	void testGetAuthorByNameSuccessful() {
		String authorName = "frank";
		AuthorEntity authorTest = TestDataProvider.returnFrankHerbertForTest();
		when(authorRepository.findAuthorByName(anyString())).thenReturn(authorTest);

		AuthorEntity authorFound = databaseCenter.getAuthorByName(authorName);

		assertNotNull(authorFound);
		assertThat(authorFound.getFullName().toLowerCase()).contains(authorName);
		assertEquals(2L,
				authorFound.getId());
		assertEquals("Frank Herbert",
				authorFound.getFullName());
		assertEquals(LocalDate.of(1920, 10, 8),
				authorFound.getBirthday());
		assertEquals("Tacoma, Washington",
				authorFound.getPlaceOfBirth());
		assertEquals("North American",
				authorFound.getNationality());
	}

	@Test
	@DisplayName("Get Author By Name - Author Not Found Exception")
	void testGetAuthorByNameWithNullAndThrowsAuthorNotFoundException() {
		String authorName = "Garcia";
		when(authorRepository.findAuthorByName(anyString()))
				.thenReturn(null);

		assertThrows(AuthorNotFoundException.class,
				() -> databaseCenter.getAuthorByName(authorName));

		verify(authorRepository).findAuthorByName(anyString());
	}

	@Test
	@DisplayName("Complete Author Info - Success")
	void testCompleteAuthorInformationSuccessful() {
		Long id = 5L;
		AuthorEntity authorData = TestDataProvider.returnOtherInfoAuthorForTest();
		Optional<AuthorEntity> authorToUpdate =
				Optional.of(TestDataProvider.returnOnlyNameForTest());
		when(authorRepository.findById(anyLong())).thenReturn(authorToUpdate);

		databaseCenter.completeAuthorInfo(id, authorData);

		assertNotNull(authorToUpdate.get());
		assertEquals(4L,
				authorToUpdate.get().getId());
		assertEquals(LocalDate.of(1892,1,3),
				authorToUpdate.get().getBirthday());
		assertEquals("Bloemfontein, South Africa",
				authorToUpdate.get().getPlaceOfBirth());
		assertEquals("British",
				authorToUpdate.get().getNationality());

		verify(authorRepository).findById(anyLong());
		verify(authorRepository).save(any(AuthorEntity.class));
	}

	@Test
	@DisplayName("Complete Author Info - Throws Author Not Found Exception")
	void testCompleteAuthorInformationFindEmptyOptionalAndThrowsAuthorNotFoundException() {
		Long id = 10L;
		AuthorEntity authorParam = TestDataProvider.returnOnlyNameForTest();
		when(authorRepository.findById(anyLong()))
				.thenReturn(Optional.empty());

		assertThrows(AuthorNotFoundException.class,
				() -> databaseCenter.completeAuthorInfo(id, authorParam));

		verify(authorRepository).findById(anyLong());
	}
}
