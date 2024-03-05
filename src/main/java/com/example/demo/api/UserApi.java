package com.example.demo.api;
import com.example.demo.entities.Post;
import com.example.demo.entities.User;
import com.example.demo.service.UserInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class UserApi {
    private final UserInterface userInterface;

    @GetMapping("/searchUser/{currentUserId}")
    public String showPostsUser(@PathVariable Long currentUserId, Model model) {
        model.addAttribute("currentUserId", currentUserId);
        return "users/searchUser-page";
    }

    @GetMapping("/users/search/{currentUserId}")
    String searchUsers(@PathVariable Long currentUserId, @RequestParam String keyword, Model model, Model model2) {
        List<User> searchUsers = userInterface.search(keyword);
        model2.addAttribute("currentUserId", currentUserId);
        model.addAttribute("searchUsers", searchUsers);
        return "users/searchUser-page";
    }

    @GetMapping("/subscribe/{chooseUserId}/{currentUserId}")
    public String subscribe(@PathVariable Long chooseUserId, @PathVariable Long currentUserId) {
        userInterface.subscribe(chooseUserId, currentUserId);
        return "redirect:/searchUser/" + currentUserId;
    }

    @GetMapping("/profile/{userId}")
    public String profile(@PathVariable Long userId, Model model, Model postModel) {
        User user = userInterface.profile(userId);
        List<Post> posts = userInterface.getAllPostFindUser(userId);
        postModel.addAttribute("findUserPosts", posts);
        model.addAttribute("profileUser", user);
        return "viewProfile";
    }

}
