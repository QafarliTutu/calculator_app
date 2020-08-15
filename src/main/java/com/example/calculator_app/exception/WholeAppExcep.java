package com.example.calculator_app.exception;

import com.example.calculator_app.entity.History;
import com.example.calculator_app.repo.HistoryRepo;
import com.example.calculator_app.service.HistoryService;
import com.example.calculator_app.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.script.ScriptException;

@AllArgsConstructor
@ControllerAdvice
public class WholeAppExcep {
    private final UserService userService;

    @ExceptionHandler({ScriptException.class})
    public ModelAndView handle3(Exception ex, Authentication auth){
        ModelAndView mav = new ModelAndView("main-page");
        String result = "You have mistake at expression.";
        if (ex.getMessage().startsWith("ReferenceError")) result = "You should use only numbers!";
        mav.addObject("result", result);
        mav.addObject("name",userService.getNameFromPrincipal(auth));
        return mav;
    }

}
