package com.revature.p1.Controllers;

import com.revature.p1.Models.Post;
import com.revature.p1.Models.User;
import com.revature.p1.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RestController
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/post")
    @ResponseStatus(HttpStatus.OK)
    public Post createPost(@RequestBody Post post){
        return postService.createPost(post);
    }

    @GetMapping("/posts")
    @ResponseStatus(HttpStatus.OK)
    public List<Post> getAllPosts(){
        return postService.getAllPosts();
    }

    @GetMapping("/post")
    @ResponseStatus(HttpStatus.OK)
    public Post getPostByPostId(@RequestParam(value = "postId") Integer postId){
        return postService.getPostByPostId(postId);
    }

    @GetMapping("/post/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<Post> getUsersPosts(@PathVariable("userId") Integer userId){
        return postService.getPostsByUser(userId);
    }

    @PutMapping("/post/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public Post editPost(@PathVariable("postId") int postId, @RequestBody Post post){
        return postService.editPost(postId, post);
    }

    @DeleteMapping("/post/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public void deletePost(@PathVariable("postId") int postId){
        postService.deletePost(postId);
    }

    @PostMapping("/post/{postId}/share")
    @ResponseStatus(HttpStatus.OK)
    public Post sharePost(@PathVariable("postId") int postId, @RequestBody User user){
        return postService.sharePost(postId, user.getUserId());
    }
}
