package com.example.demo.api;

import com.example.demo.entities.Post;
import com.example.demo.service.PostInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping()
public class PostApi {
    private final PostInterface postInterface;

    @GetMapping("createPost/{currentUserId}")
    public String createPost(@PathVariable Long currentUserId, Model modelPost, Model model) {
        modelPost.addAttribute("newPost", new Post());
        model.addAttribute("currentUserId", currentUserId);
        return "posts/createPost-page";
    }

    @PostMapping("save/post/{currentUserId}")
    public String save(@PathVariable Long currentUserId, @ModelAttribute Post post) {
        postInterface.savePost(currentUserId,post);
        return "redirect:/ret";
    }
}
