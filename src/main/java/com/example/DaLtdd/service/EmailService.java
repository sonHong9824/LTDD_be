package com.example.DaLtdd.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendOtpEmail(String toEmail, String otp) throws MessagingException {
        try{
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(toEmail);
            helper.setSubject("Xác nhận OTP");

            String htmlContent = "<div style='font-family:Arial,sans-serif;padding:20px;'>"
                    + "<h2 style='color:#2E86C1;'>Mã xác nhận OTP của bạn</h2>"
                    + "<p>Xin chào,</p>"
                    + "<p>Mã OTP của bạn là: <b style='font-size:20px;color:#D35400;'>" + otp + "</b></p>"
                    + "<p>Vui lòng nhập mã này để tiếp tục.</p>"
                    + "<hr style='border:1px solid #ddd;'>"
                    + "<p style='font-size:12px;color:#777;'>Nếu bạn không yêu cầu OTP này, vui lòng bỏ qua email này.</p>"
                    + "</div>";

            helper.setText(htmlContent, true);

            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }
}
