package com.example.calculator_app.controller;

import com.example.calculator_app.forms.UserForm;
import com.example.calculator_app.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;


@Log4j2
@AllArgsConstructor
@Controller
public class LoginRegisController {
    private final UserService userService;

    @GetMapping("/register")
    public ModelAndView registerGET() {
        final ModelAndView modelAndView = new ModelAndView("registration");
        modelAndView.addObject("form", new UserForm());
        return modelAndView;
    }

    @PostMapping("/register")
    public RedirectView registerPOST(@ModelAttribute("form") UserForm userForm){
        userService.save(userForm);
        userService.updateUser(userForm.getRef_user_email());
        return new RedirectView("/login");
    }

    @GetMapping("/login")
    public ModelAndView loginGET(){
        return new ModelAndView("login");
    }
}
