package com.example.calculator_app.controller;

import com.example.calculator_app.service.CountService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Log4j2
@RequestMapping("/share")
@AllArgsConstructor
@Controller
public class ShareController {
    private final CountService countService;


    @GetMapping
    public ModelAndView shareGET(Authentication auth){
        countService.increaseMaxCount(auth);
        return new ModelAndView("share");
    }


}
