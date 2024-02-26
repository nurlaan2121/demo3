package com.example.demo.api;

import com.example.demo.service.PostInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class CommentApi {
    private final PostInterface postInterface;
    @PostMapping("/comment/{postId}/{currentUserId}")
    public String addComment(@PathVariable Long postId,@PathVariable Long currentUserId, @RequestParam String comment) {
        postInterface.addComment(postId,currentUserId,comment);
        return "redirect:/search/" + currentUserId;
    }
}
