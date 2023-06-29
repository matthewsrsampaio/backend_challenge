package com.example.challenge_backend.response;

import com.example.challenge_backend.model.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseStatus implements Serializable {
    private Integer id;
    private String statusName;

    public static ResponseStatus of(Status status) {
        var response = new ResponseStatus();
        BeanUtils.copyProperties(status, response);
        return response;
    }
}
