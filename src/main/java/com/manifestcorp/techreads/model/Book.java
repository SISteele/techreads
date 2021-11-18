package com.manifestcorp.techreads.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    String title;
    String author;
    String rating;
    String coverArt;

    public Book() {}

    public Book(String title, String author, String rating, String coverArt) {
        this.title = title;
        this.author= author;
        this.rating= rating;
        this.coverArt = coverArt;
    }


    public long getId() {return id; }
    public void setId(long id) {this.id = id;}


    public String getTitle() {return title;}
    public void setTitle(String title) {this.title = title;}

    public String getAuthor() {return author;}
    public void setAuthor(String author) {this.author = author;}

    public String getRating() {return rating;}
    public void setRating(String rating) {this.rating = rating;}

    public String getCoverArt() {return coverArt;}
    public void setCoverArt(String coverArt) {this.coverArt = coverArt;}
}