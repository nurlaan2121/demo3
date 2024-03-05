package com.example.demo.api.post;

import com.example.demo.entities.Post;
import com.example.demo.service.PostInterface;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping("/create/post")
@RequiredArgsConstructor
public class PostController {
    @Autowired
    private final PostInterface postInterface;

//    @GetMapping()
//    public String mainPage2() {
//        return "create-post";
//    }
//
//    @GetMapping("/new")
//    public String createUser(Model model) {
//        Post post = new Post();
//        post.setImages(new ArrayList<>());
//        model.addAttribute("newPost", post);
//        return "create-post";
//    }
//
//    @PostMapping("/save")
//    public String save(@ModelAttribute("newPostt") Post post) {
//        postInterface.save(post);
//        return "redirect:/home";
//    }

    @GetMapping("/delete/{id}/{currentUserId}")
    public String delete(@PathVariable Long id,@PathVariable Long currentUserId) {
        System.out.println(id + " ++++++++++++++++");
        postInterface.deletePost(id);
        return "redirect:/myProfile/" + currentUserId;
    }

    @GetMapping("/update/{id}/{currentUserId}")
    public String update(Model model, @PathVariable Long id,Model model2,@PathVariable Long currentUserId) {
        Post oldPost = postInterface.getPost(id);
        model2.addAttribute("currentUserId",currentUserId);
        model.addAttribute("oldPost", oldPost);
        return "postUpdate-page";

    }

    @PostMapping("/update2/{currentUserId}")
    public String update2(@PathVariable Long currentUserId,@ModelAttribute("oldPost") Post post) {
        postInterface.update(post);
        return "redirect:/myProfile/" + currentUserId;
    }



}
