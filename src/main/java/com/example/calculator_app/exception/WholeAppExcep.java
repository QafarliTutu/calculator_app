package com.example.calculator_app.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.script.ScriptException;
import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class WholeAppExcep {

    @ExceptionHandler({ScriptException.class})
    public ModelAndView handle3(HttpServletRequest req, Exception ex){
        ModelAndView mav = new ModelAndView();
        mav.addObject("message",ex.getMessage());
        mav.setViewName("invalidInputExcep");
        return mav ;
    }

}
