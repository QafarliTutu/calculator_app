package com.example.calculator_app.validator;

import com.example.calculator_app.forms.UserForm;
import com.example.calculator_app.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.validator.GenericValidator;

@Component
//@PropertySource("classpath:messages.properties")
public class RegisterValidator implements Validator {


    private final UserService userService;

//    @Value("${userform.email.duplicate}")
//    private String emailDublicate;

    public RegisterValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass == UserForm.class;
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserForm userForm = (UserForm) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,
                "fullname", "userform.name.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,
                "username", "userform.email.required");
        if (!errors.hasFieldErrors("username")) {
            if (userService.checkEmail(userForm.getUsername())) {
                errors.rejectValue("username", "userform.email.duplicate");
//            }else if (GenericValidator.isEmail(userForm.getUsername())){
//                errors.rejectValue("username","userform.email.invalid");
//            }
            }
            ValidationUtils.rejectIfEmptyOrWhitespace(errors,
                    "password", "userform.password.required");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors,
                    "passwordConfirm", "userform.passwordConfirm.required");
            if (!userForm.getPassword().equals(userForm.getPasswordConfirm())) {
                errors.rejectValue("passwordConfirm", "userform.password.mismatch");
            }
            if (userForm.getUsername().equals(userForm.getRef_user_email())) {
                errors.rejectValue("ref_user_email", "userform.ref_user.invalid");
            }

        }
    }
}
