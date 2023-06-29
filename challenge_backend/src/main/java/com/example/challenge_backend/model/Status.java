package com.example.challenge_backend.model;

import com.example.challenge_backend.request.RequestAll;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

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
    private Integer id;

    @Column(name = "status_name")
    private String status;

    public static Status of(RequestAll requestAll) {
        var status = new Status();
        BeanUtils.copyProperties(requestAll, status);
        return status
                .builder()
//                .idStatus(requestAll.getId())
                .status(requestAll.getStatus())
                .build();
    }

}
