package com.example.DaLtdd.controller;

import com.example.DaLtdd.dto.LoginRequest;
import com.example.DaLtdd.dto.UserCreationRequest;
import com.example.DaLtdd.dto.VerifyUserRequest;
import com.example.DaLtdd.entity.User;
import com.example.DaLtdd.model.MessageResponse;
import com.example.DaLtdd.service.OtpService;
import com.example.DaLtdd.service.UserService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private OtpService otpService;

    @PostMapping("/reset-password")
    public MessageResponse resetPassword(@RequestBody VerifyUserRequest request) {
        return userService.resetPassword(request);
    }
    @PostMapping("/verify-forgot-password-otp")
    public MessageResponse verifyForgotPassword(@RequestParam String email, @RequestParam String otp) {
        return otpService.verifyForgotPassword(email, otp);
    }
    @PostMapping("/send-otp-forgot-pass")
    public MessageResponse sendOtpForgotPass(@RequestParam("email") String email) throws MessagingException {
        return otpService.sendForgotPassword(email);
    }

    @PostMapping("/create")
    public MessageResponse createUser(@RequestBody UserCreationRequest request, @RequestParam String otp) {
        return userService.createUser(request, otp);
    }
    @PostMapping("/send-otp")
    public MessageResponse sendOtp(@RequestBody UserCreationRequest request) throws MessagingException {
        return otpService.sendOtp(request.getEmail());
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        User user = userService.authenticateUser(request.getEmail(), request.getPassword());
        if (user != null) {
            return ResponseEntity.ok(user); // Trả về thông tin User
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new MessageResponse("Sai tài khoản hoặc mật khẩu"));
        }
    }
}
