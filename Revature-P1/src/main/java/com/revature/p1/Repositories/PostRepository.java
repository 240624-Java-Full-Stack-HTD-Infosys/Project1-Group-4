package com.revature.p1.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.revature.p1.Models.*;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    // https://docs.spring.io/spring-data/jpa/docs/1.6.0.RELEASE/reference/html/jpa.repositories.html
    List<Post> findByUserId(int userId);
    Post findByPostId(int postId);
    void deleteByPostId(int postId);

}
