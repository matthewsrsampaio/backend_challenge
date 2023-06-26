package com.example.challenge_backend.service;

import com.example.challenge_backend.model.User;
import com.example.challenge_backend.repository.RepositoryEvent;
import com.example.challenge_backend.repository.RepositoryStatus;
import com.example.challenge_backend.repository.RepositoryUser;
import com.example.challenge_backend.repository.RepositorySubscription;
import com.example.challenge_backend.request.Request;
import com.example.challenge_backend.response.ResponseEvent;
import com.example.challenge_backend.response.ResponseStatus;
import com.example.challenge_backend.response.ResponseSubscription;
import com.example.challenge_backend.response.ResponseUser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ChallengeService {

    private final RepositoryUser repositoryUser;
    private final RepositorySubscription repositorySubscription;
    private final RepositoryStatus repositoryStatus;
    private final RepositoryEvent repositoryEvent;

    public ResponseUser saveUser(Request request) {
        User user;
        user = User.of(request);
//        user.producemessage(user);
        var User = repositoryUser.save(user);
        return ResponseUser.of(user);
    }

    public List<ResponseUser> findAllUsers() {
        return repositoryUser
                .findAll()
                .stream()
                .map(ResponseUser::of)
                .collect(Collectors.toList());
    }

    public List<ResponseSubscription> findAllSubscriptions() {
//        List<Subscription> subscriptions = repositorySubscriptions.findAll();
//        return subscriptions;
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

}
