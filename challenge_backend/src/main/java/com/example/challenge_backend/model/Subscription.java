package com.example.challenge_backend.model;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    private Integer idSubscription;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_user_fk", referencedColumnName = "id_user")
    private User userFk;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_status_fk", referencedColumnName = "id_status")
    private Status statusFk;

    @Column(name = "created_at")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp updatedAt;

}
