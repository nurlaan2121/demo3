package com.example.demo.service;

import com.example.demo.entities.Follower;
import com.example.demo.entities.User;
import com.example.demo.entities.UserInfo;
import com.example.demo.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service()
@RequiredArgsConstructor
public class UserImpl implements UserInterface {
    private final UserRepo userRepo;

    @Override
    public void saveUser(User user) {
        Follower follower = new Follower();
        UserInfo userInfo = new UserInfo();
        user.setUserInfo(userInfo);
        user.setFollower(follower);
        userRepo.save(user);
    }

    @Override
    public Long signIn(User user) {
       return userRepo.signIn(user.getUserName(),user.getPassword());
    }
}
