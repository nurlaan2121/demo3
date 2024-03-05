package com.example.demo.api.user;

import com.example.demo.entities.User;
import com.example.demo.service.UserInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping()
@RequiredArgsConstructor
public class SearchUserController {
    private final UserInterface userInterface;

    @GetMapping("/getFollows{currentUserId}")
    @ResponseBody
    public List<User> getFollows(@PathVariable Long currentUserId) {
        return userInterface.getMySubscription(currentUserId);
    }
    @GetMapping("/getAllUsers")
    @ResponseBody
    public List<User> getAllSUsers() {
        return userInterface.getAllUsers();
    }

}
