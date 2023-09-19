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
    private List<Genre> genreList;


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

    @Override //overrides the default behavior defined in the Object class.
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

