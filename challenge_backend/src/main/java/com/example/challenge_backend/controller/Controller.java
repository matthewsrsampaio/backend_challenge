package com.example.challenge_backend.controller;

import com.example.challenge_backend.request.Request;
import com.example.challenge_backend.request.RequestAll;
import com.example.challenge_backend.request.RequestEvent;
import com.example.challenge_backend.request.RequestStatus;
import com.example.challenge_backend.response.*;
import com.example.challenge_backend.response.ResponseStatus;
import com.example.challenge_backend.service.ChallengeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class Controller {

    private final ChallengeService challengeService;

    @GetMapping("subscription/name/{name}")
    public List<ResponseUser> findByName(@PathVariable String name) {
        return challengeService.findByName(name);
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

    @PostMapping("subscriptionAll")
    public ResponseAll saveAll(@RequestBody RequestAll request) {
        return challengeService.saveAll(request);
    }

    @PutMapping("subscription/cancel/{id}")
    public ResponseAll cancel(@RequestBody RequestAll requestAll, @PathVariable Integer id) {
        return challengeService.cancelSubscription(requestAll, id);
    }

    @PutMapping("subscription/restart/{id}")
    public ResponseAll restart(@RequestBody RequestAll requestAll, @PathVariable Integer id) {
        return challengeService.restartSubscription(requestAll, id);
    }
}
