package com.example.book.service;

import com.example.book.repository.BookRepository;
import com.example.book.repository.GenreRepository;
import org.springframework.stereotype.Service;

@Service
public class GenreService {
    private GenreRepository genreRepository;

    private BookRepository bookRepository;
    // These repositories are used to interact with the database for Genre and Book entities.
    //These repositories enable to retrieve, create, update, and delete records in the database for these entities.

    public void setGenreRepository(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }
    //assigning instances of 'GenreRepository'
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    //assigning instances of 'BookRepository'

    // These setter methods are used to inject (set) the GenreRepository and BookRepository instances into this service.
}
