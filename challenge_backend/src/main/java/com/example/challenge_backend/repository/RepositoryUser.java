package com.example.challenge_backend.repository;

import com.example.challenge_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositoryUser extends JpaRepository<User, Integer>{
    List<User> findByNameIgnoreCaseContaining (String name);
}
