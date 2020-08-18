package com.example.calculator_app.controller;

import com.example.calculator_app.forms.LoginForm;
import com.example.calculator_app.forms.UserForm;
import com.example.calculator_app.service.UserService;
import com.example.calculator_app.validator.LoginValidator;
import com.example.calculator_app.validator.RegisterValidator;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;


@Log4j2
@AllArgsConstructor
@Controller
public class LoginRegisController {
    private final UserService userService;
    private final LoginValidator loginValidator;
    private final RegisterValidator registerValidator;

    @InitBinder
    protected void initBinder(WebDataBinder dataBinder) {

        Object target = dataBinder.getTarget();

        if (target != null && target.getClass() == UserForm.class) {
            dataBinder.setValidator(registerValidator);
        }
        if (target != null && target.getClass() == LoginForm.class) {
            dataBinder.setValidator(loginValidator);
        }
    }

    @GetMapping("/register")
    public ModelAndView registerGET() {
        final ModelAndView modelAndView = new ModelAndView("registration");
        modelAndView.addObject("form", new UserForm());
        return modelAndView;
    }

    @PostMapping("/register")
    public ModelAndView registerPOST(@ModelAttribute("form") @Validated UserForm userForm, BindingResult result){
        ModelAndView mav = new ModelAndView();
        if(result.hasErrors()){
            mav.setViewName("registration");
        }
        else{
            userService.save(userForm);
            userService.updateUser(userForm.getRef_user_email());
            mav.setViewName("login");
        }
        return mav;
    }

    @GetMapping("/login")
    public ModelAndView loginGET(){
        return new ModelAndView("login");
    }
}
