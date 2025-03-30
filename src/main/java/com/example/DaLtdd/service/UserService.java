package com.example.DaLtdd.service;

import com.example.DaLtdd.dto.UserCreationRequest;
import com.example.DaLtdd.dto.VerifyUserRequest;
import com.example.DaLtdd.entity.Otp;
import com.example.DaLtdd.entity.User;
import com.example.DaLtdd.model.MessageResponse;
import com.example.DaLtdd.repository.OtpRepository;
import com.example.DaLtdd.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OtpRepository otpRepository;
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
    public MessageResponse resetPassword(VerifyUserRequest request) {
        MessageResponse messageResponse = new MessageResponse();

        Optional<Otp> otpRecord = otpRepository.findByEmailAndOtpAndType(request.getEmail(), request.getOtp(), "FORGOT_PASSWORD");

        if (otpRecord.isPresent() && otpRecord.get().isVerified()) {
            Otp otp = otpRecord.get();

            // Kiểm tra xem OTP có hết hạn không
            if (otp.getExpirationTime().isBefore(LocalDateTime.now())) {
                messageResponse.setMessage("OTP đã hết hạn, vui lòng yêu cầu mã mới");
                return messageResponse;
            }

            Optional<User> user = userRepository.findByEmail(request.getEmail());
            if (user.isPresent()) {
                // Cập nhật mật khẩu mới
                user.get().setPassword(request.getPassword());
                userRepository.save(user.get());

                messageResponse.setMessage("Mật khẩu đã được đặt lại thành công");
            } else {
                messageResponse.setMessage("Người dùng không tồn tại");
            }
        } else {
            messageResponse.setMessage("OTP chưa xác nhận hoặc không hợp lệ");
        }
        return messageResponse;
    }
}
