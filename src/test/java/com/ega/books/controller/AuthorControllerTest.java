package com.ega.books.controller;

import com.ega.books.TestDataProvider;
import com.ega.books.domain.dto.AuthorDTO;
import com.ega.books.service.author.AuthorServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthorServiceImpl authorService;

    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(new AuthorController(authorService))
                .build();
    }

    @Test
    @DisplayName("Get Author By Name - Controller - Success")
    public void testGetAuthor() throws Exception {
        AuthorDTO author = TestDataProvider.returnFrankHerbertDtoForTest();
        when(authorService.getAuthorByName(anyString())).thenReturn(author);

        mockMvc.perform(get("/author/authorName")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("James"))
                .andExpect(status().isFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id")
                        .value(2L))
                .andExpect(jsonPath("$.fullName")
                        .value("Frank Herbert"))
                .andExpect(jsonPath("$.birthday")
                        .value("1920-10-08"))
                .andExpect(jsonPath("$.placeOfBirth")
                        .value("Tacoma, Washington"))
                .andExpect(jsonPath("$.nationality")
                        .value("North American"));
    }

    @Test
    @DisplayName("Get All Authors - Controller - Success")
    void testGetAllAuthorsSuccessful() throws Exception {
        List<AuthorDTO> authors = List.of(
                TestDataProvider.returnJKRowlingDtoForTest(),
                TestDataProvider.returnFrankHerbertDtoForTest(),
                TestDataProvider.returnAgathaChristieDtoForTest(),
                TestDataProvider.returnJRRTolkienDtoForTest()
        );
        when(authorService.getAllAuthors()).thenReturn(authors);

        mockMvc.perform(get("/author"))
                .andExpect(status().isFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id")
                        .value(1L))
                .andExpect(jsonPath("$[0].fullName")
                        .value("J.K. Rowling"))
                .andExpect((jsonPath("$[0].birthday"))
                        .value("1965-07-31"))
                .andExpect(jsonPath("$[0].placeOfBirth")
                        .value("Yate, Reino Unido"))
                .andExpect(jsonPath("$[0].nationality")
                        .value("British"))
                .andExpect(jsonPath("$[1].id")
                        .value(2L))
                .andExpect(jsonPath("$[1].fullName")
                        .value("Frank Herbert"))
                .andExpect((jsonPath("$[1].birthday"))
                        .value("1920-10-08"))
                .andExpect(jsonPath("$[1].placeOfBirth")
                        .value("Tacoma, Washington"))
                .andExpect(jsonPath("$[1].nationality")
                        .value("North American"))
                .andExpect(jsonPath("$[2].id")
                        .value(3L))
                .andExpect(jsonPath("$[2].fullName")
                        .value("Agatha Christie"))
                .andExpect((jsonPath("$[2].birthday"))
                        .value("1890-09-15"))
                .andExpect(jsonPath("$[2].placeOfBirth")
                        .value("Torquay, Reino Unido"))
                .andExpect(jsonPath("$[2].nationality")
                        .value("British"))
                .andExpect(jsonPath("$[3].id")
                        .value(4L))
                .andExpect(jsonPath("$[3].fullName")
                        .value("J.R.R. Tolkien"))
                .andExpect((jsonPath("$[3].birthday"))
                        .value("1892-01-03"))
                .andExpect(jsonPath("$[3].placeOfBirth")
                        .value("Bloemfontein, South Africa"))
                .andExpect(jsonPath("$[3].nationality")
                        .value("British"));
    }

    @Test
    @DisplayName("Complete Author Info - Controller - Success")
    void testCompleteAuthorInfoSuccessful() throws Exception {
        AuthorDTO author = TestDataProvider.returnJRRTolkienDtoForTest();
        doNothing().when(authorService).completeAuthorInfo(anyLong(), any(AuthorDTO.class));

        mockMvc.perform(put("/author/editAuthorInfo/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"birthday\" : \"1892-01-03\"," +
                        "\"placeOfBirth\" : \"Bloemfontein, South Africa\"," +
                        "\"nationality\" : \"British\"}"))
                .andExpect(status().isOk())
                .andExpect(content().encoding(StandardCharsets.ISO_8859_1))
                .andExpect(content()
                        .string("Los datos del autor han sido actualizados"));
    }
}
