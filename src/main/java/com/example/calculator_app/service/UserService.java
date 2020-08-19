package com.example.calculator_app.service;

import com.example.calculator_app.entity.XUser;
import com.example.calculator_app.entity.XUserCount;
import com.example.calculator_app.entity.XUserDetails;
import com.example.calculator_app.forms.UserForm;
import com.example.calculator_app.repo.CountRepo;
import com.example.calculator_app.repo.XUserRepo;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
@AllArgsConstructor
@Log4j2
public class UserService {
    private final XUserRepo repo;
    private final BCryptPasswordEncoder bcpe;

    public void updateUser(String ref_email) {
//        boolean result = repo.findXUsersByUsername(ref_email).isPresent();
//        XUser freeUser = repo.findXUsersByUsername(ref_email).orElseThrow(()->new UsernameNotFoundException("User not found!"));
//        if(result){
//            freeUser.getUserCount().setMaxCount(Long.MAX_VALUE);
//            repo.save(freeUser);
//        }
        repo.findXUsersByUsername(ref_email).ifPresent(u -> {
            u.getUserCount().setMaxCount(Long.MAX_VALUE);
            repo.save(u);
        });
    }

    public boolean checkEmail(String email){
        return repo.findXUsersByUsername(email).isPresent();
    }

    public XUser findUser(String username){
       return repo.findXUsersByUsername(username).orElseThrow(()-> new UsernameNotFoundException("User not found."));
    }

    public XUser convertFormToUser(UserForm userForm){
        XUser user = new XUser();
        user.setFullname(userForm.getFullname());
        user.setPassword(bcpe.encode(userForm.getPassword()));
        user.setUsername(userForm.getUsername());
        user.setRoles(new String[]{"USER"});
        user.setRef_user_email(userForm.getRef_user_email());
        return user;
    }

    public void save(XUser user) {
            XUserCount userCount = new XUserCount();
            userCount.setCurrCount(0);
            userCount.setMaxCount(10);
            userCount.setUser(user);
            user.setUserCount(userCount);
            repo.save(user);

    }

    public String getNameFromPrincipal(Authentication auth){
        String name = "";
        if (auth != null) {
            XUserDetails user = (XUserDetails) auth.getPrincipal();
            name = user.getFullname();
        }
        return name;
    }


    public boolean checkSessionCount(HttpSession session) {
        if (session.getAttribute("count") == null) {
            session.setAttribute("count", 0);
        }
        int count = (int) session.getAttribute("count");
        if (count < 3) {
            session.setAttribute("count", count + 1);
            return true;
        }
        else return false;
    }


}
