package com.example.challenge_backend.repository;

import com.example.challenge_backend.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryEvent extends JpaRepository<Event, Integer> {
}
