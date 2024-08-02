package com.revature.p1.Repositories;

import com.revature.p1.Models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    // https://docs.spring.io/spring-data/jpa/docs/1.6.0.RELEASE/reference/html/jpa.repositories.html
    @Query(value = "SELECT * FROM post WHERE user_id = :userId", nativeQuery = true)
    List<Post> findPostByUserId(Integer userId);

    Post findPostByPostId(Integer postId);

    void deleteByPostId(Integer postId);

    @Query(value = "SELECT * FROM post", nativeQuery = true)
    List<Post> findAllPosts();
}
