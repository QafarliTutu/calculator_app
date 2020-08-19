package com.example.calculator_app.repo;

import com.example.calculator_app.entity.ResetToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResetTokenRepo extends JpaRepository<ResetToken, Long> {
    ResetToken findByToken(String token);
}
