package com.example.challenge_backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "subscription")
public class Subscription {

    @Id
    @Column(name = "id_subscription")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_subscription;

    @ManyToOne
    @JoinColumn(name = "id_user_fk")
    private User user_fk;

    @ManyToOne
    @JoinColumn(name = "id_status_fk")
    private Status status_fk;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

}
