package com.example.challenge_backend.model;

import com.example.challenge_backend.request.RequestAll;
import com.example.challenge_backend.request.RequestStatus;
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
    private Integer idStatus;

    @Column(name = "status_name")
    private String status;

//    @JsonIgnore
//    @OneToOne(mappedBy = "statusFk")
//    private Subscription subscription;

//    public static Status of(RequestStatus requestStatus) {
//        var status = new Status();
//        BeanUtils.copyProperties(requestStatus, status);
//        return status
//                .builder()
//                .idStatus(requestStatus.getId())
//                .status(requestStatus.getStatusName())
//                .build();
//    }

    public static Status of(RequestAll requestAll) {
        var status = new Status();
        BeanUtils.copyProperties(requestAll, status);
        return status
                .builder()
                .status(requestAll.getStatus())
                .build();
    }

}
