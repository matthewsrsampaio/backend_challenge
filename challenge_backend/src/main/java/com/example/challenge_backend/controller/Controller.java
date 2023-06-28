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

    @PostMapping("subscriptionAll")
    public ResponseAll saveAll(@RequestBody RequestAll request) {
        return challengeService.saveAll(request);
    }

    @PostMapping("subscriptionUser")
    public ResponseUser saveUser(@RequestBody RequestAll requestAll) {
        return challengeService.saveUser(requestAll);
    }

//    @PostMapping("subscriptionEvent")
//    public ResponseEvent saveEvent(@RequestBody RequestEvent requestEvent) {
//        return challengeService.saveEvent(requestEvent);
//    }

//    @PostMapping("subscriptionStatus")
//    public ResponseStatus saveStatus(@RequestBody RequestStatus requestStatus) {
//        return challengeService.saveStatus(requestStatus);
//    }

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

    @PutMapping("subscription/cancel/{id}")
    public ResponseAll cancel(@RequestBody RequestAll requestAll, @PathVariable Integer id) {
        return challengeService.cancelSubscription(requestAll, id);
    }

    @PutMapping("subscription/restart/{id}")
    public ResponseAll restart(@RequestBody RequestAll requestAll, @PathVariable Integer id) {
        return challengeService.restartSubscription(requestAll, id);
    }
}
