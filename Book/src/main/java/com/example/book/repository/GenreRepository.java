package com.example.book.repository;
import com.example.book.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
    Genre findByName(String genreName);
//Find Genre entity by name and takes a genreName as input and
// returns the corresponding "Genre" object if found, or null if not found.
    Genre findByIdAndUserId(Long categoryId, Long userId);
    List<Genre> findByUserId(Long userId);
}
//Repository is an interface tha allows us to an perform a CRUD operation (Create, Update, and Delete) for Genre Objects
//It extends the JpaRepository interface, specifying that it works with the "Genre" entity and uses Long as the type for the primary key.