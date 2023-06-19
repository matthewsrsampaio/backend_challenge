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
@Table(name = "event")
public class Event {

    @Id
    @Column(name = "id_event")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_event;

    @ManyToOne
    @JoinColumn(name = "id_sub_fk")
    private Subscription subscription;

    @Column(name = "type")
    private String type;

    @Column(name = "created_at")
    private Timestamp createdAt;

}
