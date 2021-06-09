package com.demo.dto.security;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdatePasswordDto {

    private String username;
    private String currentPassword;
    private String newPassword;

}
