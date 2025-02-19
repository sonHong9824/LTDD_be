package com.example.DaLtdd.repository;

import com.example.DaLtdd.entity.Otp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OtpRepository extends JpaRepository<Otp, String> {
    Optional<Otp> findByEmailAndOtp(String email, String otpId);

    Optional<Otp> findByEmailAndOtpAndType(String email, String otp, String forgotPassword);
}
