package com.revature.p1.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;
    private String content;
    private Integer shares;

    @ManyToOne
    @JoinColumn
    @JsonBackReference
    private User author;

    @OneToMany(mappedBy = "post")
    @JsonManagedReference
    private List<Comment> comments;

    @ManyToMany
    @JsonBackReference
    @JoinTable
    private List<User> usersWhoLikeThisPost;

    public Post() {
    }

    public Post(String content, Integer shares, User author) {
        this.content = content;
        this.shares = shares;
        this.author = author;
    }

    public Post(Integer postId, String content, Integer shares, User author, List<Comment> comments, List<User> usersWhoLikeThisPost) {
        this.postId = postId;
        this.content = content;
        this.shares = shares;
        this.author = author;
        this.comments = comments;
        this.usersWhoLikeThisPost = usersWhoLikeThisPost;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getShares() {
        return shares;
    }

    public void setShares(Integer shares) {
        this.shares = shares;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<User> getUsersWhoLikeThisPost() {
        return usersWhoLikeThisPost;
    }

    public void setUsersWhoLikeThisPost(List<User> usersWhoLikeThisPost) {
        this.usersWhoLikeThisPost = usersWhoLikeThisPost;
    }
}
