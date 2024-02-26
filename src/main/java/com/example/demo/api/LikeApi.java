package com.example.demo.api;

import com.example.demo.service.PostInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping
public class LikeApi {
    private final PostInterface postInterface;
    @GetMapping("/like/{postId}/{currentUserId}")
    public String likePost(@PathVariable("postId") Long postId, @PathVariable("currentUserId") Long currentUserId) {
        postInterface.likePost(postId, currentUserId);
        return "redirect:/search/" + currentUserId;
    }
}
