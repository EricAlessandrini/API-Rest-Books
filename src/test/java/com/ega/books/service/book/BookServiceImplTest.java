package com.ega.books.service.book;

import com.ega.books.TestDataProvider;
import com.ega.books.domain.dto.BookDTO;
import com.ega.books.domain.entity.BookEntity;
import com.ega.books.persistence.dao.DatabaseCenterImpl;
import com.ega.books.utils.BookMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceImplTest {

    @Mock
    private DatabaseCenterImpl databaseCenter;

    @Mock
    private BookMapper bookMapper;

    @InjectMocks
    private BookServiceImpl bookService;

    @Test
    void testFindBookByTitleSuccessful() {
        List<BookEntity> booksObtainedFromDatabase = List.of(
                TestDataProvider.returnAsesinatoExpresoOrienteForTest()
        );
        when(databaseCenter.findBookByTitle(anyString())).thenReturn(booksObtainedFromDatabase);

        BookDTO bookMapped = TestDataProvider.returnAsesinatoExpresoOrienteDtoForTest();
        when(bookMapper.entityToDTO(booksObtainedFromDatabase.get(0))).thenReturn(bookMapped);
        List<BookDTO> booksMapped = List.of(bookMapped);

        List<BookDTO> books = bookService.findBookByTitle("Oriente");

        assertBookDTOValues(booksMapped.get(0), books.get(0));

        verify(databaseCenter, times(1)).findBookByTitle(anyString());
        verify(bookMapper, times(1)).entityToDTO(any(BookEntity.class));
    }

    @Test
    void testFindBooksByAuthorNameSuccessful() {
        List<BookEntity> booksObtainedFromDatabase = List.of(
                TestDataProvider.returnHarryPotterPiedraFilosofalForTest(),
                TestDataProvider.returnHarryPotterCamaraSecretaForTest()
        );
        when(databaseCenter.findBooksByAuthorName(anyString())).thenReturn(booksObtainedFromDatabase);

        BookDTO bookMapped1 = TestDataProvider.returnHarryPotterPiedraFilosofalDtoForTest();
        when(bookMapper.entityToDTO(booksObtainedFromDatabase.get(0))).thenReturn(bookMapped1);
        BookDTO bookMapped2 = TestDataProvider.returnHarryPotterCamaraSecretaDtoForTest();
        when(bookMapper.entityToDTO(booksObtainedFromDatabase.get(1))).thenReturn(bookMapped2);
        List<BookDTO> booksMapped = List.of(bookMapped1, bookMapped2);

        List<BookDTO> books = bookService.findBooksByAuthorName("Rowling");

        assertBookDTOValues(booksMapped.get(0), books.get(0));
        assertBookDTOValues(booksMapped.get(1), books.get(1));

        verify(databaseCenter, times(1)).findBooksByAuthorName(anyString());
        verify(bookMapper, times(2)).entityToDTO(any(BookEntity.class));
    }

    @Test
    void testFindBooksByGenreNameSuccessful() {
        String genreName = "Fantasia";
        List<BookEntity> booksObtainedFromDatabaseCenter = List.of(
                TestDataProvider.returnHarryPotterPiedraFilosofalForTest(),
                TestDataProvider.returnHarryPotterCamaraSecretaForTest()
        );
        when(databaseCenter.findBooksByGenreName(anyString())).thenReturn(booksObtainedFromDatabaseCenter);

        BookDTO bookMapped1 = TestDataProvider.returnHarryPotterPiedraFilosofalDtoForTest();
        when(bookMapper.entityToDTO(booksObtainedFromDatabaseCenter.get(0))).thenReturn(bookMapped1);
        BookDTO bookMapped2 = TestDataProvider.returnHarryPotterCamaraSecretaDtoForTest();
        when(bookMapper.entityToDTO(booksObtainedFromDatabaseCenter.get(1))).thenReturn(bookMapped2);
        List<BookDTO> booksMapped = List.of(bookMapped1, bookMapped2);

        List<BookDTO> books = bookService.findBooksByGenreName(genreName);

        assertBookDTOValues(booksMapped.get(0), books.get(0));
        assertBookDTOValues(booksMapped.get(1), books.get(1));

        verify(databaseCenter, times(1)).findBooksByGenreName(anyString());
        verify(bookMapper, times(2)).entityToDTO(any(BookEntity.class));
    }

    @Test
    void testFindAllBooksSuccessful() {
        List<BookEntity> booksObtainedFromDatabaseCenter = List.of(
                TestDataProvider.returnDuneForTest(),
                TestDataProvider.returnAsesinatoExpresoOrienteForTest()
        );
        when(databaseCenter.findAllBooks()).thenReturn(booksObtainedFromDatabaseCenter);

        BookDTO bookMapped1 = TestDataProvider.returnDuneDtoForTest();
        when(bookMapper.entityToDTO(booksObtainedFromDatabaseCenter.get(0)))
                .thenReturn(bookMapped1);
        BookDTO bookMapped2 = TestDataProvider.returnAsesinatoExpresoOrienteDtoForTest();
        when(bookMapper.entityToDTO(booksObtainedFromDatabaseCenter.get(1)))
                .thenReturn(bookMapped2);
        List<BookDTO> booksMapped = List.of(bookMapped1, bookMapped2);

        List<BookDTO> books = bookService.findAllBooks();

        assertBookDTOValues(booksMapped.get(0), books.get(0));
        assertBookDTOValues(booksMapped.get(1), books.get(1));

        verify(databaseCenter, times(1)).findAllBooks();
        verify(bookMapper, times(2)).entityToDTO(any(BookEntity.class));
    }

    @Test
    void testSaveBookSuccessful() {
        BookDTO bookObtainedFromClient = TestDataProvider.returnDuneDtoForTest();
        BookEntity bookMapped = TestDataProvider.returnDuneForTest();
        when(bookMapper.dtoToEntity(bookObtainedFromClient)).thenReturn(bookMapped);

        bookService.saveBook(bookObtainedFromClient);

        verify(bookMapper, times(1)).dtoToEntity(any(BookDTO.class));

        ArgumentCaptor<BookEntity> entityCaptor = ArgumentCaptor.forClass(BookEntity.class);
        verify(databaseCenter, times(1)).saveBook(entityCaptor.capture());

        assertBookEntityValues(bookMapped, entityCaptor.getValue());
    }

    @Test
    void testUpdateBookSuccessful() {
        Long idFromClient = 2L;
        BookDTO bookObtainedFromClient = TestDataProvider.returnAsesinatoExpresoOrienteDtoForTest();
        BookEntity bookMapped = TestDataProvider.returnAsesinatoExpresoOrienteForTest();
        when(bookMapper.dtoToEntity(bookObtainedFromClient))
                .thenReturn(bookMapped);

        bookService.updateBook(idFromClient, bookObtainedFromClient);

        verify(bookMapper, times(1))
                .dtoToEntity(bookObtainedFromClient);

        ArgumentCaptor<BookEntity> entityCaptor = ArgumentCaptor.forClass(BookEntity.class);
        verify(databaseCenter, times(1))
                .updateBook(eq(idFromClient), entityCaptor.capture());

        assertBookEntityValues(bookMapped, entityCaptor.getValue());
    }

    @Test
    void testDeleteBookSuccessful() {
        Long idFromClient = 3L;
        doNothing().when(databaseCenter).deleteBookById(anyLong());

        bookService.deleteBook(idFromClient);

        verify(databaseCenter, times(1))
                .deleteBookById(eq(idFromClient));

    }

    private void assertBookDTOValues(BookDTO expected, BookDTO actual) {
        assertNotNull(actual);
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getTitle(), actual.getTitle());
        assertEquals(expected.getGenre(), actual.getGenre());
        assertEquals(expected.getAuthor(), actual.getAuthor());
    }

    private void assertBookEntityValues(BookEntity expected, BookEntity actual) {
        assertNotNull(actual);
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getTitle(), actual.getTitle());
        assertEquals(expected.getGenre(), actual.getGenre());
        assertEquals(expected.getAuthor(), actual.getAuthor());
    }
}
