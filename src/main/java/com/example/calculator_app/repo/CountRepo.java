package com.example.calculator_app.repo;

import com.example.calculator_app.entity.XUser;
import com.example.calculator_app.entity.XUserCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountRepo extends JpaRepository<XUserCount,Long> {

   Optional<XUserCount> findByUser(XUser user);


}
