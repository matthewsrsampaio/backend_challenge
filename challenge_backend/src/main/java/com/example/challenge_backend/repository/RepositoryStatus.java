package com.example.challenge_backend.repository;

import com.example.challenge_backend.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryStatus extends JpaRepository<Status, Integer> {
}
