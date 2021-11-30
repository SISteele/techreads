package com.manifestcorp.techreads.controller;

import com.manifestcorp.techreads.model.Book;
import com.manifestcorp.techreads.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class ResourceController {

    @Autowired
    BookRepository bookRepo;

    @GetMapping
    public Collection<Book> getBooks() {
        return bookRepo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> findBook(@PathVariable("id") long id) throws Exception {
        Optional<Book> book = bookRepo.findById(id);

        if(book.isPresent()) { return new ResponseEntity<>(book.get(), HttpStatus.OK);}
        else { return new ResponseEntity<>(HttpStatus.NOT_FOUND);}
    }

    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book newBook) {
        Book book = new Book(newBook);
        bookRepo.saveAndFlush(book);
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable("id") long id) {
        Optional<Book> book = bookRepo.findById(id);

        if(book.isPresent()) {
            bookRepo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else { return new ResponseEntity<>(HttpStatus.NOT_FOUND); }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> editBook(@PathVariable("id") long id, @RequestBody Book response) {
        Optional<Book> toEdit = bookRepo.findById(id);

        if(toEdit.isPresent()) {
            Book book = toEdit.get();
            book.copy(response);
            bookRepo.saveAndFlush(book);
            return new ResponseEntity<>(book, HttpStatus.OK);
        }
        else { return new ResponseEntity<>(HttpStatus.NOT_FOUND);}
    }
}