package com.example.demo.api;

import com.example.demo.dtoes.PostDto;
import com.example.demo.entities.User;
import com.example.demo.service.PostInterface;
import com.example.demo.service.UserInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping
@RequiredArgsConstructor
public class MainApi {
    private final UserInterface userInterface;
    private final PostInterface postInterface;

    @GetMapping()
    public String mainPage(Model deleteUser) {
        deleteUser.addAttribute("newUser", new User());
        return "main-page";
    }
    @GetMapping("/ret")
    public String mainReturn(Model deleteUser) {
        deleteUser.addAttribute("newUser", new User());
        return "main-page";
    }

    @GetMapping("/signIn")
    public String signIn(@ModelAttribute User user, Model currentUserId,Model homePosts) {
        Long idCurrentUser = userInterface.signIn(user);
        if (idCurrentUser != null) {
            List<PostDto> posts = postInterface.getHomePosts(idCurrentUser);
            homePosts.addAttribute("homePosts",posts);
            currentUserId.addAttribute("idCurrentUser", idCurrentUser);
            return "home-page";
        } else {
            return "error-page";
        }

    }

    @GetMapping("/signUp")
    public String signIn(Model newUser) {
        newUser.addAttribute("newUser", new User());
        return "signUp-page";
    }

    @PostMapping("/saveUser")
    private String saveUser(@ModelAttribute User user) {
        userInterface.saveUser(user);
        return "redirect:/";
    }
}
