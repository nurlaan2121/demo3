package com.example.demo.service;

import com.example.demo.dtoes.PostDto;
import com.example.demo.entities.Post;
import com.example.demo.repo.PostRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
@RequiredArgsConstructor
public class PostImpl implements PostInterface{
    private final PostRepo postRepo;

    @Override
    public List<PostDto> getHomePosts(Long idCurrentUser) {
        return postRepo.getHomePosts(idCurrentUser);
    }

    @Override
    public void savePost(Long currentUserId, Post post) {
        postRepo.save(post);
    }
}
