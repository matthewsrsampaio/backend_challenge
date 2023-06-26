package com.example.challenge_backend.repository;

import com.example.challenge_backend.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorySubscriptions extends JpaRepository<Subscription, Integer> {

}
