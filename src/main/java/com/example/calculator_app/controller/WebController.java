package com.example.calculator_app.controller;

import com.example.calculator_app.entity.XUserDetails;
import com.example.calculator_app.forms.UserForm;
import com.example.calculator_app.service.CalculationService;
import com.example.calculator_app.service.UserService;
import com.example.calculator_app.session.UserSession;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.script.ScriptException;
import javax.servlet.http.HttpSession;


@RequestMapping("/")
@AllArgsConstructor
@Controller
public class WebController {

    private final UserService userService;
    private final CalculationService calcService;

    @GetMapping("/")
    public RedirectView redirect() {
        return new RedirectView("/main-page");
    }

    @GetMapping("/main-page")
    public ModelAndView handleGET(Authentication auth) {
        ModelAndView mav = new ModelAndView("main-page");
        mav.addObject("result", "");
        mav.addObject("name", userService.getNameFromPrincipal(auth));
        return mav;
    }

    @PostMapping("/main-page")
    public ModelAndView handlePOST(@RequestParam String x, ModelAndView mav, HttpSession session,Authentication auth) throws ScriptException {
        mav.setViewName("main-page");
        mav.addObject("name",userService.getNameFromPrincipal(auth));
        if(session.getAttribute("count")==null){
            session.setAttribute("count",0); }
        int count = (int) session.getAttribute("count");
        if (count < 3) {
            session.setAttribute("count", count + 1);
            mav.addObject("result", calcService.calculate(x));
            return mav;
        }
        mav.addObject("result", "You should register for continue.");
        return mav;
    }

    @GetMapping("/register")
    public ModelAndView registerGET() {
        final ModelAndView modelAndView = new ModelAndView("registration");
        modelAndView.addObject("form", new UserForm());
        return modelAndView;
    }

    @PostMapping("/register")
    public RedirectView registerPOST(@ModelAttribute("form") UserForm userForm){
        userService.save(userForm);
        return new RedirectView("/main-page");
    }

    @GetMapping("/login")
    public ModelAndView loginGET(){
        return new ModelAndView("login");
    }

}

