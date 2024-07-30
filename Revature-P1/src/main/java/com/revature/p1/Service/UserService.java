package com.revature.p1.Service;


import at.favre.lib.crypto.bcrypt.BCrypt;
import com.revature.p1.Models.User;
import com.revature.p1.Repositories.PostRepository;
import com.revature.p1.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
public class UserService {
    private final UserRepository userRepo;
    private final PostRepository postRepo;

    @Autowired
    public UserService(UserRepository userRepo, PostRepository postRepo) {
        this.userRepo = userRepo;
        this.postRepo = postRepo;
    }


    //CRUD
    //TODO : login
    public User createUser(User user) {
        user.setPassword(BCrypt.withDefaults().hashToString(12, user.getPassword().toCharArray()));
        return userRepo.save(user);
    }

//    public Optional<User> getUser(Integer userID) {
//        return userRepo.findById(userID);
//    }

    public User getUser(String username) {
        return userRepo.findUserByUsername(username);
    }

    public User updateUser(Integer userId) {
        return userRepo.updateUser(userId);
    }

    public User getUser(Integer userId) {
        return userRepo.findUserByUserId(userId);
    }

    public void deleteUser(Integer userId) {
        userRepo.delUser(userId);
    }

    // get followers
    public List<User> getFollowers(Integer userId) {
        return userRepo.findFollowersByUserId(userId);
    }

    // get following
    public List<User> getFollowing(Integer userId) {
        User user = userRepo.findUserByUserId(userId);
        return user.getFollowing();
    }

    // create follow
    public void followUser(Integer followerId, Integer followingId) {
        User follower = userRepo.findUserByUserId(followerId);
        User following = userRepo.findUserByUserId(followingId);
        if (follower != null && following != null) {
            follower.getFollowing().add(following);
            userRepo.save(follower);
        }
    }

    // delete follow
    public void unfollowUser(Integer followerId, Integer followingId) {
        User follower = userRepo.findUserByUserId(followerId);
        User following = userRepo.findUserByUserId(followingId);
        if (follower != null && following != null) {
            follower.getFollowing().remove(following);
            userRepo.save(follower);
        }
    }

}
