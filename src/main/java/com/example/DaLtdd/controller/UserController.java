package com.example.DaLtdd.controller;

import com.example.DaLtdd.dto.UserCreationRequest;
import com.example.DaLtdd.entity.User;
import com.example.DaLtdd.model.MessageResponse;
import com.example.DaLtdd.service.OtpService;
import com.example.DaLtdd.service.UserService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private OtpService otpService;

    @PostMapping("/create")
    public MessageResponse createUser(@RequestBody UserCreationRequest request, @RequestParam String otp) {
        return userService.createUser(request, otp);
    }
    @PostMapping("/send-otp")
    public MessageResponse sendOtp(@RequestBody UserCreationRequest request) throws MessagingException {
        return otpService.sendOtp(request.getEmail());
    }
}
