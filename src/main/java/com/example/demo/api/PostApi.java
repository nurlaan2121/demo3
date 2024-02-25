package com.example.demo.api;

import com.example.demo.entities.Image;
import com.example.demo.entities.Post;
import com.example.demo.service.PostImpl;
import com.example.demo.service.PostInterface;
import com.example.demo.service.UserInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
@Controller
@RequiredArgsConstructor
@RequestMapping()
public class PostApi {
    private final PostInterface postInterface;

    @GetMapping("createPost/{idCurrentUser}")
    public String createPost(@PathVariable("idCurrentUser") Long idCurrentUser, Model modelPost, Model model) {
        Post post = new Post();
        Image image = new Image();
        post.setImage(image);
        modelPost.addAttribute("newPost", post);
        model.addAttribute("currentUserId", idCurrentUser);
        return "posts/createPost-page";
    }


    @PostMapping("save/post/{currentUserId}")
    public String save(@PathVariable Long currentUserId, @ModelAttribute Post post) {
        postInterface.savePost(currentUserId,post);
        return "redirect:/home/" + currentUserId;
    }
}
