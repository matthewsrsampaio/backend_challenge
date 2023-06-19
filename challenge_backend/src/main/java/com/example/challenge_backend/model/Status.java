package com.example.challenge_backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "status")
public class Status {

    @Id
    @Column(name = "id_status")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_status;

    @Column(name = "status_name")
    private String status_name;

    @OneToMany(mappedBy = "status")
    private List<Subscription> subscriptionList;
}
