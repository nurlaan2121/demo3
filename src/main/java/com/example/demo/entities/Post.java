package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "posts")
@Getter
@Setter
@SequenceGenerator(name = "id_gen", allocationSize = 1, sequenceName = "post_id_seq")
public class Post extends GeneratedId {
    private String title;
    private String description;
    @Column(name = "created_ad")
    private Date createdAd;
    @OneToMany(mappedBy = "post", cascade = {CascadeType.REMOVE,CascadeType.MERGE,CascadeType.REFRESH}, fetch = FetchType.EAGER)
    private List<Comment> comments = new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy = "post", cascade = {CascadeType.PERSIST, CascadeType.REMOVE,CascadeType.MERGE}, fetch = FetchType.EAGER)
    private List<Like> likes = new ArrayList<>();
    @OneToOne(cascade = {CascadeType.REMOVE, CascadeType.PERSIST,CascadeType.MERGE})
    private Image image;
    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    private User user;
}
