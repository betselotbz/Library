package com.example.book.controller;

import com.example.book.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.book.service.BookService;
import com.example.book.model.Genre;

import java.util.List;

@RestController//annotation indicates that this class will handle incoming HTTP requests and provide responses.
@RequestMapping("/api")//http://localhost:9094/api
// annotation specifies the base path for the controller's endpoints, meaning that all endpoints defined within this controller will start with "/api" in their URL paths
public class GenreController {
    private GenreService genreService;
    @Autowired
    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }
    @GetMapping(path = "/categories/") // http://localhost:9092/api/categories/
    public List<Genre> getGenres() {
        return genreService.getGenres();
    }
}
