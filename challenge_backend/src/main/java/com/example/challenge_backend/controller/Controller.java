package com.example.challenge_backend.controller;

import com.example.challenge_backend.request.Request;
import com.example.challenge_backend.request.RequestEvent;
import com.example.challenge_backend.request.RequestStatus;
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

    @PostMapping("subscriptionUser")
    public ResponseUser save(@RequestBody Request request) {
        return challengeService.saveUser(request);
    }

    @PostMapping("subscriptionEvent")
    public ResponseEvent save(@RequestBody RequestEvent requestEvent) {
        return challengeService.saveEvent(requestEvent);
    }

    @PostMapping("subscriptionStatus")
    public ResponseStatus save(@RequestBody RequestStatus requestStatus) {
        return challengeService.saveStatus(requestStatus);
    }

    @GetMapping("all_users")
    public List<ResponseUser> findAllUsers() {
        return challengeService.findAllUsers();
    }

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
