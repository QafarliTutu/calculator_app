package com.example.calculator_app.service;

import com.example.calculator_app.entity.History;
import com.example.calculator_app.entity.XUser;
import com.example.calculator_app.entity.XUserDetails;
import com.example.calculator_app.repo.HistoryRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class HistoryService {
    private final HistoryRepo historyRepo;
    private final UserService userService;

    public void save(Authentication auth, String expression, String result){
        XUserDetails userDetails = (XUserDetails) auth.getPrincipal();
        XUser xUser = userService.findUser(userDetails.getUsername());
        historyRepo.save(new History(expression,result, LocalDateTime.now(),xUser));
    }

    public List<History> getAllHistory(Authentication auth) {
        XUserDetails userDetails = (XUserDetails) auth.getPrincipal();
        XUser xUser = userService.findUser(userDetails.getUsername());
        return historyRepo.findAllUserId(xUser.getId()).orElse(new ArrayList<>());
    }
}
