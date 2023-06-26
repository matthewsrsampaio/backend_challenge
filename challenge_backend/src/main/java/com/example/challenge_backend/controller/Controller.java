package com.example.challenge_backend.controller;

import com.example.challenge_backend.model.Subscription;
import com.example.challenge_backend.response.ResponseEvents;
import com.example.challenge_backend.response.ResponseStatus;
import com.example.challenge_backend.response.ResponseSubscriptions;
import com.example.challenge_backend.response.ResponseUsers;
import com.example.challenge_backend.service.Services;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/subscription")
public class Controller {
//
    private final Services services;

//    @PostMapping
//    public Response save(@RequestBody Request request) {
//        return services.saveSubscription(request);
//    }

    @GetMapping("all_users")
    public List<ResponseUsers> findAllUsers() {
        return services.findAllUsers();
    }

//    @GetMapping("all_subscriptions")
//    public List<Subscription> findAllSubscriptions() {
//        return services.findAllSubscriptions();
//    }

    @GetMapping("all_subscriptions")
    public List<ResponseSubscriptions> findAllSubscriptions() {
        return services.findAllSubscriptions();
    }

    @GetMapping("all_status")
    public List<ResponseStatus> findAllStatus() {
        return services.findAllStatus();
    }

    @GetMapping("all_events")
    public List<ResponseEvents> findAllEvents() {
        return services.findAllEvents();
    }

}
