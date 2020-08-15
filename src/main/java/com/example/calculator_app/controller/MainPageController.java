package com.example.calculator_app.controller;


import com.example.calculator_app.entity.History;
import com.example.calculator_app.service.CalculationService;
import com.example.calculator_app.service.CountService;
import com.example.calculator_app.service.HistoryService;
import com.example.calculator_app.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.script.ScriptException;
import javax.servlet.http.HttpSession;

@Log4j2
@RequestMapping("/")
@AllArgsConstructor
@Controller
public class MainPageController {
    private final CountService countService;
    private final UserService userService;
    private final CalculationService calcService;
    private final HistoryService historyService;

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
    public ModelAndView handlePOST(@RequestParam String expression, ModelAndView mav, HttpSession session,Authentication auth) throws ScriptException {
        mav.setViewName("main-page");
        mav.addObject("name",userService.getNameFromPrincipal(auth));
        if(auth != null){
            boolean result = countService.checkCount(auth);
            if(result) {
                mav.addObject("result", calcService.calculate(expression));
                historyService.save(auth, expression,calcService.calculate(expression));
            }
            else mav.addObject("result","You should share link in your social media for extra 10 calculation!");
        }
        else {
            boolean result = userService.checkSessionCount(session);
            if(result) mav.addObject("result", calcService.calculate(expression));
            else mav.addObject("result", "You should register for continue.");
        }return mav;
    }



}

