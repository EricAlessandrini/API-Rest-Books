package com.ega.books.service.author;

import com.ega.books.TestDataProvider;
import com.ega.books.domain.dto.AuthorDTO;
import com.ega.books.domain.entity.AuthorEntity;
import com.ega.books.persistence.dao.DatabaseCenterImpl;
import com.ega.books.utils.mappers.AuthorMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthorServiceImplTest {

    @Mock
    private DatabaseCenterImpl databaseCenter;

    @Mock
    private AuthorMapper authorMapper;

    @InjectMocks
    private AuthorServiceImpl authorService;

    @Test
    @DisplayName("Get Author By Name - Service - Success")
    void testGetAuthorByNameSuccessful() {
        String authorName = "Agatha";

        AuthorEntity authorFromDatabase = TestDataProvider.returnAgathaChristieForTest();
        when(databaseCenter.getAuthorByName(authorName)).thenReturn(authorFromDatabase);
        AuthorDTO authorDto = TestDataProvider.returnAgathaChristieDtoForTest();
        when(authorMapper.entityToDTO(authorFromDatabase)).thenReturn(authorDto);

        AuthorDTO author = authorService.getAuthorByName(authorName);

        assertAuthorDtoValues(authorDto, author);

        verify(databaseCenter, times(1)).getAuthorByName(anyString());
        verify(authorMapper, times(1)).entityToDTO(any(AuthorEntity.class));
    }

    @Test
    @DisplayName("Get All Authors - Service - Success")
    void testGetAllAuthorsSuccessful() {
        List<AuthorEntity> authorEntities = List.of(
                TestDataProvider.returnFrankHerbertForTest()
        );
        when(databaseCenter.getAllAuthors()).thenReturn(authorEntities);
        AuthorDTO authorFrank = TestDataProvider.returnFrankHerbertDtoForTest();
        when(authorMapper.entityToDTO(authorEntities.get(0))).thenReturn(authorFrank);
        List<AuthorDTO> authorsDto = List.of(authorFrank);

        List<AuthorDTO> listAuthor = authorService.getAllAuthors();

        assertAuthorDtoValues(authorsDto.get(0), listAuthor.get(0));

        verify(databaseCenter, times(1)).getAllAuthors();
        verify(authorMapper, times(1)).entityToDTO(any(AuthorEntity.class));
    }

    @Test
    void testCompleteAuthorInfoSuccessful() {
        AuthorDTO author = TestDataProvider.returnJRRTolkienDtoForTest();
        AuthorEntity authorToUpdate = TestDataProvider.returnJRRTolkienForTest();
        when(authorMapper.dtoToEntity(author)).thenReturn(authorToUpdate);

        Long id = 2L;
        doNothing().when(databaseCenter).completeAuthorInfo(anyLong(), any(AuthorEntity.class));

        authorService.completeAuthorInfo(id, author);

        verify(authorMapper, times(1)).dtoToEntity(any(AuthorDTO.class));

        ArgumentCaptor<AuthorEntity> authorEntityCaptor = ArgumentCaptor.forClass(AuthorEntity.class);
        verify(databaseCenter, times(1)).completeAuthorInfo(eq(id), authorEntityCaptor.capture());

        assertAuthorEntityValues(authorToUpdate, authorEntityCaptor.getValue());
    }

    private void assertAuthorDtoValues(AuthorDTO expected, AuthorDTO actual) {
        assertNotNull(actual);
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getFullName(), actual.getFullName());
        assertEquals(expected.getBirthday(), actual.getBirthday());
        assertEquals(expected.getPlaceOfBirth(), actual.getPlaceOfBirth());
        assertEquals(expected.getNationality(), actual.getNationality());
    }

    private void assertAuthorEntityValues(AuthorEntity expected, AuthorEntity actual) {
        assertNotNull(actual);
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getFullName(), actual.getFullName());
        assertEquals(expected.getBirthday(), actual.getBirthday());
        assertEquals(expected.getPlaceOfBirth(), actual.getPlaceOfBirth());
        assertEquals(expected.getNationality(), actual.getNationality());
    }
}
