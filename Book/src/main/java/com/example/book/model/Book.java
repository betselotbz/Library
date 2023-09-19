package com.example.book.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "books")
public class Book {
    private Long id;
    private String title;
    private String Author;
    private Long pages;
    private boolean available;

}
