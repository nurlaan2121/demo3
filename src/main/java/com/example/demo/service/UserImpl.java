package com.example.demo.service;

import com.example.demo.dtoes.UserDto;
import com.example.demo.entities.Follower;
import com.example.demo.entities.Post;
import com.example.demo.entities.User;
import com.example.demo.entities.UserInfo;
import com.example.demo.repo.FollowerRepo;
import com.example.demo.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.atn.SemanticContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service()
@RequiredArgsConstructor
public class UserImpl implements UserInterface {
    private final UserRepo userRepo;
    private final FollowerRepo followerRepo;

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
        return userRepo.signIn(user.getUserName(), user.getPassword());
    }

    @Override
    public List<User> getAllUsers() {
        return Collections.emptyList();
    }

    @Override
    public List<User> search(String keyword) {
        return userRepo.search("%" + keyword + "%");
    }

    @Transactional
    @Override
    public void subscribe(Long chooseUserId, Long currentUserId) {
        User currentUser = userRepo.findById(currentUserId).orElseThrow();
        User subscriberUser = userRepo.findById(chooseUserId).orElseThrow();

        Follower currentUserFollower = currentUser.getFollower();
        Follower subscriberFollower = subscriberUser.getFollower();

        List<Long> currentUserSubscriptions = currentUserFollower.getSubscriptions();
        List<Long> subscriberSubscribers = subscriberFollower.getSubscribers();

        if (currentUserSubscriptions.contains(chooseUserId)) {
            currentUserSubscriptions.remove(chooseUserId);
            subscriberSubscribers.remove(currentUserId);
        } else {
            currentUserSubscriptions.add(chooseUserId);
            subscriberSubscribers.add(currentUserId);
        }
    }

    @Override
    public User profile(Long userId) {
        return userRepo.findById(userId).get();
    }

    @Override
    public List<Post> getAllPostFindUser(Long userId) {
        return userRepo.getAllPostFindUser(userId);
    }

    @Override
    public UserDto getMyInfo(Long id) {
        User user = userRepo.findById(id).get();
        UserDto userDto = new UserDto();
        userDto.setUserName(user.getUserName());
        userDto.setId(user.getId());
        userDto.setFullName(user.getUserInfo().getFullName());
        userDto.setProfileLink(user.getUserInfo().getImageProfile());
        userDto.setSubscribers((long) user.getFollower().getSubscribers().size());
        userDto.setSubscriptions((long) user.getFollower().getSubscriptions().size());
        return userDto;
    }

    @Override
    public User getCurrentUser(Long currentUserId) {
        return userRepo.findById(currentUserId).get();
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        User user1 = userRepo.findById(user.getId()).get();
        UserInfo userInfo = user1.getUserInfo();
        userInfo.setFullName(user.getUserInfo().getFullName());
        userInfo.setBiography(user.getUserInfo().getBiography());
        userInfo.setGender(user.getUserInfo().getGender());
        userInfo.setImageProfile(user.getUserInfo().getImageProfile());
        user1.setUserName(user.getUserName());
        user1.setImage(user.getImage());
        user1.setEmail(user.getEmail());
        user1.setPassword(user.getPassword());
        user1.setPhoneNumber(user.getPhoneNumber());
    }

    @Override
    public List<User> findUserGetAllSubscribers(Long userId) {
        List<User> users = new ArrayList<>();
        Follower follower = userRepo.getFollower(userId);
        for (int i = 0; i < follower.getSubscribers().size(); i++) {
            User userId1 = userRepo.getUser(follower.getSubscribers().get(i));
            users.add(userId1);
        }
        return users;
    }

    @Override
    public List<User> findUserGetAllSubscriptions(Long userId) {
        List<User> users = new ArrayList<>();
        Follower follower = userRepo.getFollower(userId);
        for (int i = 0; i < follower.getSubscriptions().size(); i++) {
            User userId1 = userRepo.getUser(follower.getSubscribers().get(i));
            users.add(userId1);
        }
        return users;
    }

    @Override
    public List<User> getMySubscription(Long currentUserId) {
        List<User> users = new ArrayList<>();
        Follower currentFollower = userRepo.getFollower(currentUserId);
        List<Long> subscriptions = currentFollower.getSubscriptions();
        for (int i = 0; i < subscriptions.size(); i++) {
            User fori = userRepo.getUser(subscriptions.get(i));
            users.add(fori);
        }
        return users;
    }


}
