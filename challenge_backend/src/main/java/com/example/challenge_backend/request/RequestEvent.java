package com.example.challenge_backend.request;

import com.example.challenge_backend.model.Subscription;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class RequestEvent {
    @JsonIgnore
    private Integer id;
    @JsonFormat
    private String type;
    @JsonIgnore
    private Subscription subscriptionFk;
}
