package com.revature.p1.Controllers;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.revature.p1.Exceptions.InvalidLogin;
import com.revature.p1.Models.User;
import com.revature.p1.Service.AuthDto;
import com.revature.p1.Service.JwtTokenService;
import com.revature.p1.Service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class UserController {

    private final UserService userService;
    private final JwtTokenService tokenService;
    private final AuthDto authDto;

    @Autowired
    public UserController(UserService userService, JwtTokenService tokenService, AuthDto authDto) {
        this.userService = userService;
        this.tokenService = tokenService;
        this.authDto = authDto;
    }

    @GetMapping("/user/{username}")
    public User userByUsername(@PathVariable String username){
        return userService.getUser(username);
    }

    @GetMapping("/user/{userId}")
    public User userById(@PathVariable Integer uid){
        return userService.getUser(uid);
    }

    @PutMapping("/user/{userId}")
    public User updateUser(@PathVariable Integer uid){
        return userService.updateUser(uid);
    }

    @DeleteMapping("/user/{userId}")
    public void delUser(@PathVariable Integer uid){
        userService.deleteUser(uid);
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerNewUser(@RequestBody User user, HttpServletResponse httpServletResponse){
        if(userService.getUser(user.getUsername()) != null){
            throw new InvalidLogin("Username taken");
        }
        else{
            User temp = userService.createUser(user);
            Map<String, String> claimsMap = new HashMap<>();
            claimsMap.put("username", temp.getUsername());
            claimsMap.put("userID", String.valueOf(temp.getUserId()));
            Cookie cookie = new Cookie("Authentication", this.tokenService.generateToken(claimsMap));
            httpServletResponse.addCookie(cookie);
            try{
                return ResponseEntity.status(200).body("Successful Registration!");
            }
            catch (Exception e){
                return ResponseEntity.status(500).body("Generic server error.");
            }
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody User user, HttpServletResponse httpServletResponse){
        if(userService.getUser(user.getUsername()) == null){
            throw new InvalidLogin("Incorrect username");
        }
        else{
            User temp = userService.getUser(user.getUsername());
            String hashedPassword = this.userService.getUser(user.getUsername()).getPassword();
            BCrypt.Result result = BCrypt.verifyer().verify(user.getPassword().toCharArray(), hashedPassword);
            if(result.verified){
                Map<String, String> claimsMap = new HashMap<>();
                claimsMap.put("username", temp.getUsername());
                claimsMap.put("userID", String.valueOf(temp.getUserId()));
                Cookie cookie = new Cookie("Authentication", this.tokenService.generateToken(claimsMap));
                httpServletResponse.addCookie(cookie);
                try{
                    return ResponseEntity.status(200).body("Successful Login!");
                }
                catch (Exception e){
                    return ResponseEntity.status(500).body("Generic server error.");
                }
            }
            else{
                throw new InvalidLogin("Incorrect password");
            }
        }
    }

    // follower handlers
    @GetMapping("/user/{userId}/followers")
    public List<User> getUserFollowers(@PathVariable Integer uid){
        return userService.getFollowers(uid);
    }

    @GetMapping("/user/{userId}/following")
    public List<User> getUserFollowing(@PathVariable Integer uid){
        return userService.getFollowing(uid);
    }

    @PostMapping("/user/{userId}/following/{followingId}")
    public void followUser(@PathVariable Integer userId, @PathVariable Integer followingId){
        userService.followUser(userId, followingId);
    }

    @DeleteMapping("/user/{userId}/following/{followingId}")
    public void unfollowUser(@PathVariable Integer userId, @PathVariable Integer followingId){
        userService.unfollowUser(userId, followingId);
    }




}
