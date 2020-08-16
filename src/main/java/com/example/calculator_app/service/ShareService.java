package com.example.calculator_app.service;

import org.springframework.stereotype.Service;

@Service
public class ShareService {

    public String getSocialLink(String social) {
        String url = "";
        switch (social){
            case "facebook":
                url = "http://www.facebook.com/sharer/sharer.php?u=https%3A%2F%2Fgithub.com%2FQafarliTutu%2Fcalculator_app%2Ftree%2Fmaster%2Fsrc%2Fmain%2Fjava%2Fcom%2Fexample%2Fcalculator_app&ext=1597605902&hash=AeZyO2jVSxxr9qlK";
                break;
            case "facebook1":
                url = "http://www.facebook.com/sharer/sharer.php?u=https%3A%2F%2Fgithub.com%2FQafarliTutu%2Fcalculator_app%2Ftree%2Fmaster%2Fsrc%2Fmain%2Fjava%2Fcom%2Fexample%2Fcalculator_app&ext=1597605902&hash=AeZyO2jVSxxr9qlK";
                break;
        }return url;
    }
}
