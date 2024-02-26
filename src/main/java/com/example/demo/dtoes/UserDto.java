package com.example.demo.dtoes;

import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String userName;
    private String fullName;
    private String profileLink;
    private Long subscribers;
    private Long subscriptions;
}
