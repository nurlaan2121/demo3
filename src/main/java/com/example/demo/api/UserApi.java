package com.example.demo.api;

import com.example.demo.service.UserImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class UserApi {
    private final UserImpl userImpl;

//    @GetMapping
//    private String getAllUsers(Model model) {
//        model.addAttribute("users", userImpl.getAllUsers());
//        return "user/users";
//    }
    
}
