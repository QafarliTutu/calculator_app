package com.example.calculator_app.service;

import com.example.calculator_app.entity.XUser;
import com.example.calculator_app.entity.XUserDetails;
import com.example.calculator_app.forms.UserForm;
import com.example.calculator_app.repo.XUserRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private final XUserRepo repo;
    private final BCryptPasswordEncoder bcpe;

    public boolean checkEmail(String username) {
        return repo.findXUsersByUsername(username).isPresent();
    }

    public XUser save(UserForm userForm) {
        XUser user = new XUser();
        user.setFullname(userForm.getFullname());
        user.setPassword(bcpe.encode(userForm.getPassword()));
        user.setUsername(userForm.getUsername());
        user.setRef_user_email(userForm.getRef_user_email());
        user.setRoles(new String[]{"USER"});
        return repo.save(user);
    }

    public String getNameFromPrincipal(Authentication auth){
        String name = "";
        if (auth != null) {
            XUserDetails user = (XUserDetails) auth.getPrincipal();
            name = user.getFullname();
        }
        return name;
    }


}
