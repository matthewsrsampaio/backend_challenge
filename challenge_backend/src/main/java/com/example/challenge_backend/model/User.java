package com.example.challenge_backend.model;

import com.example.challenge_backend.request.Request;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_user")
public class User {

    @Id
    @Column(name = "id_user")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer idUser;

    @Column(name = "full_name")
    private String name;

    @Column(name = "created_at")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp createdAt = new Timestamp(System.currentTimeMillis());

//    @JsonIgnore
//    @OneToOne(mappedBy = "userFk")
//    private Subscription subscription;

    public static User of(Request request) {
        var user = new User();
        BeanUtils.copyProperties(request, user);
        return user
                .builder()
                .idUser(request.getId())
                .name(request.getName())
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .build();
    }

}
