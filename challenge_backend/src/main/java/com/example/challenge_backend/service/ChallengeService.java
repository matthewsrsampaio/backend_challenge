package com.example.challenge_backend.service;

import com.example.challenge_backend.model.Event;
import com.example.challenge_backend.model.Status;
import com.example.challenge_backend.model.Subscription;
import com.example.challenge_backend.model.User;
import com.example.challenge_backend.rabbitMQ.Producer;
import com.example.challenge_backend.repository.RepositoryEvent;
import com.example.challenge_backend.repository.RepositoryStatus;
import com.example.challenge_backend.repository.RepositorySubscription;
import com.example.challenge_backend.repository.RepositoryUser;
import com.example.challenge_backend.request.RequestAll;
import com.example.challenge_backend.response.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ChallengeService {

    private final RepositoryUser repositoryUser;
    private final RepositorySubscription repositorySubscription;
    private final RepositoryStatus repositoryStatus;
    private final RepositoryEvent repositoryEvent;
    private final Producer producer;

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

    public ResponseAll saveAll(RequestAll requestAll) {
        User user = new User();
        user.setIdUser(requestAll.getId());
        user.setName(requestAll.getName());
        var User = repositoryUser.save(user);
        Status status = new Status();
        status.setStatus(requestAll.getStatus());
        var Status = repositoryStatus.save(status);

        Subscription subscription = new Subscription();
        subscription.setUserFk(User);
        subscription.setStatusFk(Status);
        var Subscription = repositorySubscription.save(subscription);

        Event event = new Event();
        event.setType(requestAll.getType());
        event.setSubscriptionFk(Subscription);
        repositoryEvent.save(event);

        producer.produceUserMessage(user);

        return ResponseAll.of(user, status, event);
    }

    public ResponseAll cancelSubscription(RequestAll requestAll, Integer id) {
        producer.produceStatusMessage(getStatus(requestAll, "DEACTIVATED",id));
        producer.produceEventMessage(getEvent(requestAll, "CANCEL", id));
        return ResponseAll.of(getUser(requestAll, id),
                getStatus(requestAll, "DEACTIVATED",id),
                getEvent(requestAll, "CANCEL", id));
    }

    public ResponseAll restartSubscription(RequestAll requestAll, Integer id) {
        producer.produceUserMessage(getUser(requestAll, id));
        return ResponseAll.of(getUser(requestAll, id),
                getStatus(requestAll, "ACTIVATED",id),
                getEvent(requestAll, "RESTART", id));
    }

    public User getUser(RequestAll requestAll, Integer id) {
        User user;
        user = User.of(requestAll);
        user.setIdUser(id);
        user.setName(findById(id).getName());
        user.setCreatedAt(findById(id).getCreatedAt());
        return user;
    }

    public Status getStatus(RequestAll requestAll, String statusName, Integer id) {
        Status status;
        status = Status.of(requestAll);
        status.setIdStatus(id);
        status.setStatus(statusName);
        repositoryStatus.save(status);
        return status;
    }

    public Event getEvent(RequestAll requestAll, String eventName, Integer id) {
        Event event = new Event();
        event.setType(eventName);
        event.setSubscriptionFk(getSubscription(requestAll, id));
        repositoryEvent.save(event);
        return event;
    }

    public Subscription getSubscription(RequestAll requestAll, Integer id) {
        Subscription subscription = new Subscription();
        subscription.setIdSubscription(id);
        subscription.setStatusFk(getStatus(requestAll, "ACTIVATED", id));
        subscription.setUserFk(getUser(requestAll, id));
        subscription.setCreatedAt(findEventById(id).getCreatedAt());
        repositorySubscription.save(subscription);
        return subscription;
    }
}
