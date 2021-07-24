package com.sachet.userservice.response;

import com.sachet.userservice.dto.UserDto;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
public class Response {

    private LocalDateTime time = LocalDateTime.now();
    private UserDto userDto;
    private String action;

    public Response(UserDto userDto) {
        this.userDto = userDto;
    }

    public Response(UserDto userDto, String action) {
        this.userDto = userDto;
        this.action = action;
    }

}
