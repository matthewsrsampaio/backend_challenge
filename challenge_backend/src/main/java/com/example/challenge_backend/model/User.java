package com.example.challenge_backend.model;

import com.example.challenge_backend.request.RequestAll;
import com.fasterxml.jackson.annotation.JsonFormat;
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
@Table(name = "_user")
public class User {

    @Id
    @Column(name = "id_user")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "full_name")
    private String name;

    @Column(name = "created_at")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp createdAt = new Timestamp(System.currentTimeMillis());

    public static User of(RequestAll requestAll) {
        var user = new User();
        BeanUtils.copyProperties(requestAll, user);
        return user
                .builder()
//                .idUser(requestAll.getId())
                .name(requestAll.getName())
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .build();
    }

}
