package com.example.demo.repo;

import com.example.demo.entities.Follower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowerRepo extends JpaRepository<Follower, Long> {
    @Query("select f FROM Follower f join User u on u.follower.id = f.id where u.id =:currentUserId")
    Follower findd(Long currentUserId);
}
