package com.example.book.controller;

import com.example.book.model.Book;
import com.example.book.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.book.service.BookService;
import com.example.book.model.Genre;

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
    @GetMapping(path = "/genres/") // http://localhost:9094/api/genres/
    public List<Genre> getGenres() {
        return genreService.getGenres();
    }
//    @GetMapping(path = "/genres/{genreId}") // http://localhost:9094/api/genres/1/
//    public Optional<Genre> getGenre(@PathVariable(value = "genreId") Long genreId) {
//        return genreService.getGenre(genreId);
//
    @PostMapping(path = "/genres/") // http://localhost:9094/api/genres/
    public Genre createGenre(@RequestBody Genre genreObject) {
        return genreService.createGenre(genreObject);
    }
    @PutMapping(path = "/genres/{genreId}") // http://localhost:9094/api/genres/1/
    public Genre updateGenre(@PathVariable(value = "genreId") Long genreId, @RequestBody Genre genre) {
        return genreService.updateGenre(genreId, genre);
    }
    @DeleteMapping(path = "/genres/{genreId}") // http://localhost:9094/api/genres/1/
    public Optional<Genre> deleteGenre(@PathVariable(value = "genreId") Long genreId) {
        return genreService.deleteGenre(genreId);
    }
    @GetMapping(path = "/genres/{genreId}/books") // http://localhost:9092/api/genres/1/books/
    public List<Book> getGenreBooks(@PathVariable(value = "genreId") Long genreId) {
        return genreService.getGenresBooks(genreId);
    }
    @GetMapping(path = "/genres/{genreId}/books/{bookId}")
    public Book getGenreBook(@PathVariable(value = "genreId") Long genreId, @PathVariable(value = "bookId") Long bookId) {
        return genreService.getGenreBook(genreId, bookId);
    }


}
