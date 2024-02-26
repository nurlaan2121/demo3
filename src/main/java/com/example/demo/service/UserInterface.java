package com.example.demo.service;


import com.example.demo.dtoes.UserDto;
import com.example.demo.entities.Post;
import com.example.demo.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public interface UserInterface {

    void saveUser(User user);

    Long signIn(User user);

    List<User> getAllUsers();

    List<User> search(String keyword);

    void subscribe(Long chooseUserId, Long currentUserId);

    User profile(Long userId);

    List<Post> getAllPostFindUser(Long userId);

    UserDto getMyInfo(Long id);

}
