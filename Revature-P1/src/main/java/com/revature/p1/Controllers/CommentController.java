package com.revature.p1.Controllers;

import com.revature.p1.Models.Comment;
import com.revature.p1.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/comment")
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@RequestBody Comment cmt) {
        return "Good";
    }

    @GetMapping("/comment/{comment_id}")
    @ResponseStatus(HttpStatus.OK)
    public Comment view(@PathVariable String id) {
        return commentService.get(Integer.valueOf(id));
    }
}
