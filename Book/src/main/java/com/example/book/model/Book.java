package com.example.book.model;

import javax.persistence.*;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String title;
    @Column
    private String author;
    @Column
    private Long pages;
    @Column
    private boolean available;

    public Book() {
        //default constructor
    }

    public Book(Long id, String title, String author, Long pages, boolean available) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.pages = pages;
        this.available = available;
    }
}
