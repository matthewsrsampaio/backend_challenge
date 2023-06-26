package com.example.challenge_backend.response;

import com.example.challenge_backend.model.Users;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseUsers implements Serializable {

    private Integer idUser;
    private String name;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp createdAt;

    public static ResponseUsers of(Users users) {
        var response = new ResponseUsers();
        BeanUtils.copyProperties(users, response);
        return response;
    }

}
