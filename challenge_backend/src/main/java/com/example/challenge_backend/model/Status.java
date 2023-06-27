package com.example.challenge_backend.model;

import com.example.challenge_backend.request.Request;
import com.example.challenge_backend.request.RequestStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.sql.Timestamp;

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

    public static Status of(RequestStatus requestStatus) {
        var status = new Status();
        BeanUtils.copyProperties(requestStatus, status);
        return status
                .builder()
                .idStatus(requestStatus.getId())
                .statusName(requestStatus.getStatusName())
                .build();
    }
}
