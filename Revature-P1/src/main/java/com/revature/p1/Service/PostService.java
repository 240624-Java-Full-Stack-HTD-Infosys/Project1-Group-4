package com.revature.p1.Service;

import com.revature.p1.Exceptions.PostContentIsEmptyException;
import com.revature.p1.Exceptions.PostNotFoundException;
import com.revature.p1.Models.*;
import com.revature.p1.Repositories.PostRepository;
import com.revature.p1.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;

import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
public class PostService {
    private final PostRepository postRepo;
    private final UserRepository userRepo;
  
  @Autowired
  public PostService(PostRepository postRepository, UserRepository userRepository) {
    this.postRepo = postRepository;
    this.userRepo = userRepository;
  }

    public Post createPost(Post post) {
        if (post.getContent().isEmpty()) {
            System.out.println("There is no content.");
            throw new PostContentIsEmptyException("There is no content.");
        } else {
            post.setShares(0);
            return postRepo.save(post);
        }
    }

    public List<Post> getAllPosts(){
        return postRepo.findAllPosts();
    }

    public Post getPostByPostId(Integer postId){
        return postRepo.findPostByPostId(postId);
    }

    public List<Post> getPostsByUser(Integer userId){
        return postRepo.findPostByUserId(userId);
    }

    public Post editPost(Integer postId, Post post){
        Post currPost = postRepo.findPostByPostId(postId);
        if(currPost == null){
            System.out.println("Post doesn't exist.");
            throw new PostNotFoundException("Post doesn't exist.");
        }
        else{
            currPost.setContent(post.getContent());
            return postRepo.save(currPost);
        }
    }

    public void deletePost(Integer postId){
        Post currPost = postRepo.findPostByPostId(postId);
        if(currPost == null){
            System.out.println("Post doesn't exist.");
            throw new RuntimeException("Post doesn't exist.");
        }
        else{
            postRepo.deleteByPostId(postId);
        }
    }

    public Post sharePost(Integer postId, Integer userId){
        Post originalPost = postRepo.findPostByPostId(postId);
        if(originalPost == null){
            System.out.println("Original post does not exist.");
            throw new RuntimeException("Original post does not exist.");
        }

        User user = userRepo.findUserByUserId(userId);
        if (user == null) {
            System.out.println("User does not exist.");
            throw new RuntimeException("User does not exist.");
        }

        Post sharePost = new Post();

        sharePost.setContent(originalPost.getContent());
        sharePost.setAuthor(user);
        sharePost.setShares(0);

        originalPost.setShares(originalPost.getShares() + 1);

        postRepo.save(sharePost);
        postRepo.save(originalPost);

        return sharePost;
    }

    // get post comments
    public List<Comment> getCommentsByPost(Integer postId) {
        Post post = postRepo.findPostByPostId(postId);
        return post.getComments();
    }

}
