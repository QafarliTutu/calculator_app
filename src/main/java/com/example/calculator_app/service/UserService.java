package com.example.calculator_app.service;

import com.example.calculator_app.entity.XUser;
import com.example.calculator_app.forms.UserForm;
import com.example.calculator_app.repo.XUserRepo;
import lombok.AllArgsConstructor;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private final XUserRepo repo;
//    private final BCryptPasswordEncoder bcpe;

    public boolean checkEmail(String email) {
        return repo.findXUsersByEmail(email).isPresent();
    }

    public XUser save(UserForm userForm) {
        XUser user = new XUser();
        user.setUsername(userForm.getUsername());
//        user.setPassword(bcpe.encode(userForm.getPassword()));
        user.setPassword(userForm.getPassword());
        user.setEmail(userForm.getEmail());
        user.setRef_user_email(userForm.getRef_user_email());
        return repo.save(user);
    }


}
