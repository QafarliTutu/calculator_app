package com.example.calculator_app.controller;

import com.example.calculator_app.entity.History;
import com.example.calculator_app.service.HistoryService;
import com.example.calculator_app.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Log4j2
@AllArgsConstructor
@Controller
@RequestMapping("/history")
public class HistoryController {

    private final HistoryService historyService;
    private final UserService userService;

    @GetMapping
    public ModelAndView historyGET(Authentication auth){
        ModelAndView mav = new ModelAndView("history");
        List<History> allHistory = historyService.getAllHistory(auth);
        mav.addObject("histories",allHistory);
        mav.addObject("name",userService.getNameFromPrincipal(auth));
        return mav;
    }

}
