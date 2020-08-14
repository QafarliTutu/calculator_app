package com.example.calculator_app.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;


@Service
@AllArgsConstructor
public class CalculationService {

    private final ScriptEngineManager mgr;

    public String calculate(String x) throws ScriptException {
        ScriptEngine engine = mgr.getEngineByName("JavaScript");
        if(x.equals("")) return "Empty expression!";
        String result = engine.eval(x).toString();
        if (result.equals("Infinity")) return "Sorry, Dividing to zero is impossible!";
        return result;
    }
}
