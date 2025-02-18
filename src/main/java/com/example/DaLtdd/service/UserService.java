package com.example.DaLtdd.service;

import com.example.DaLtdd.dto.UserCreationRequest;
import com.example.DaLtdd.dto.VerifyUserRequest;
import com.example.DaLtdd.entity.User;
import com.example.DaLtdd.model.MessageResponse;
import com.example.DaLtdd.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
    public User authenticateUser(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent() && user.get().getPassword().equals(password)) {
            return user.get();
        }
        return null;
    }
    public MessageResponse forgetPassword(VerifyUserRequest request) {
        MessageResponse messageResponse = new MessageResponse();
        messageResponse = otpService.verifyForgotPassword(request.getEmail(), request.getOtp());
        if (!messageResponse.getMessage().equals("OTP xác nhận thành công")) {
            return messageResponse;
        }
        Optional<User> userOptional = userRepository.findByEmail(request.getEmail());
        User user = userOptional.get();
        user.setPassword(request.getPassword());
        userRepository.save(user);
        messageResponse.setMessage("Đã thay đổi mật khẩu");
        return messageResponse;
    }
}
