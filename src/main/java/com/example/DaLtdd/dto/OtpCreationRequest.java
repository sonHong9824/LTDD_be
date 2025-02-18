package com.example.DaLtdd.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OtpCreationRequest {
    private String email;
    private String otp;
    private LocalDateTime expirationTime;
    private boolean isVerified;
    private String type;

}
