package com.example.challenge_backend.service;

import com.example.challenge_backend.model.Subscription;
import com.example.challenge_backend.repository.RepositoryEvents;
import com.example.challenge_backend.repository.RepositoryStatus;
import com.example.challenge_backend.repository.RepositoryUsers;
import com.example.challenge_backend.repository.RepositorySubscriptions;
import com.example.challenge_backend.response.ResponseEvents;
import com.example.challenge_backend.response.ResponseStatus;
import com.example.challenge_backend.response.ResponseSubscriptions;
import com.example.challenge_backend.response.ResponseUsers;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class Services {

    private final RepositoryUsers repositoryUsers;
    private final RepositorySubscriptions repositorySubscriptions;
    private final RepositoryStatus repositoryStatus;
    private final RepositoryEvents repositoryEvents;

//    public Response saveSubscription(Request request) {
//        Subscription subscription;
//        subscription = Subscription.of(request);
//        var subscriptionModel = Repository.save(subscription);
//        return Response.of(subscriptionModel);
//    }

    public List<ResponseUsers> findAllUsers() {
        return repositoryUsers
                .findAll()
                .stream()
                .map(ResponseUsers::of)
                .collect(Collectors.toList());
    }

    public List<ResponseSubscriptions> findAllSubscriptions() {
//        List<Subscription> subscriptions = repositorySubscriptions.findAll();
//        return subscriptions;
        return repositorySubscriptions
                .findAll()
                .stream()
                .map(ResponseSubscriptions::of)
                .collect(Collectors.toList());
    }

    public List<ResponseStatus> findAllStatus() {
        return repositoryStatus
                .findAll()
                .stream()
                .map(ResponseStatus::of)
                .collect(Collectors.toList());
    }

    public List<ResponseEvents> findAllEvents() {
        return repositoryEvents
                .findAll()
                .stream()
                .map(ResponseEvents::of)
                .collect(Collectors.toList());
    }


}
