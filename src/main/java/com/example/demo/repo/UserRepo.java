package com.example.demo.repo;

import com.example.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
    @Query("select u.id from User u where u.userName = :userName and u.password = :password")
    Long signIn(String userName,String password);
}
