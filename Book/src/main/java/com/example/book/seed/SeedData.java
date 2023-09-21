package com.example.book.seed;

import com.example.book.model.Genre; // Updated entity name
import com.example.book.model.Book;  // Updated entity name
import com.example.book.model.User;
import com.example.book.repository.UserRepository;
import com.example.book.repository.GenreRepository;  // Updated repository name
import com.example.book.repository.BookRepository;   // Updated repository name
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class SeedData implements CommandLineRunner {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final GenreRepository genreRepository; // Updated repository name
    private final BookRepository bookRepository;   // Updated repository name

    @Autowired
    public SeedData(@Lazy PasswordEncoder passwordEncoder,
                    UserRepository userRepository,
                    BookRepository bookRepository,        // Updated repository name
                    GenreRepository genreRepository) {     // Updated repository name

        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.genreRepository = genreRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        User user = new User();
        user.setUserName("betselot");
        user.setEmailAddress("betselot@gmail.com");
        user.setPassword(passwordEncoder.encode("betselot123"));
        userRepository.save(user);

        //First Genre
        Genre genre = new Genre();  // Updated entity name
        genre.setName("Fiction");
        genre.setDescription("Literature in the form of prose that describes imaginary events and people");
        genre.setUser(user);
        genreRepository.save(genre);  // Updated repository name


        Book book1 = new Book();   // Updated entity name
        book1.setGenre(genre);     // Updated attribute name
        book1.setTitle("TheKite Runner");
        book1.setPages(371L);
        book1.setAuthor("Khaled Hosseini");
        book1.setAvailable(true);
        book1.setUser(user);
        bookRepository.save(book1);  // Updated repository name

        Book book2 = new Book();
        book2.setGenre(genre);
        book2.setTitle("To Kill a Mockingbird");
        book2.setPages(281L);
        book2.setAuthor("Harper Lee");
        book2.setAvailable(true);
        book2.setUser(user);
        bookRepository.save(book1);
        // Save the new book to the repository

    }
}
