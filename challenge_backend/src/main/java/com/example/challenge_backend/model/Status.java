package com.example.challenge_backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Status {

    @Id
    @Column(name = "id_status")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer idStatus;

    @Column(name = "status_name")
    private String statusName;

    @JsonIgnore
    @OneToOne(mappedBy = "statusFk")
    private Subscription subscription;
}
