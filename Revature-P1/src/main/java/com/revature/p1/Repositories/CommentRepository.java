package com.revature.p1.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.revature.p1.Models.*;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    // https://docs.spring.io/spring-data/jpa/docs/1.6.0.RELEASE/reference/html/jpa.repositories.html


    @Transactional
    @Query(value = "INSERT INTO comments (content, user_id, post_id, timestamp) VALUES (?1, ?2, ?3, ?4)", nativeQuery = true)
    void create(String content, Integer userId, Integer postId, Timestamp time);

    @Modifying
    @Query(value = "UPDATE comments SET content = ?1, timestamp = ?2 WHERE comment_id = ?3", nativeQuery = true)
    void update(String content, Timestamp time, Integer commentId);

    @Query(value = "SELECT * FROM comments WHERE comment_id = ?1", nativeQuery = true)
    Comment get(Integer commentId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM comments WHERE comment_id = ?1", nativeQuery = true)
    void delete(Integer commentId);

    List<Comment> findCommentByPostPostId(Integer postId);

}
