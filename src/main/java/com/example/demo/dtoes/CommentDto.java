package com.example.demo.dtoes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
    private Long id;
    private Long userId;
    private String comment;
    private String userName;
    private Date createdAd;
    private Long likesCount;

    public CommentDto(Long id, Long userId, String comment, String userName, Date createdAd) {
        this.id = id;
        this.userId = userId;
        this.comment = comment;
        this.userName = userName;
        this.createdAd = createdAd;
    }
}
