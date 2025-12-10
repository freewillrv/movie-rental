package com.rahul.verma.movierental.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class UserDto extends ResponseDto {
    private Integer id;
    @NonNull
    private String userName;
    private String emailId;
    private String givenName;
    private String lastName;
    private LocalDateTime lastLogin;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private String password;
}
