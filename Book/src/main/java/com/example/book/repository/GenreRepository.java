package com.example.book.repository;
import com.example.book.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {


}
//Repository is an interface tha allows us to an perform a CRUD operation (Create, Update, and Delete) for Genre Objects
//It extends the JpaRepository interface, specifying that it works with the "Genre" entity and uses Long as the type for the primary key.