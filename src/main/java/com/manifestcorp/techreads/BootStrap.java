package com.manifestcorp.techreads;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.manifestcorp.techreads.model.Book;
import com.manifestcorp.techreads.repository.BookRepository;

import javax.naming.Context;

@Component
public class BootStrap implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    protected BookRepository bookRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if(bookRepository.count() < 3) {
            bookRepository.save(new Book("TestBook One", "TestAuthor One", "5/10", "https://www.iconpacks.net/icons/2/free-opened-book-icon-3169-thumb.png"));
            bookRepository.save(new Book("TestBook Two", "TestAuthor Two", "5/10", "https://www.iconpacks.net/icons/2/free-opened-book-icon-3169-thumb.png"));
            bookRepository.save(new Book("TestBook Three", "TestAuthor Three", "5/10", "https://www.iconpacks.net/icons/2/free-opened-book-icon-3169-thumb.png"));
        }
    }
}