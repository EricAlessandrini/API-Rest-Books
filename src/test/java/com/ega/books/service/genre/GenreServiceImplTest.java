package com.ega.books.service.genre;

import com.ega.books.TestDataProvider;
import com.ega.books.domain.dto.GenreDTO;
import com.ega.books.domain.entity.GenreEntity;
import com.ega.books.persistence.dao.DatabaseCenterImpl;
import com.ega.books.utils.GenreMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
public class GenreServiceImplTest {

    @Mock
    private DatabaseCenterImpl databaseCenter;

    @Mock
    private GenreMapper genreMapper;

    @InjectMocks
    private GenreServiceImpl genreService;

    @Test
    void testGetAllGenresSuccessful() {
        List<GenreEntity> genresObtainedFromDatabaseCenter = List.of(
                TestDataProvider.returnMisteryGenreForTest()
        );
        when(databaseCenter.getAllGenres()).thenReturn(genresObtainedFromDatabaseCenter);

        GenreDTO genreMapped = TestDataProvider.returnMisteryGenreDtoForTest();
        when(genreMapper.entityToDTO(any(GenreEntity.class))).thenReturn(genreMapped);
        List<GenreDTO> genresMockObtained = List.of(genreMapped);

        List<GenreDTO> genres = genreService.getAllGenres();

        assertGenreDtoValues(genresMockObtained.get(0), genres.get(0));
    }

    @Test
    void testGetGenreByNameSuccessful() {
        String genreName = "Misterio";
        GenreEntity genreObtainedByName = TestDataProvider.returnMisteryGenreForTest();
        when(databaseCenter.getGenreByName(anyString())).thenReturn(genreObtainedByName);

        GenreDTO genreMapped = TestDataProvider.returnMisteryGenreDtoForTest();
        when(genreMapper.entityToDTO(genreObtainedByName)).thenReturn(genreMapped);

        GenreDTO genre = genreService.getGenreByName(genreName);

        assertGenreDtoValues(genreMapped, genre);

        verify(genreMapper, times(1)).entityToDTO(any(GenreEntity.class));

    }

    private void assertGenreDtoValues(GenreDTO expected, GenreDTO actual) {
        assertNotNull(actual);
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getDescription(), actual.getDescription());
        assertEquals(expected.getExamples(), actual.getExamples());
    }
}
