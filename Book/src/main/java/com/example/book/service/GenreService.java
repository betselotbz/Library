package com.example.book.service;

import com.example.book.exception.InformationExistException;
import com.example.book.model.Genre;
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

    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /* assigning instances of 'GenreRepository'
     assigning instances of 'GenreRepository'
    These setter methods are used to inject (set) the GenreRepository and BookRepository instances into this service.
    @Autowired helps Spring provide instances of CategoryRepository and RecipeRepository and sets them as dependencies for your class.*/

    public Genre createGenre(Genre genreObject) {
        Genre genre = genreRepository.findByName(genreObject.getName());
        if (genre != null) {
            throw new InformationExistException("genre with name " + genreObject.getName() + " already exists");
        } else {
            return genreRepository.save(genreObject);
        }
    }
    /*creating a new genre in an application.
     It checks if a genre with the same name already exists in the database using genreRepository.
     If it does, it throws an exception.
     If not, it creates and saves the new genre to the database */
}
