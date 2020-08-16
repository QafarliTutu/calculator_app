package com.example.calculator_app.service;

import org.springframework.stereotype.Service;

@Service
public class ShareService {

    public String getSocialLink(String social) {
        String url = "";
        switch (social){
            case "facebook":
                url = "http://www.facebook.com/sharer/sharer.php?u=http://myfirstcalculatorapp.herokuapp.com/main-page";
                break;
            case "twitter":
                url = "http://twitter.com/intent/tweet/?url=http://myfirstcalculatorapp.herokuapp.com/main-page";
                break;
            case "e-mail":
                url = "mailto:?body=http://myfirstcalculatorapp.herokuapp.com/main-page";
                break;
            case "linkedin":
                url = "http://www.linkedin.com/shareArticle?mini=true&amp;url=http://myfirstcalculatorapp.herokuapp.com/main-page";
                break;
            case "telegram":
                url = "http://telegram.me/share/url?url=http://myfirstcalculatorapp.herokuapp.com/main-page";
                break;
        }return url;
    }
}
