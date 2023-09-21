package com.example.book.controller;

import com.example.book.model.Book;
import com.example.book.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.book.model.Genre;
import java.lang.Long;


import java.util.List;
import java.util.Optional;

@RestController//annotation indicates that this class will handle incoming HTTP requests and provide responses.
@RequestMapping("/api")//http://localhost:9094/api
// annotation specifies the base path for the controller's endpoints, meaning that all endpoints defined within this controller will start with "/api" in their URL paths
public class GenreController {
    private GenreService genreService;

    @Autowired
    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    //retrieving a specific genre by its ID
    @GetMapping(path = "/genres/{genreId}") // http://localhost:8081/api/genres/1/
    public Optional<Genre> getGenre(@PathVariable(value = "genreId") Long genreId) {
        return genreService.getGenre(genreId);
    }
    //retrieving a list of all genres
    @GetMapping(path = "/genres/") // http://localhost:8081/api/genres/
    public List<Genre> getAllGenres() {
        return genreService.getGenres();
    }
    //Retrieving a list of books related with a specific genre(genreId)
    @GetMapping(path = "/genres/{genreId}/books") // http://localhost:8081/api/genres/1/books/
    public List<Book> getGenreBooks(@PathVariable(value = "genreId") Long genreId) {
        return genreService.getGenresBooks(genreId);
    }
    //Creating a new Genre
    @PostMapping(path = "/genres/") // http://localhost:8081/api/genres/
    public Genre createGenre(@RequestBody Genre genreObject) {
        return genreService.createGenre(genreObject);
    }
    //Creating a book within a specific genre(genreId)
    @PostMapping(path = "/genres/{genreId}/books/") // http://localhost:8081/api/genres/1/books/
    public Book createGenreBook(@PathVariable(value = "genreId") Long genreId, @RequestBody Book genreObject) {
        return genreService.createGenreBook(genreId, genreObject);
    }


    //Updating a specific genre
    @PutMapping(path = "/genres/{genreId}") // http://localhost:8081/api/genres/1/
    public Genre updateGenre(@PathVariable Long genreId, @RequestBody Genre genre) {
        return genreService.updateGenre(genreId, genre);
    }
    //Deleting a specific genre
    @DeleteMapping(path = "/genres/{genreId}") // http://localhost:8081/api/genres/1/
    public Optional<Genre> deleteGenre(@PathVariable(value = "genreId") Long genreId) {
        return genreService.deleteGenre(genreId);
    }
    //Deleting a specific book within a genre
    @DeleteMapping(path = "/genres/{genreId}/books/{bookId}") //http://localhost:8081/api/genres/1/books/1/
    public void deleteGenreBook(@PathVariable(value = "genreId") Long genreId, @PathVariable(value = "bookId") Long bookId) {
        genreService.deleteGenreBook(genreId, bookId);
    }

}
