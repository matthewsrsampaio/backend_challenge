package com.example.challenge_backend.response;

import com.example.challenge_backend.model.Events;
import com.example.challenge_backend.model.Status;
import com.example.challenge_backend.model.Subscription;
import com.example.challenge_backend.model.Users;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseSubscriptions implements Serializable {

    private Integer idSubscription;
    private Users usersFk;
    private Status statusFk;
    private List<Events> events;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp createdAt;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp updatedAt;

    public static ResponseSubscriptions of(Subscription subs) {
        var response = new ResponseSubscriptions();
        BeanUtils.copyProperties(subs, response);
        return response;
    }
}
