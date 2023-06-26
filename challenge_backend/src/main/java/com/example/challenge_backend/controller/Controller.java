package com.example.challenge_backend.controller;

import com.example.challenge_backend.request.Request;
import com.example.challenge_backend.response.ResponseEvent;
import com.example.challenge_backend.response.ResponseStatus;
import com.example.challenge_backend.response.ResponseSubscription;
import com.example.challenge_backend.response.ResponseUser;
import com.example.challenge_backend.service.ChallengeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class Controller {
//
    private final ChallengeService challengeService;

    @PostMapping("subscription")
    public ResponseUser save(@RequestBody Request request) {
        return challengeService.saveUser(request);
    }

    @GetMapping("all_users")
    public List<ResponseUser> findAllUsers() {
        return challengeService.findAllUsers();
    }

//    @GetMapping("all_subscriptions")
//    public List<Subscription> findAllSubscriptions() {
//        return services.findAllSubscriptions();
//    }

    @GetMapping("all_subscriptions")
    public List<ResponseSubscription> findAllSubscriptions() {
        return challengeService.findAllSubscriptions();
    }

    @GetMapping("all_status")
    public List<ResponseStatus> findAllStatus() {
        return challengeService.findAllStatus();
    }

    @GetMapping("all_events")
    public List<ResponseEvent> findAllEvents() {
        return challengeService.findAllEvents();
    }

    @GetMapping("user/{id}")
    public ResponseUser findById(@PathVariable Integer id) {
        return challengeService.findByIdResponse(id);
    }
}
