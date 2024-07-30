package com.revature.p1.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Entity(name = "users")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "userId")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    private String fname;
    private String lname;
    private String email;
    private String bio;

    @Column(unique = true, nullable = false)
    @Length(min = 6, max = 20)
    @Pattern(regexp = "^[A-Za-z0-9]{6,20}$")
    private String username;

    private String password;

    // users writing posts & comments
    @OneToMany(mappedBy = "author")
    @JsonManagedReference("authorOfPosts")
    private List<Post> posts;

    @OneToMany(mappedBy = "author")
    @JsonManagedReference("authorOfComments")
    private List<Comment> comments;

    // users liking posts & comments
    @ManyToMany(mappedBy = "usersWhoLikeThisPost")
    private List<Post> likedPosts;

    @ManyToMany(mappedBy = "usersWhoLikeThisComment")
    private List<Comment> likedComments;

    // users following users
    @Column(name = "user_following")
    @ManyToMany
    @JsonBackReference("back")
    @JoinTable(
            name = "follower_junction",
            joinColumns = @JoinColumn(name = "follower_id"),
            inverseJoinColumns = @JoinColumn(name = "following_id")
    )
    private List<User> following; // our user following many other users

    public User() {
    }

    public User(String fname, String lname, String email, String bio, String username, String password) {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.bio = bio;
        this.username = username;
        this.password = password;
    }

    public User(Integer userId, String fname, String lname, String email, String bio, String username, String password, List<Post> posts, List<Comment> comments, List<Post> likedPosts, List<Comment> likedComments, List<User> following) {
        this.userId = userId;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.bio = bio;
        this.username = username;
        this.password = password;
        this.posts = posts;
        this.comments = comments;
        this.likedPosts = likedPosts;
        this.likedComments = likedComments;
        this.following = following;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public @Length(min = 6, max = 20) @Pattern(regexp = "^[A-Za-z0-9]{6,20}$") String getUsername() {
        return username;
    }

    public void setUsername(@Length(min = 6, max = 20) @Pattern(regexp = "^[A-Za-z0-9]{6,20}$") String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Post> getLikedPosts() {
        return likedPosts;
    }

    public void setLikedPosts(List<Post> likedPosts) {
        this.likedPosts = likedPosts;
    }

    public List<Comment> getLikedComments() {
        return likedComments;
    }

    public void setLikedComments(List<Comment> likedComments) {
        this.likedComments = likedComments;
    }

    public List<User> getFollowing() {
        return following;
    }

    public void setFollowing(List<User> following) {
        this.following = following;
    }
}
