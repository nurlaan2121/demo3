package com.example.demo.service;

import com.example.demo.dtoes.CommentDto;
import com.example.demo.dtoes.PostDto;
import com.example.demo.entities.Comment;
import com.example.demo.entities.Like;
import com.example.demo.entities.Post;
import com.example.demo.entities.User;
import com.example.demo.repo.CommentRepo;
import com.example.demo.repo.LikeRepo;
import com.example.demo.repo.PostRepo;
import com.example.demo.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class PostImpl implements PostInterface {
    private final PostRepo postRepo;
    private final UserRepo userRepo;
    private final LikeRepo likeRepo;
    private final CommentRepo commentRepo;

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

    @Override
    public List<PostDto> getAllPosts() {
        return postRepo.getAllPosts();
    }

    @Override
    public List<PostDto> search2(String keyword) {
        return postRepo.search("%" + keyword + "%");
    }

    @Override
    public void likePost(Long postId, Long currentUserId) {
        boolean isLike = false;
        Post post = postRepo.findById(postId).get();
        for (int i = 0; i < post.getLikes().size(); i++) {
            if (post.getLikes().get(i).getUser().getId().equals(currentUserId)) {
                likeRepo.delete(post.getLikes().get(i));
                post.getLikes().remove(i);
                isLike = true;
                break;
            }
        }
        if (!isLike) {
            User user = userRepo.findById(currentUserId).get();
            Like like = new Like();
            like.setPost(post);
            like.setUser(user);
            like.setIsLike(true);
            post.getLikes().add(like);
            likeRepo.save(like);
        }
    }

    @Override
    public void addComment(Long postId, Long currentUserId, String comment) {
        User user = userRepo.findById(currentUserId).get();
        Post post = postRepo.findById(postId).get();
        Comment newComment = new Comment();
        newComment.setUser(user);
        newComment.setPost(post);
        newComment.setComment(comment);
        newComment.setCreatedAd(Date.valueOf(LocalDate.now()));
        post.getComments().add(newComment);
        commentRepo.save(newComment);
    }

    @Override
    public List<CommentDto> infoAboutCurrentPost(Long postId) {
        List<CommentDto> commentDTOList = commentRepo.infoAboutCurrentPost(postId);
        for (int i = 0; i < commentDTOList.size(); i++) {
            Long idComment = commentDTOList.get(i).getId();
            Comment comment1 = commentRepo.findById(idComment).get();
            commentDTOList.get(i).setLikesCount((long) comment1.getLikes().size());
        }
        return commentDTOList;
    }

    @Override
    public List<Post> getAllMyPosts(Long idCurrentUser) {
        return postRepo.getMyPosts(idCurrentUser);
    }
}
