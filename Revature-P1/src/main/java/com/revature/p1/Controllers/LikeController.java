package com.revature.p1.Controllers;

import com.revature.p1.Models.*;
import com.revature.p1.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class LikeController {

    @Autowired
    private final LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @GetMapping("/users/{userId}/likedPosts")
    public List<Post> getUsersLikedPosts(@PathVariable Integer userId) {
        return likeService.getPostsLikedByUser(userId);
    }

    @GetMapping("/posts/{postId}/likes")
    public List<User> getUserLikesForPost(@PathVariable Integer postId) {
        return likeService.getPostLikes(postId);
    }

    @PostMapping("/posts/{postId}/{userId}")
    public void likePost(@PathVariable Integer postId, @PathVariable Integer userId){
        likeService.likePost(userId, postId);
    }

    @DeleteMapping("/posts/{postId}/{userId}")
    public void unlikePost(@PathVariable Integer postId, @PathVariable Integer userId){
        likeService.unlikePost(userId, postId);
    }

}