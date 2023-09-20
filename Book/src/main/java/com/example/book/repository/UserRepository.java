package com.example.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.book.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // to register
    boolean existsByEmailAddress(String userEmailAddress);
    //to check if the user exist in the database if. if it exist true or else false;

    // to login
    User findUserByEmailAddress(String emailAddress);

}
