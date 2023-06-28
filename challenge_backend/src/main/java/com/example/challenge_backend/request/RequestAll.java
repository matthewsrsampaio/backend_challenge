package com.example.challenge_backend.request;

import com.example.challenge_backend.model.Subscription;
import lombok.Data;

@Data
public class RequestAll {
//    private Integer idUser;
    private String name;
//    private Integer id;
    private String type;
//    private Subscription subscriptionFk;
//    private Integer idStatus;
    private String statusName;
}
