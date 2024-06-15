package com.ega.books.controller;

import com.ega.books.TestDataProvider;
import com.ega.books.domain.dto.BookDTO;
import com.ega.books.service.book.BookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTest {

    private MockMvc mockMvc;

    @MockBean
    private BookServiceImpl bookService;

    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(new BookController(bookService))
                .build();
    }

    @Test
    @DisplayName("Get Book By Title - Controller - Success")
    void testGetBookByTitleSuccessful() throws Exception {
        List<BookDTO> books = List.of(
                TestDataProvider.returnHarryPotterPiedraFilosofalDtoForTest(),
                TestDataProvider.returnHarryPotterCamaraSecretaDtoForTest()
        );
        when(bookService.findBookByTitle(anyString())).thenReturn(books);

        mockMvc.perform(get("/books/title")
                .contentType(MediaType.APPLICATION_JSON)
                .content("Any"))
                .andExpect(status().isFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].title").value("Harry Potter y la piedra filosofal"))
                .andExpect(jsonPath("$[0].genre").isArray())
                .andExpect(jsonPath("$[0].genre", hasSize(2)))
                .andExpect(jsonPath("$[0].genre[0]").value("Ciencia Ficcion"))
                .andExpect(jsonPath("$[0].genre[1]").value("Fantasia"))
                .andExpect(jsonPath("$[0].author").value("J.K. Rowling"))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].title").value("Harry Potter y la camara secreta"))
                .andExpect(jsonPath("$[1].genre").isArray())
                .andExpect(jsonPath("$[1].genre", hasSize(2)))
                .andExpect(jsonPath("$[1].genre[0]").value("Ciencia Ficcion"))
                .andExpect(jsonPath("$[1].genre[1]").value("Fantasia"))
                .andExpect(jsonPath("$[1].author").value("J.K. Rowling"));
    }

    @Test
    @DisplayName("Get All Books - Controller - Success")
    void testGetAllBooksSuccessful() throws Exception {
        List<BookDTO> books = List.of(
                TestDataProvider.returnHarryPotterPiedraFilosofalDtoForTest(),
                TestDataProvider.returnHarryPotterCamaraSecretaDtoForTest(),
                TestDataProvider.returnDuneDtoForTest(),
                TestDataProvider.returnAsesinatoExpresoOrienteDtoForTest()
        );
        when(bookService.findAllBooks()).thenReturn(books);

        mockMvc.perform(get("/books")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].title").value("Harry Potter y la piedra filosofal"))
                .andExpect(jsonPath("$[0].genre").isArray())
                .andExpect(jsonPath("$[0].genre", hasSize(2)))
                .andExpect(jsonPath("$[0].author").value("J.K. Rowling"))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].title").value("Harry Potter y la camara secreta"))
                .andExpect(jsonPath("$[1].genre").isArray())
                .andExpect(jsonPath("$[1].genre", hasSize(2)))
                .andExpect(jsonPath("$[1].author").value("J.K. Rowling"))
                .andExpect(jsonPath("$[2].id").value(3L))
                .andExpect(jsonPath("$[2].title").value("Dune"))
                .andExpect(jsonPath("$[2].genre").isArray())
                .andExpect(jsonPath("$[2].genre", hasSize(3)))
                .andExpect(jsonPath("$[2].author").value("Frank Herbert"))
                .andExpect(jsonPath("$[3].id").value(4L))
                .andExpect(jsonPath("$[3].title").value("Asesinato en el Expreso de Oriente"))
                .andExpect(jsonPath("$[3].genre").isArray())
                .andExpect(jsonPath("$[3].genre", hasSize(2)))
                .andExpect(jsonPath("$[3].author").value("Agatha Christie"));
    }

    @Test
    @DisplayName("Save Book - Controller - Success")
    void testSaveBookSuccess() throws Exception {
        doNothing().when(bookService).saveBook(any(BookDTO.class));

        mockMvc.perform(post("/books/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\" : \"Lord Of The Rings\"," +
                        "\"genre\" : [\"Fantasia\", \"Thriller\"]," +
                        "\"author\" : \"J.R.R. Tolkien\"}"))
                .andExpect(status().isCreated())
                .andExpect(content().encoding(StandardCharsets.ISO_8859_1))
                .andExpect(content().string("El libro fue guardado con exito"));
    }
}
