package com.example.DaLtdd.service;

import com.example.DaLtdd.dto.UserCreationRequest;
import com.example.DaLtdd.entity.User;
import com.example.DaLtdd.model.MessageResponse;
import com.example.DaLtdd.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OtpService otpService;

    public MessageResponse createUser(UserCreationRequest request, String otp) {
        MessageResponse messageResponse = new MessageResponse();
        messageResponse = otpService.verifyOtp(request.getEmail(), otp);
        if(messageResponse.getMessage().equals("OTP xác nhận thành công"))
        {
            User user = new User();

            user.setName(request.getName());
            user.setEmail(request.getEmail());
            user.setPassword(request.getPassword());

            userRepository.save(user);
            messageResponse.setMessage("Tạo tài khoản thành công");
            return messageResponse;
        }
        return messageResponse;
    }
}
