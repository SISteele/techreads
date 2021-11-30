package com.manifestcorp.techreads.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.manifestcorp.techreads.model.Book;
import com.manifestcorp.techreads.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

@WebMvcTest(BookController.class)
public class BookControllerTests {

    @Autowired
    private MockMvc mock;

    @MockBean
    private BookRepository bookRepo;
    private List<Book> books;

    @BeforeEach
    void setup() {
        this.books = new ArrayList<Book>();
        for(int i = 0; i < 10; i++) {
            books.add(i, new Book("Book #" + i, "Author", "URL", "5/10"));
        }

        given(this.bookRepo.findAll()).willReturn(this.books);
    }

    @Test
    void testInitBookList() throws Exception {
        mock.perform(get("/books")).andExpect(status().isOk())
                .andExpect(model().attribute("books", books))
                .andExpect(view().name("books"));
    }

    @Test
    void testAddBookPage() throws Exception {
        mock.perform(get("/books/add")).andExpect(status().isOk())
                .andExpect(model().attribute("bookForm.title", new Book().getTitle()))
                .andExpect(view().name("add"));
    }

    @Test
    void testAddBookFormSubmit() throws Exception {
        mock.perform(post("/books/add").param("title", "addTest")
                .param("author", "addAuthor").param("rating", "5/5")
                .param("coverArt", "addURL")).andExpect(status().isOk());
    }

/*    @Test
    void testNewBookAdded() throws Exception {
        mock.perform(post("/books/add").param("title", "addTest")
                .param("author", "addAuthor").param("rating", "5/5")
                .param("coverArt", "addURL"));
        mock.perform(get("/books")).andExpect(status().isOk());
        assertEquals(11, books.size());
    }

    @Test
    void testRemoveBook() throws Exception {
        mock.perform(get("/remove/1")).andExpect(status().isOk());
        assertEquals(9, bookRepo.findAll().size());
    }*/

}