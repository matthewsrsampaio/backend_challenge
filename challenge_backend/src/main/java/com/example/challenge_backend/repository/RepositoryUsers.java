package com.example.challenge_backend.repository;

import com.example.challenge_backend.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositoryUsers extends JpaRepository<Users, Integer>{
    List<Users> findByNameIgnoreCaseContaining (String name);
}
