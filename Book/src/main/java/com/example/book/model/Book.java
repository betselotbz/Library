package com.example.book.model;
import com.example.book.model.Genre;
import com.example.book.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jdk.jfr.Category;

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

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genre genre;
// Establishes a many-to-one relationship with the "Genre" entity
// using the "genre_id" column as a foreign key.
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
// Defines a many-to-one relationship with the "User" entity using the "user_id" column as a foreign key.

    public Book() {
        //default constructor
    }

    public Book(Long id, String title, String author, Long pages, boolean available, Genre genre) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.pages = pages;
        this.available = available;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Long getPages() {
        return pages;
    }

    public void setPages(Long pages) {
        this.pages = pages;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
//parameterized
    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

//non parameterized
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    @Override //overrides the default behavior defined in the Object class.
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", pages=" + pages +
                ", available='" + available +
                '}';
    }
}
