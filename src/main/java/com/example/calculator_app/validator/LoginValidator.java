package com.example.calculator_app.validator;

import com.example.calculator_app.forms.LoginForm;
import com.example.calculator_app.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
@AllArgsConstructor
public class LoginValidator implements Validator {
    private final UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass == LoginForm.class;
    }

    @Override
    public void validate(Object o, Errors errors) {
        LoginForm loginForm = (LoginForm) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,
                "username",
                "userform.email.invalid");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,
                "password",
                "userform.password.enter");
        final String password = userService
                .findUser(loginForm.getUsername()).getPassword();
        if(!BCrypt.checkpw(loginForm.getPassword(),password)){
            errors.rejectValue("password","userform.password.invalid");
        }

    }
}
