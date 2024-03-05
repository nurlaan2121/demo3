package com.example.demo.repo;

import com.example.demo.dtoes.UserDto;
import com.example.demo.entities.Follower;
import com.example.demo.entities.Post;
import com.example.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    @Query("select u.id from User u where u.userName = :userName and u.password = :password")
    Long signIn(String userName, String password);

    @Query("select u from User u")
    List<UserDto> getAllUsers();

    @Query("select u from User u join Follower f on u.follower.id  = f.id where u.email ilike (:key) or u.userName ilike (:key) or u.userInfo.fullName ilike (:key)")
    List<User> search(String key);

    @Query("select p from Post p join User u on p.user.id = u.id where u.id = :userId")
    List<Post> getAllPostFindUser(Long userId);
    @Query("select f from  Follower f join User u on f.id = u.follower.id where u.id = :userId")
    Follower getFollower(Long userId);
    @Query("select u from User u where u.id = :userId")
    User getUser(Long userId);


}
