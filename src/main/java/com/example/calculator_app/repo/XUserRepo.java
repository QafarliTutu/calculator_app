package com.example.calculator_app.repo;


import com.example.calculator_app.entity.XUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface XUserRepo extends JpaRepository<XUser, Long> {
    Optional<XUser> findXUsersByEmail(String email);
}
