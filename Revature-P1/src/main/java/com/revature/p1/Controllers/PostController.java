package com.revature.p1.Controllers;

import com.revature.p1.Models.Post;
import com.revature.p1.Models.User;
import com.revature.p1.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @CrossOrigin
    @PostMapping("/post")
    @ResponseStatus(HttpStatus.OK)
    public Post createPost(@RequestBody Post post){
        return postService.createPost(post);
    }

    @GetMapping("/post/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public Post getPostByPostId(@PathVariable Integer postId){
        return postService.getPostByPostId(postId);
    }

    @GetMapping("/post/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<Post> getUsersPosts(@PathVariable Integer userId){
        return postService.getPostsByUser(userId);
    }

    @PutMapping("/post")
    @ResponseStatus(HttpStatus.OK)
    public Post editPost(@PathVariable int postId, @RequestBody Post post){
        return postService.editPost(postId, post);
    }

    @DeleteMapping("/post/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public void deletePost(@PathVariable int postId){
        postService.deletePost(postId);
    }

    @PostMapping("/post/{postId}/share")
    @ResponseStatus(HttpStatus.OK)
    public Post sharePost(@PathVariable int postId, @RequestBody User user){
        return postService.sharePost(postId, user.getUserId());
    }
}
