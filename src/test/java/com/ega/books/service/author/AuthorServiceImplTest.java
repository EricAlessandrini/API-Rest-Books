package com.ega.books.service.author;

import com.ega.books.TestDataProvider;
import com.ega.books.domain.entity.AuthorEntity;
import com.ega.books.persistence.dao.DatabaseCenterImpl;
import com.ega.books.utils.AuthorMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AuthorServiceImplTest {

    @Mock
    private DatabaseCenterImpl databaseCenter;
    @Mock
    private AuthorMapper authorMapper;
    @InjectMocks
    private AuthorServiceImpl authorService;

    @Test
    @DisplayName("Get Author By Name - Success")
    void testGetAuthorByNameSuccessful() {
        String authorName = "Agatha";
        AuthorEntity authorFromDatabase = TestDataProvider.returnAgathaChristieForTest();
    }
}
