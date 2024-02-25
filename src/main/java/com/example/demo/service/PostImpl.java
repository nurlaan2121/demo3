package com.example.demo.service;

import com.example.demo.dtoes.PostDto;
import com.example.demo.entities.Post;
import com.example.demo.entities.User;
import com.example.demo.repo.PostRepo;
import com.example.demo.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
@Repository
@RequiredArgsConstructor
public class PostImpl implements PostInterface{
    private final PostRepo postRepo;
    private final UserRepo userRepo;
    @Override
    public List<PostDto> getHomePosts(Long idCurrentUser) {
        return postRepo.getHomePosts(idCurrentUser);
    }

    @Override
    public void savePost(Long currentUserId, Post post) {
        User user = userRepo.findById(currentUserId).get();
        post.setUser(user);
        user.getPosts().add(post);
        post.setCreatedAd(Date.valueOf(LocalDate.now()));
        postRepo.save(post);
    }
}
