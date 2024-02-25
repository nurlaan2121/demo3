package com.example.demo.service;

import com.example.demo.dtoes.PostDto;
import com.example.demo.entities.Post;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public interface PostInterface {

    List<PostDto> getHomePosts(Long idCurrentUser);

    void savePost(Long currentUserId, Post post);

}
