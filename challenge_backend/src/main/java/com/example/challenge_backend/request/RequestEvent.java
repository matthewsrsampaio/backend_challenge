package com.example.challenge_backend.request;

import com.example.challenge_backend.model.Subscription;
import lombok.Data;

@Data
public class RequestEvent {
    private Integer id;
    private String type;
    private Subscription subscriptionFk;
}
