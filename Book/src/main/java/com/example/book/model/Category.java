package com.example.book.model;

import javax.persistence.*;

@Entity
@Table(name = "categories")
public class Category {

    @Id //marks the id field as the primary key of our entity class.
    @Column // specifies that a field is mapped to a database column.
    @GeneratedValue(strategy = GenerationType.IDENTITY) //automatically generate unique ID values
    private Long id;
    @Column
    private String name;
    @Column
    private String description;


}

