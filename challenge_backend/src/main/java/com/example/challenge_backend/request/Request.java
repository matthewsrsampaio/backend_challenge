package com.example.challenge_backend.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Request {
    private Integer id;
    private String status;
    private String name;
//    private LocalDateTime createdAt;
}
