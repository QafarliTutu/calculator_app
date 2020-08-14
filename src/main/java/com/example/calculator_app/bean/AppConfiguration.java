package com.example.calculator_app.bean;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.script.ScriptEngineManager;

@Configuration
public class AppConfiguration {

    @Bean
     public ScriptEngineManager getScriptEngineManager(){
         return new ScriptEngineManager();
     }

//     @Bean
//    public BCryptPasswordEncoder bCryPswrdEncoder(){
//        return new BCryptPasswordEncoder();
//     }

}
