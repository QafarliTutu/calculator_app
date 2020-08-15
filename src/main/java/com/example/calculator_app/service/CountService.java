package com.example.calculator_app.service;

import com.example.calculator_app.entity.XUser;
import com.example.calculator_app.entity.XUserCount;
import com.example.calculator_app.entity.XUserDetails;
import com.example.calculator_app.repo.CountRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CountService {
    private final CountRepo countRepo;
    private final UserService userService;

    public boolean checkCount(Authentication auth){
        XUserDetails userDetails = (XUserDetails) auth.getPrincipal();
        XUser xUser = userService.findUser(userDetails.getUsername());
        XUserCount userCount = xUser.getUserCount();
        if (userCount.getMaxCount() == Long.MAX_VALUE) return true;
        if(userCount.getMaxCount()-userCount.getCurrCount()>0) {
            userCount.setCurrCount(userCount.getCurrCount() + 1);
            xUser.setUserCount(userCount);
            countRepo.save(userCount);
            return true;
        }
        else return false;
    }

    public void increaseMaxCount(Authentication auth) {
        XUserDetails userDetails = (XUserDetails) auth.getPrincipal();
        XUser xUser = userService.findUser(userDetails.getUsername());
        XUserCount userCount = xUser.getUserCount();
        userCount.setMaxCount(userCount.getMaxCount()+10);
        countRepo.save(userCount);
    }
}
