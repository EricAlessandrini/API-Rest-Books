package com.ega.books.controller;

import com.ega.books.TestDataProvider;
import com.ega.books.domain.dto.GenreDTO;
import com.ega.books.service.genre.GenreServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class GenreControllerTest {

    private MockMvc mockMvc;

    @MockBean
    private GenreServiceImpl genreService;

    /*@MockBean
    private GlobalControllerAdvice controllerAdvice;*/

    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(new GenreController(genreService))
                .build();
    }

    @Test
    @DisplayName("Get All Genres - Controller - Success")
    void getAllGenresSuccessful() throws Exception {
        List<GenreDTO> genres = List.of(
                TestDataProvider.returnFantasyGenreDtoForTest(),
                TestDataProvider.returnScienceFictionGenreDtoForTest(),
                TestDataProvider.returnMisteryGenreDtoForTest(),
                TestDataProvider.returnThrillerGenreDtoForTest()
        );
        when(genreService.getAllGenres()).thenReturn(genres);

        mockMvc.perform(get("/genre"))
                .andExpect(status().isFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name")
                        .value(genres.get(0).getName()))
                .andExpect(jsonPath("$[0].description")
                        .value(genres.get(0).getDescription()))
                .andExpect(jsonPath("$[0].examples")
                        .value(genres.get(0).getExamples()))
                .andExpect(jsonPath("$[1].name")
                        .value(genres.get(1).getName()))
                .andExpect(jsonPath("$[1].description")
                        .value(genres.get(1).getDescription()))
                .andExpect(jsonPath("$[1].examples")
                        .value(genres.get(1).getExamples()))
                .andExpect(jsonPath("$[2].name")
                        .value(genres.get(2).getName()))
                .andExpect(jsonPath("$[2].description")
                        .value(genres.get(2).getDescription()))
                .andExpect(jsonPath("$[2].examples")
                        .value(genres.get(2).getExamples()))
                .andExpect(jsonPath("$[3].name")
                        .value(genres.get(3).getName()))
                .andExpect(jsonPath("$[3].description")
                        .value(genres.get(3).getDescription()))
                .andExpect(jsonPath("$[3]examples")
                        .value(genres.get(3).getExamples()));
    }

    @Test
    @DisplayName("Get Genre By Name - Controller - Success")
    void testGetGenreByNameSuccessful() throws Exception {
        String genreName = "Any";
        GenreDTO genre = TestDataProvider.returnMisteryGenreDtoForTest();
        when(genreService.getGenreByName(anyString())).thenReturn(genre);

        mockMvc.perform(get("/genre/name")
                .contentType(MediaType.APPLICATION_JSON)
                .content(genreName))
                .andExpect(status().isFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name")
                        .value(genre.getName()))
                .andExpect(jsonPath("$.description")
                        .value(genre.getDescription()))
                .andExpect(jsonPath("$.examples")
                        .value(genre.getExamples()));
    }

    /*@Test
    @DisplayName("Get Genre By Name - Controller - Throws Invalid Genre Exception")
    void testGetGenreByNameAndThrowsInvalidGenreException() throws Exception {
        String genreName = "Comedia";
        when(genreService.getGenreByName(anyString())).thenThrow(new InvalidGenreException());

        mockMvc.perform(get("/genre/name")
                .contentType(MediaType.APPLICATION_JSON)
                .param("name", genreName))
                .andExpect(jsonPath("$.code")
                        .value("API-004"))
                .andExpect(jsonPath("$.message")
                        .value("The book's genre is invalid or doesn't exist in our DB"))
                .andExpect(jsonPath("$.details").doesNotExist())
                .andExpect(jsonPath("$.timestamp")
                        .exists());
    }*/
}
