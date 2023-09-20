package com.example.book.service;

import com.example.book.exception.InformationExistException;
import com.example.book.exception.InformationNotFoundException;
import com.example.book.model.Book;
import com.example.book.model.Genre;
import com.example.book.repository.BookRepository;
import com.example.book.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GenreService {
    private GenreRepository genreRepository;

    private BookRepository bookRepository;
    // These repositories are used to interact with the database for Genre and Book entities.
    //These repositories enable to retrieve, create, update, and delete records in the database for these entities.
@Autowired
    public void setGenreRepository(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }
@Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /* assigning instances of 'GenreRepository'
     assigning instances of 'GenreRepository'
    These setter methods are used to inject (set) the GenreRepository and BookRepository instances into this service.
    @Autowired helps Spring provide instances of GenreRepository and BookRepository and sets them as dependencies for the class.*/

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


    public Genre updateGenre(Long genreId, Genre genreObject) {
        System.out.print("service calling updateGenre ==> ");
        Optional<Genre> genreOptional = genreRepository.findById(genreId);

        if (genreOptional.isPresent()) {
            Genre existingGenre = genreOptional.get();

            if (genreObject.getName().equals(existingGenre.getName())) {
                System.out.println("Same");
                throw new InformationExistException("The genre name is already " + existingGenre.getName() + " and description is already " + existingGenre.getDescription());
            } else {
                // Update the existing genre
                existingGenre.setName(genreObject.getName());
                existingGenre.setDescription(genreObject.getDescription());

                // Save the updated genre back to the repository
                genreRepository.save(existingGenre);

                return existingGenre; // Return the updated genre
            }
        } else {
            throw new InformationNotFoundException("genre with id " + genreId + " not found");
        }
    }
    public List<Genre> getGenres(Long userId) {
        List<Genre> genraList = genreRepository.findByUserId(userId);
        if (genraList.isEmpty()) {
            throw new InformationNotFoundException("no genres found for user id " + userId);
        } else {
            return genraList;
        }
    }


    public Optional<Genre> deleteGenre(Long genreId) {
        // Retrieve a Genre with the given genreId from the repository.
        Optional<Genre> genreOptional = genreRepository.findById(genreId);

        if (genreOptional.isPresent()) {
            // If the Genre exists, delete it from the repository.
            genreRepository.deleteById(genreId);

            // Return the deleted Genre wrapped in an Optional.
            return genreOptional;
        } else {
            // If the Genre doesn't exist, throw an exception.
            throw new InformationNotFoundException("Genre with ID " + genreId + " not found");
        }
    }
    public Book createGenreBook(Long genreId, Book bookObject) {
        try {
            // Attempt to find the genre by its ID in the repository
            Optional<Genre> genreOptional = genreRepository.findById(genreId);

            // Check if the genre was found
            if (genreOptional.isPresent()) {
                // Set the genre of the book to the one found in the repository
                bookObject.setGenre(genreOptional.get());

                // Save the book object in the repository and return the newly created book
                return bookRepository.save(bookObject);
            } else {
                // Throw an exception if the genre with the specified ID is not found
                throw new InformationNotFoundException("Genre with ID " + genreId + " not found");
            }
        } catch (NoSuchElementException e) {
            // Handle the case where the genre is not found
            throw new InformationNotFoundException("Genre with ID " + genreId + " not found");
        }
    }
    public List<Book> getGenresBooks(Long genreId) {
        try {
            // Attempt to find the genre by its ID in the repository
            Optional<Genre> genreOptional = genreRepository.findById(genreId);

            // Check if the genre was found
            if (genreOptional.isPresent()) {
                // Get the genre object
                Genre genre = genreOptional.get();

                // Retrieve the list of books associated with the genre
                return genre.getBookList();
            } else {
                // Throw an exception if the genre with the specified ID is not found
                throw new InformationNotFoundException("Genre with ID " + genreId + " not found");
            }
        } catch (NoSuchElementException e) {
            // Handle the case where the genre is not found
            throw new InformationNotFoundException("Genre with ID " + genreId + " not found");
        }
    }
    public Book getGenreBook(Long genreId, Long bookId) {
        // Attempt to find the genre by its ID in the repository
        Optional<Genre> genreOptional = genreRepository.findById(genreId);

        // Attempt to find the book by its ID in the repository
        Optional<Book> bookOptional = bookRepository.findById(bookId);

        if (genreOptional.isPresent()) {

            if (bookOptional.isPresent()) {
                // Filter the list of books associated with the genre by book ID
                List<Book> book1 = genreOptional.get().getBookList().stream()
                        .filter(book -> book.getId().equals(bookId))
                        .collect(Collectors.toList());

                if (book1.isEmpty()) {
                    // Throw an exception if the specified book is not found within the genre
                    throw new InformationNotFoundException("Book with ID " + bookId + " not found in genre with ID " + genreId);
                }
                // Return the found book
                return book1.get(0);
            } else {
                // Throw an exception if the book with the specified ID is not found
                throw new InformationNotFoundException("Book with ID " + bookId + " not found");
            }

        } else {
            // Throw an exception if the genre with the specified ID is not found
            throw new InformationNotFoundException("Genre with ID " + genreId + " not found");
        }
    }

    public Book updateGenreBook(Long genreId, Long bookId, Book bookObject) {
        System.out.println("service calling updateGenreBook ==>");
        Optional<Genre> genre = genreRepository.findById(genreId);
        if (genre.isPresent()) {
            if (bookObject.getName().equals(genre.get().getName())) {
                // Handle the case where names are the same (if needed)
            } else {
                Genre updateGenre = genreRepository.findById(genreId).get();
                updateGenre.setName(bookObject.getName());
                updateGenre.setDescription(bookObject.getDescription());
                return bookRepository.save(bookObject);
            }
        } else {
            throw new InformationNotFoundException("Genre with id " + genreId + " not found");
        }
        return null;
    }
    public Optional<Book> deleteGenreBook(Long genreId, Long bookId) {
        Optional<Genre> genreOptional = genreRepository.findById(genreId);
        try {
            List<Book> book1 = genreOptional.get().getBookList().stream()
                    .filter(book -> book.getId().equals(bookId))
                    .collect(Collectors.toList());
            bookRepository.deleteById(book1.get(0).getId());
            return Optional.of(book1.get(0));
        } catch (NoSuchElementException e) {
            throw new InformationNotFoundException("Genre or book not found.");
        }
    }


}