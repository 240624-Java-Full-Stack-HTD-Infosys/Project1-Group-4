package com.revature.p1.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.List;

// TODO: Use Lombok, refactor getters and setters

@Entity(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentId;

    @Column(name = "content")
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference("authorOfComments")
    private User author;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    @JsonBackReference("postComments")
    private Post post;

    @Column(name = "timestamp", nullable = false)
    Timestamp time;

    @ManyToMany
    private List<User> usersWhoLikeThisComment;

    public Comment() {
    }

    public Comment(String content, User author, Post post) {
        this.content = content;
        this.author = author;
        this.post = post;
    }

    public Comment(Integer commentId, String content, User author, Post post, List<User> usersWhoLikeThisComment) {
        this.commentId = commentId;
        this.content = content;
        this.author = author;
        this.post = post;
//        this.time = ; // TODO:
        this.usersWhoLikeThisComment = usersWhoLikeThisComment;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public List<User> getUsersWhoLikeThisComment() {
        return usersWhoLikeThisComment;
    }

    public void setUsersWhoLikeThisComment(List<User> usersWhoLikeThisComment) {
        this.usersWhoLikeThisComment = usersWhoLikeThisComment;
    }
}
