package com.example.challenge_backend.response;

import com.example.challenge_backend.model.User;
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
public class ResponseUser implements Serializable {
    private Integer idUser;
    private String name;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp createdAt;

    public static ResponseUser of(User user) {
        var response = new ResponseUser();
        BeanUtils.copyProperties(user, response);
        return response;
    }

}
