package com.example.demo.api;

import com.example.demo.dtoes.CommentDto;
import com.example.demo.dtoes.PostDto;
import com.example.demo.entities.Image;
import com.example.demo.entities.Post;
import com.example.demo.service.PostImpl;
import com.example.demo.service.PostInterface;
import com.example.demo.service.UserInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("/search/{currentUserId}")
    public String search(Model model,@PathVariable("currentUserId") Long currentUserId,Model model2){
        List<PostDto> posts = postInterface.getAllPosts();
        model.addAttribute("posts",posts);
        model2.addAttribute("currentUserId",currentUserId);
        return "posts/search-page";
    }
    @GetMapping("/search2/{currentUserId}")
    String searchPosts(@RequestParam String keyword, Model model,@PathVariable("currentUserId") Long currentUserId,Model model2){
        List<PostDto> searchUsers = postInterface.search2(keyword);
        model.addAttribute("searchPosts", searchUsers);
        model2.addAttribute("currentUserId",currentUserId);
        return "posts/search-page";
    }
    @GetMapping("/info/{postId}/{currentUserId}")
    public String info(@PathVariable Long postId,@PathVariable Long currentUserId,Model model,Model model2) {
        List<CommentDto> commentDTO = postInterface.infoAboutCurrentPost(postId);
        model2.addAttribute("currentUserId",currentUserId);
        model.addAttribute("infoPost",commentDTO);
        return "comments/infoComment-page";
    }
}
