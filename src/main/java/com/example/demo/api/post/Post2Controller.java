package com.example.demo.api.post;
import com.example.demo.dtoes.PostDto;
import com.example.demo.entities.Post;
import com.example.demo.service.PostInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping()
@RequiredArgsConstructor

public class Post2Controller {
    @Autowired
    private final PostInterface postInterface;

    @GetMapping("/getPosts")
    @ResponseBody
    public List<PostDto> getPosts() {
        return postInterface.getAllPosts();
    }

    @GetMapping("/getLikedPosts")
    @ResponseBody
    public List<Post> getLikedPosts() {
//        return postInterface.getLikedPost();
        return Collections.emptyList();
    }

    @PostMapping("/like/{id}")
    public String like(@PathVariable Long id) {
//        postInterface.likePost(id);
        return "redirect:/search";
    }

    @PostMapping("/comment/{id}")
    public String comment(@PathVariable Long id, @RequestBody Map<String, String> requestBody) {
        String commentText = requestBody.get("commentText");
//        postInterface.addComment(id, commentText);
        return "redirect:/search";
    }
}
