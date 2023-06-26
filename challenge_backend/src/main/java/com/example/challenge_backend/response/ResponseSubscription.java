package com.example.challenge_backend.response;

import com.example.challenge_backend.model.Event;
import com.example.challenge_backend.model.Status;
import com.example.challenge_backend.model.Subscription;
import com.example.challenge_backend.model.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseSubscription implements Serializable {

    private Integer idSubscription;
    private User userFk;
    private Status statusFk;
    private List<Event> event;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp createdAt;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp updatedAt;

    public static ResponseSubscription of(Subscription subs) {
        var response = new ResponseSubscription();
        BeanUtils.copyProperties(subs, response);
        return response;
    }
}
