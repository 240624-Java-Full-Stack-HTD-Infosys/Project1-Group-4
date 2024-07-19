package com.revature.p1.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.revature.p1.Models.*;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    // https://docs.spring.io/spring-data/jpa/docs/1.6.0.RELEASE/reference/html/jpa.repositories.html
    User findByUsername(String username);

    //TODO :login
    //use repo.save
    //@Query("INSERT INTO users (fname, lname, email, bio, username, pwd) VALUES (?1, ?2, ?3, ?4, ?5, ?6)")
    //User registration(User user);

//    @Query("SELECT * FROM users WHERE user_id = ?1")
//    User findUserByUserId(Integer userId);

//    @Query("SELECT * FROM users WHERE username = ?1")
//    User findUserByUsername(String username);

    //use repo.save
    //@Query("UPDATE users SET fname = ?1 , lname = ?2 , email = ?3, bio = ?4 , username = ?5 , password = ?6")
    //void updateUser(User user);

    @Query("DELETE FROM users WHERE user_id = ?1")
    void delUser(Integer userId);

}
