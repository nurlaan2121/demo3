package com.example.demo.repo;

import com.example.demo.dtoes.PostDto;
import com.example.demo.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepo extends JpaRepository<Post, Long> {
    @Query("SELECT NEW com.example.demo.dtoes.PostDto(" +
            "  p.id, p.title, p.description, COUNT(l), COUNT(c), p.user.id, p.user.userName, i.imageUrl, p.createdAd) " +
            "FROM Post p " +
            "LEFT JOIN Like l ON l.post.id = p.id " +
            "LEFT JOIN Comment c ON c.post.id = p.id " +
            "LEFT JOIN Image i ON  i.id = p.image.id join User u on p.user.id = u.id where u.id = :idCurrentUser " +
            "GROUP BY p.id, p.title, p.description, p.user.id, p.user.userName, i.imageUrl, p.createdAd")
    List<PostDto> getHomePosts(Long idCurrentUser);

}
