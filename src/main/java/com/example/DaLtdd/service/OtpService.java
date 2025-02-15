package com.example.DaLtdd.service;

import com.example.DaLtdd.dto.OtpCreationRequest;
import com.example.DaLtdd.entity.Otp;
import com.example.DaLtdd.entity.User;
import com.example.DaLtdd.model.MessageResponse;
import com.example.DaLtdd.repository.OtpRepository;
import com.example.DaLtdd.repository.UserRepository;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class OtpService {
    @Autowired
    private OtpRepository otpRepository;

    @Autowired
    private EmailService emailService;
    @Autowired
    private UserRepository userRepository;

    public MessageResponse sendOtp(String email) throws MessagingException {
        MessageResponse messageResponse = new MessageResponse();
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isPresent()) {
            messageResponse.setMessage("Email đã được sử dụng");
            return messageResponse;
        }

        String otp = generateOtp();
        LocalDateTime expiryTime = LocalDateTime.now().plusMinutes(5);

        OtpCreationRequest otp1 = new OtpCreationRequest();
        otp1.setEmail(email);
        otp1.setOtp(otp);
        otp1.setExpirationTime(expiryTime);
        otp1.setType("REGISTER");
        createOtp(otp1);
        emailService.sendOtpEmail(email, otp);

        messageResponse.setMessage("Đã gửi OTP");
        return messageResponse;
    }
    public MessageResponse verifyOtp(String email, String otp) {
        MessageResponse messageResponse = new MessageResponse();
        Optional<Otp> record = otpRepository.findByEmailAndOtp(email, otp);

        if (record.isPresent()) {
            Otp otpRecord = record.get();

            if(otpRecord.getExpirationTime().isBefore(LocalDateTime.now())) {
                messageResponse.setMessage("OTP hết hạn");
                return messageResponse;
            }
            if(otpRecord.isVerified()) {
                messageResponse.setMessage("OTP đã xác nhận trước đó");
                return messageResponse;
            }
            otpRecord.setVerified(true);
            otpRepository.save(otpRecord);

            messageResponse.setMessage("OTP xác nhận thành công");
            return messageResponse;
        } else {
            messageResponse.setMessage("OTP không hợp lệ");
            return messageResponse;
        }
    }

    private String generateOtp() {
        return String.valueOf(100000 + (int)(Math.random() * 900000));
    }
    private void createOtp(OtpCreationRequest otpCreationRequest) {
        Otp otp = new Otp();
        otp.setEmail(otpCreationRequest.getEmail());
        otp.setOtp(otpCreationRequest.getOtp());
        otp.setExpirationTime(otpCreationRequest.getExpirationTime());
        otp.setType("REGISTER");
        otpRepository.save(otp);
        return;
    }
}
