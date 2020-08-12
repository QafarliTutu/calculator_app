package com.example.calculator_app.bean;


import org.springframework.context.annotation.Bean;

import javax.script.ScriptEngineManager;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
     public ScriptEngineManager getScriptEngineManager(){
         return new ScriptEngineManager();
     }

}
