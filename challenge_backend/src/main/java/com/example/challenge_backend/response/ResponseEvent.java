package com.example.challenge_backend.response;

import com.example.challenge_backend.model.Event;
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
public class ResponseEvent implements Serializable {
    private Integer idEvent;
    private String type;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp createdAt;

    public static ResponseEvent of(Event event) {
        var response = new ResponseEvent();
        BeanUtils.copyProperties(event, response);
        return response;
    }
}
