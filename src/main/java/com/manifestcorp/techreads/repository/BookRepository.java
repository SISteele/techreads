package com.manifestcorp.techreads.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.manifestcorp.techreads.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}