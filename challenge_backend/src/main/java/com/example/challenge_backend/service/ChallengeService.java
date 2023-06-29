package com.example.challenge_backend.service;

import com.example.challenge_backend.model.Event;
import com.example.challenge_backend.model.Status;
import com.example.challenge_backend.model.Subscription;
import com.example.challenge_backend.model.User;
import com.example.challenge_backend.repository.RepositoryEvent;
import com.example.challenge_backend.repository.RepositoryStatus;
import com.example.challenge_backend.repository.RepositorySubscription;
import com.example.challenge_backend.repository.RepositoryUser;
import com.example.challenge_backend.request.RequestAll;
import com.example.challenge_backend.response.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ChallengeService {

    private final RepositoryUser repositoryUser;
    private final RepositorySubscription repositorySubscription;
    private final RepositoryStatus repositoryStatus;
    private final RepositoryEvent repositoryEvent;

    public List<ResponseUser> findAllUsers() {
        return repositoryUser
                .findAll()
                .stream()
                .map(ResponseUser::of)
                .collect(Collectors.toList());
    }

    public List<ResponseSubscription> findAllSubscriptions() {
        return repositorySubscription
                .findAll()
                .stream()
                .map(ResponseSubscription::of)
                .collect(Collectors.toList());
    }

    public List<ResponseUser> findByName(String name) {
        return repositoryUser
                .findByNameIgnoreCaseContaining(name)
                .stream()
                .map(ResponseUser::of)
                .collect(Collectors.toList());
    }

    public List<ResponseStatus> findAllStatus() {
        return repositoryStatus
                .findAll()
                .stream()
                .map(ResponseStatus::of)
                .collect(Collectors.toList());
    }

    public List<ResponseEvent> findAllEvents() {
        return repositoryEvent
                .findAll()
                .stream()
                .map(ResponseEvent::of)
                .collect(Collectors.toList());
    }

    public User findById(Integer id) {
        return repositoryUser
                .findById(id)
                .orElseThrow();
    }

    public ResponseUser findByIdResponse(Integer id) {
        return ResponseUser.of(findById(id));
    }

    public Event findEventById(Integer id) {
        return repositoryEvent
                .findById(id)
                .orElseThrow();
    }

    public Status findStatusById(Integer id) {
        return repositoryStatus
                .findById(id)
                .orElseThrow();
    }

    public ResponseAll saveAll(RequestAll requestAll) {
        User user = new User();
        user.setName(requestAll.getName());
        var User = repositoryUser.save(user);

        Status status = new Status();
        status.setStatus("ACTIVATED");
        var Status = repositoryStatus.save(status);

        Subscription subscription = new Subscription();
        subscription.setUserFk(User);
        subscription.setStatusFk(Status);
        var Subscription = repositorySubscription.save(subscription);

        Event event = new Event();
        event.setType("PURCHASE");
        event.setSubscriptionFk(Subscription);
        repositoryEvent.save(event);

        return ResponseAll.of(user, status, event);
    }

    public ResponseAll cancelSubscription(RequestAll requestAll, Integer id) {
        User user = new User();
        user.setId(findById(id).getId());
        user.setName(findById(id).getName());

        Status status;
        status = Status.of(requestAll);
        status.setId(findStatusById(id).getId());
        status.setStatus("DEACTIVATED");
        repositoryStatus.save(status);

        Subscription subscription = new Subscription();
        subscription.setIdSubscription(id);
        subscription.setStatusFk(status);
        subscription.setUserFk(user);
        subscription.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        var Subscription = repositorySubscription.save(subscription);

        Event event;
        event = Event.of(requestAll);
        event.setType("CANCELLED");
        event.setSubscriptionFk(Subscription);
        event.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        repositoryEvent.save(event);

        return ResponseAll.of(user, status, event);
    }

    public ResponseAll restartSubscription(RequestAll requestAll, Integer id) {
        User user = new User();
        user.setId(findById(id).getId());
        user.setName(findById(id).getName());

        Status status;
        status = Status.of(requestAll);
        status.setId(findStatusById(id).getId());
        status.setStatus("ACTIVATED");
        repositoryStatus.save(status);

        Subscription subscription = new Subscription();
        subscription.setIdSubscription(id);
        subscription.setStatusFk(status);
        subscription.setUserFk(user);
        subscription.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        var Subscription = repositorySubscription.save(subscription);

        Event event;
        event = Event.of(requestAll);
        event.setType("RESTART");
        event.setSubscriptionFk(Subscription);
        event.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        repositoryEvent.save(event);

        return ResponseAll.of(user, status, event);
    }

}
