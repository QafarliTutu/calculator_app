package com.example.calculator_app.controller;

import com.example.calculator_app.service.CountService;
import com.example.calculator_app.service.ShareService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Log4j2
@RequestMapping("/share")
@AllArgsConstructor
@Controller
public class ShareController {
    private final CountService countService;
    private final ShareService shareService;


    @GetMapping
    public ModelAndView shareGET(){
        return new ModelAndView("share");
    }

    @GetMapping("/{social}")
    public RedirectView facebookShareGET(Authentication auth, @PathVariable String social){
        countService.increaseMaxCount(auth);
        String url = shareService.getSocialLink(social);
        return new RedirectView(url);
    }



}
