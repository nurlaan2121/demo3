package com.example.demo.service;

import com.example.demo.dtoes.CommentDto;
import com.example.demo.dtoes.PostDto;
import com.example.demo.entities.Post;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostInterface {

    List<PostDto> getHomePosts(Long idCurrentUser);

    void savePost(Long currentUserId, Post post);

    List<PostDto> getAllPosts();

    List<PostDto> search2(String keyword);

    void likePost(Long postId, Long currentUserId);

    void addComment(Long postId, Long currentUserId, String comment);

    List<CommentDto> infoAboutCurrentPost(Long postId);

    List<Post> getAllMyPosts(Long idCurrentUser);
}
