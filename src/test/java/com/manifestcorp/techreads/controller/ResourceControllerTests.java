package com.manifestcorp.techreads.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.manifestcorp.techreads.model.Book;
import com.manifestcorp.techreads.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ResourceController.class)
public class ResourceControllerTests {
    @Autowired
    private ObjectMapper map;

    @Autowired
    private MockMvc mock;

    @MockBean
    private BookRepository bookRepo;

    private List<Book> books;
    private Book testBook;
    private final long MISSING_ID = 01;
    private final long TEST_ID = 1l;

    @BeforeEach
    void setup() {
        this.books = new ArrayList<Book>();
        for(int i = 0; i < 10; i++) {
            books.add(i, new Book("Book # " + i, "Author", "Rating", "Cover Art"));
        }
        testBook = new Book("Test Copy", "Test Author", "Test Rating", "Test Art");
    }

    @Test
    void testGetBooks() throws Exception {
        when(bookRepo.findAll()).thenReturn(books);

        mock.perform(get("/api/books").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(10)))
                .andExpect(jsonPath("$[1].title", is("Book # 1")));

        verify(bookRepo).findAll();
    }

    @Test
    void testMissingBook() throws Exception {
        when(bookRepo.findById(MISSING_ID)).thenReturn(Optional.empty());

        mock.perform(get("/api/books/"+MISSING_ID).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verify(bookRepo).findById(MISSING_ID);
    }

    @Test
    void testAddBook() throws Exception {
        when(bookRepo.saveAndFlush(argThat((Book book) -> book.getTitle().equals(testBook.getTitle())))).thenReturn(testBook);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/api/books")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.map.writeValueAsString(testBook));

        mock.perform(mockRequest)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.title", is(testBook.getTitle())));

        verify(bookRepo).saveAndFlush(argThat((Book book) -> book.getTitle().equals(testBook.getTitle())));
    }

    @Test
    void testDeleteBook() throws Exception {
        when(bookRepo.findById(TEST_ID)).thenReturn(Optional.of(books.get(1)));
        doNothing().when(bookRepo).deleteById(TEST_ID);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.delete("/api/books/"+TEST_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        mock.perform(mockRequest).andExpect(status().isNoContent());

        verify(bookRepo, times(1)).deleteById(TEST_ID);
    }

    @Test
    void testEditBook() throws Exception {
        when(bookRepo.findById(TEST_ID)).thenReturn(Optional.of(books.get(1)));
        when(bookRepo.saveAndFlush(argThat((Book book) -> book.getTitle().equals(testBook.getTitle())))).thenReturn(testBook);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/api/books/"+TEST_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.map.writeValueAsString(testBook));

        mock.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.title", is(testBook.getTitle())));

        verify(bookRepo).findById(TEST_ID);
        verify(bookRepo).saveAndFlush(argThat((Book book) -> book.getTitle().equals(testBook.getTitle())));
    }

    @Test
    void testEditFail() throws Exception {
        when(bookRepo.findById(MISSING_ID)).thenReturn(Optional.empty());

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/api/books/"+MISSING_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.map.writeValueAsString(testBook));

        mock.perform(mockRequest).andExpect(status().isNotFound());

        verify(bookRepo).findById(MISSING_ID);
    }

}