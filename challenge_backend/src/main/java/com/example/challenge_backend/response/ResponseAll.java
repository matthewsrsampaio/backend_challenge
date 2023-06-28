package com.example.challenge_backend.response;

import com.example.challenge_backend.model.Event;
import com.example.challenge_backend.model.Status;
import com.example.challenge_backend.model.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseAll implements Serializable {
    private String name;
    private String statusName;
    private String type;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp createdAt;
//    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
//    private Timestamp updatedAt;

    public static ResponseAll of(User user, Status status, Event event) {
        var responseAll = new ResponseAll();
        responseAll.setName(user.getName());
        responseAll.setStatusName(status.getStatusName());
        responseAll.setType(event.getType());
        responseAll.setCreatedAt(user.getCreatedAt());
        return responseAll;
    }
}
