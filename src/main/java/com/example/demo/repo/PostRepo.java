package com.example.demo.repo;

import com.example.demo.dtoes.PostDto;
import com.example.demo.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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

    @Query("SELECT NEW com.example.demo.dtoes.PostDto(" +
            "  p.id, p.title, p.description, COUNT(l), COUNT(c), p.user.id, p.user.userName, i.imageUrl, p.createdAd) " +
            "FROM Post p " +
            "LEFT JOIN Like l ON l.post.id = p.id " +
            "LEFT JOIN Comment c ON c.post.id = p.id " +
            "LEFT JOIN Image i ON  i.id = p.image.id join User u on p.user.id = u.id " +
            "GROUP BY p.id, p.title, p.description, p.user.id, p.user.userName, i.imageUrl, p.createdAd")
    List<PostDto> getAllPosts();

    @Query("SELECT NEW com.example.demo.dtoes.PostDto(" +
            "  p.id, p.title, p.description, COUNT(distinct (l)), COUNT(c), p.user.id, p.user.userName, i.imageUrl, p.createdAd) " +
            "FROM Post p " +
            "LEFT JOIN Like l ON l.post.id = p.id " +
            "LEFT JOIN Comment c ON c.post.id = p.id " +
            "LEFT JOIN Image i ON  i.id = p.image.id join User u on p.user.id = u.id where p.title ilike (:keyword) or p.description ilike (:keyword)" +
            "GROUP BY p.id, p.title, p.description, p.user.id, p.user.userName, i.imageUrl, p.createdAd")
    List<PostDto> search(String keyword);


    @Query("select p from Post p join User  u on p.user.id = u.id where u.id = :idCurrentUser")
    List<Post> getMyPosts(Long idCurrentUser);
    @Modifying
    @Query("delete from Comment c where c.post.id = :postId")
    void deleteCom(Long postId);
    @Modifying
    @Query("delete from Like l where l.post.id = :postId")
    void deleteLike(Long postId);
    @Modifying
    @Query("delete from Post p where p.id = :id")
    void deletePost(Long id);
}
