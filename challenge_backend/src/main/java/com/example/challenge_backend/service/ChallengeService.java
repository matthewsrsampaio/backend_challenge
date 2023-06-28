package com.example.challenge_backend.service;

import com.example.challenge_backend.model.Event;
import com.example.challenge_backend.model.Status;
import com.example.challenge_backend.model.Subscription;
import com.example.challenge_backend.model.User;
import com.example.challenge_backend.repository.RepositoryEvent;
import com.example.challenge_backend.repository.RepositoryStatus;
import com.example.challenge_backend.repository.RepositorySubscription;
import com.example.challenge_backend.repository.RepositoryUser;
import com.example.challenge_backend.request.Request;
import com.example.challenge_backend.request.RequestAll;
import com.example.challenge_backend.request.RequestEvent;
import com.example.challenge_backend.request.RequestStatus;
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

    public ResponseUser saveUser(RequestAll requestAll) {
        User user;
        user = User.of(requestAll);
//        user.producemessage(user);
        var User = repositoryUser.save(user);
        return ResponseUser.of(user);
    }

//    public ResponseEvent saveEvent(RequestEvent requestEvent) {
//        Event event;
//        event = Event.of(requestEvent);
////        event.producemessage(event);
//        var Event = repositoryEvent.save(event);
//        return ResponseEvent.of(event);
//    }

//    public ResponseStatus saveStatus(RequestStatus requestStatus) {
//        Status status;
//        status = Status.of(requestStatus);
////        status.producemessage(status);
//        var Status = repositoryStatus.save(status);
//        return ResponseStatus.of(status);
//    }

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
//
//    public Status findStatusById(Integer id) {
//        return repositoryStatus
//                .findById(id)
//                .orElseThrow();
//    }
//
//    public ResponseStatus findStatusByIdResponse(Integer id) {
//        return ResponseStatus.of(findStatusById(id));
//    }
//
    public Event findEventById(Integer id) {
        return repositoryEvent
                .findById(id)
                .orElseThrow();
    }
//
//    public ResponseEvent findEventByIdResponse(Integer id) {
//        return ResponseEvent.of(findEventById(id));
//    }

    public ResponseAll saveAll(RequestAll requestAll) {
        User user = new User();
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

        return ResponseAll.of(user, status, event);
    }

    public ResponseAll cancelSubscription(RequestAll requestAll, Integer id) {
        return ResponseAll.of(getUser(requestAll, id),
                getStatus(requestAll, "DEACTIVATED",id),
                getEvent(requestAll, "CANCELLATION", id));
    }

    public ResponseAll restartSubscription(RequestAll requestAll, Integer id) {
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
