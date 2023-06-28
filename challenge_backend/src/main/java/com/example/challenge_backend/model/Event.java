package com.example.challenge_backend.model;

import com.example.challenge_backend.request.Request;
import com.example.challenge_backend.request.RequestAll;
import com.example.challenge_backend.request.RequestEvent;
import com.fasterxml.jackson.annotation.JsonFormat;
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
public class Event {

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

//    public static Event of(RequestEvent requestEvent) {
//        var event = new Event();
//        BeanUtils.copyProperties(requestEvent, event);
//        return event
//                .builder()
//                .idEvent(requestEvent.getId())
//                .type(requestEvent.getType())
//                .subscriptionFk(requestEvent.getSubscriptionFk())
//                .createdAt(new Timestamp(System.currentTimeMillis()))
//                .build();
//    }

    public static Event of(RequestAll requestAll) {
        var event = new Event();
        BeanUtils.copyProperties(requestAll, event);
        return event
                .builder()
                .type(requestAll.getType())
                .build();
    }

}
