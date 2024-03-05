package com.example.demo.repo;

import com.example.demo.dtoes.CommentDto;
import com.example.demo.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Long> {
    @Query("select new com.example.demo.dtoes.CommentDto(c.id,u.id,c.comment,c.user.userName,c.createdAd) from Comment c join User u on c.user.id = u.id  where c.post.id = :postId")
    List<CommentDto> infoAboutCurrentPost(Long postId);
    @Query("select c from Comment c join User u on c.user.id = u.id where c.id =:commentId")
    Comment getOneCom(Long commentId);

}
