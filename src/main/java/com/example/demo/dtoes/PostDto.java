package com.example.demo.dtoes;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class PostDto {
    private Long id;
    private String title;
    private String description;
    private Long likes;
    private Long comments;
    private Long ownerId;
    private String ownerUserName;
    private String imageUrl;
    private Date createdAd;

    public PostDto(Long id, String title, String description, Long ownerId,String ownerUserName, Date createdAd) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.ownerId = ownerId;
        this.ownerUserName = ownerUserName;
        this.createdAd = createdAd;
    }
}
