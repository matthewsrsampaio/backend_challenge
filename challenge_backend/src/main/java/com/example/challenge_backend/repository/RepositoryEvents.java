package com.example.challenge_backend.repository;

import com.example.challenge_backend.model.Events;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryEvents extends JpaRepository<Events, Integer> {
}
