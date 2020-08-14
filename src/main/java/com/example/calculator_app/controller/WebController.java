package com.example.calculator_app.controller;

import com.example.calculator_app.forms.UserForm;
import com.example.calculator_app.service.CalculationService;
import com.example.calculator_app.service.UserService;
import com.example.calculator_app.session.UserSession;
import lombok.AllArgsConstructor;
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
        return new RedirectView("/main-guest");
    }

    @GetMapping("/main-guest")
    public String handleGET() {
        return "main-guest";
    }

    @PostMapping("/main-guest")
    public ModelAndView handlePOST(@RequestParam String x, ModelAndView mav, HttpSession session, UserSession userSession) throws ScriptException {
        session.setAttribute(UserSession.ATTR, UserSession.count);
        UserSession.count += 1;
        if ((int) session.getAttribute(UserSession.ATTR) < 3) {
            mav.setViewName("result");
            mav.addObject("result", calcService.calculate(x));
            return mav;
        }
        mav.addObject("result", "You should register for continue.");
        mav.setViewName("result");
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
        return new RedirectView("/main-guest");
    }

}

