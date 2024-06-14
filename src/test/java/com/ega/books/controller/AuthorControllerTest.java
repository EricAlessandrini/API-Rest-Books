package com.ega.books.controller;

import com.ega.books.domain.dto.AuthorDTO;
import com.ega.books.service.author.AuthorServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = AuthorController.class)
public class AuthorControllerTest {

    private MockMvc mockMvc;

    @Mock
    private AuthorServiceImpl authorService;

    @InjectMocks
    private AuthorController authorController;

    @Test
    void testGetAuthorByNameSuccess() throws Exception {
        String authorName = "Lucas";
        AuthorDTO response = AuthorDTO.builder()
                .id(2L)
                .fullName("J.R.R. Tolkien")
                .birthday(LocalDate.of(1994,4,29))
                .placeOfBirth("Cordoba")
                .nationality("Peruvian")
                .build();
        when(authorService.getAuthorByName(anyString())).thenReturn(response);

        mockMvc.perform(get("/author/authorName")
                .contentType(MediaType.APPLICATION_JSON)
                .content(authorName));

    }

}
