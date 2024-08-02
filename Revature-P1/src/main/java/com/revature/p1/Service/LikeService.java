package com.revature.p1.Service;


import com.revature.p1.Models.*;
import com.revature.p1.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
public class LikeService {
    private final UserRepository userRepo;
    private final PostRepository postRepo;
//    private final CommentRepository commentRepo;

    @Autowired
    public LikeService(UserRepository userRepo, PostRepository postRepo) {
        this.userRepo = userRepo;
        this.postRepo = postRepo;
//        this.commentRepo = commentRepo;
    }

    // createComment like for a post
    public void likePost(Integer userId, Integer postId) {
        User user = userRepo.findUserByUserId(userId);
        Post post = postRepo.findPostByPostId(postId);
        if (user == null || post == null)
            return;

        user.getLikedPosts().add(post);
        post.getUsersWhoLikeThisPost().add(user);
        userRepo.save(user);
        postRepo.save(post);
    }

    public void unlikePost(Integer userId, Integer postId) {
        User user = userRepo.findUserByUserId(userId);
        Post post = postRepo.findPostByPostId(postId);
        if (user == null || post == null)
            return;

        user.getLikedPosts().remove(post);
        post.getUsersWhoLikeThisPost().remove(user);
        userRepo.save(user);
        postRepo.save(post);
    }

    // get liked posts
    public List<Post> getPostsLikedByUser(Integer userId) {
        User user = userRepo.findUserByUserId(userId);
        return user.getLikedPosts();
    }

    // get post likes (usersWhoLikeThisPost)
    public List<User> getPostLikes(Integer postId) {
        Post post = postRepo.findPostByPostId(postId);
        return post.getUsersWhoLikeThisPost();
    }
}
