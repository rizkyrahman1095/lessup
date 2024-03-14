package com.rizky.lessup.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@Data
public class UserDto {
    private String email;

    private String password;

    private String name;

    private String username;

    private String address;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;
}
