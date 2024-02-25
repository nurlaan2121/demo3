package com.example.demo.service;


import com.example.demo.entities.User;

import java.util.List;

public interface UserInterface {

    void saveUser(User user);

    Long signIn(User user);
}
