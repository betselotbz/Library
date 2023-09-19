package com.example.book.repository;

import com.example.book.model.Book;
import com.example.book.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository <Book, Long> {
    Book findByName(String bookName);
    Book findByIdAndUserId(Long categoryId, Long userId);
    List<Book> findByUserId(Long userId);
}
