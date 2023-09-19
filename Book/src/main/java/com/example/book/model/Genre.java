package com.example.book.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "genres")
public class Genre {

    @Id //marks the id field as the primary key of our entity class.
    @Column // specifies that a field is mapped to a database column.
    @GeneratedValue(strategy = GenerationType.IDENTITY) //automatically generate unique ID values
    private Long id;
    @Column
    private String name;
    @Column
    private String description;

    // This field represents a list of related genres for the current genre.
// It establishes a one-to-many relationship where one genre can have multiple sub-genres.
// The "mappedBy" attribute indicates that the relationship is managed by the "genre" field in the sub-genre entity.
// Setting "orphanRemoval" to true ensures that if a sub-genre(book) is removed from this list, it will be removed from the database.
// Using LazyCollectionOption.FALSE ensures that the genreList is eagerly loaded when retrieving a genre object.
    @OneToMany(mappedBy = "genre", orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Book> bookList; //private list named genreList to store multiple genre objects, typically representing the genres associated with something (e.g., a book or a user).

    @ManyToOne
    @JoinColumn(name = "user_id")//(genre-user relationship)name of foreign key in 'genre' table referencing the primary key of the users table
    @JsonIgnore
    private User user; // Genre has a Many-to-One relationship with another entity('User')

//this annotation specifies how the foreign key relationship should be mapped in the database. It tells JPA that the user field in the Genre entity is linked to a column named user_id in the underlying database table


    public Genre() {
        //default constructor
    }
    public Genre(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    //getter and setter to define one to many bw/ an entity and bookList
    //this method helps access and manage this list of books.

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    //In summary, these getter and setter methods provide controlled access to the user property,
    // allowing us to both retrieve and modify the associated User object within an instance of the class.
    //These methods provide a way to modify the User association without requiring it to be provided during the initial construction of a Genre object.

    @Override //overrides the default behavior defined in the Object class.
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

