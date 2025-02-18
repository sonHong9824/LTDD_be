package com.example.DaLtdd.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VerifyUserRequest {
    private String email;
    private String password;
    private String otp;
}
