package com.example.challenge_backend.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table
public class Events {

    @Id
    @Column(name = "id_event")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer idEvent;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_sub_fk")
    private Subscription subscriptionFk;

    @Column(name = "type")
    private String type;

    @Column(name = "created_at")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp createdAt = new Timestamp(System.currentTimeMillis());

}
