package com.example.challenge_backend.response;

import com.example.challenge_backend.model.Events;
import com.example.challenge_backend.model.Subscription;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseEvents implements Serializable {
    private Integer idEvent;
    private Subscription subscriptionFk;
    private String type;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp createdAt;

    public static ResponseEvents of(Events events) {
        var response = new ResponseEvents();
        BeanUtils.copyProperties(events, response);
        return response;
    }
}
