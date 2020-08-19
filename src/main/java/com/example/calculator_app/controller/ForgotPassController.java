package com.example.calculator_app.controller;

import com.example.calculator_app.entity.ResetToken;
import com.example.calculator_app.entity.XUser;
import com.example.calculator_app.repo.ResetTokenRepo;
import com.example.calculator_app.service.EmailSendService;
import com.example.calculator_app.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@AllArgsConstructor
public class ForgotPassController {
    private final UserService userService;
    private final ResetTokenRepo resetTokenRepo;
    @Autowired
    private final EmailSendService senderService;
    private final BCryptPasswordEncoder encoder;

    @GetMapping("/forgot-password")
    public ModelAndView handleGET() {
        ModelAndView mav = new ModelAndView("forgotPass");
        mav.addObject("user", new XUser());
        return mav;
    }

    @PostMapping("/forgot-password")
    public ModelAndView handlePOST(ModelAndView mav, XUser user) {
        XUser existingUser = userService.findUser(user.getUsername());
        String message = "";
        if (existingUser != null) {
            ResetToken resetToken = new ResetToken(existingUser);
            resetTokenRepo.save(resetToken);
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(existingUser.getUsername());
            mailMessage.setSubject("Complete Reset Password.");
            mailMessage.setFrom("url.shortener.spring@gmail.com");
            mailMessage.setText("To complete the password reset process, please click here: "
                    + "http://localhost:8080/confirm-reset?token=" + resetToken.getToken());
            senderService.sendEmail(mailMessage);
            message = "Request to reset password received. Check your inbox for the reset link.";
            //mav.setViewName("successForgotPassword");
        } else {
            message = "This email address does not exist!";
        }
        mav.addObject("message", message);
        mav.setViewName("forgotPassResult");
        return mav;
    }

    @GetMapping("/confirm-reset")
    public ModelAndView ConfirmResetToken(ModelAndView mav, @RequestParam("token") String token){
        ResetToken resetToken = resetTokenRepo.findByToken(token);
        if(resetToken!=null){
            XUser xUser = userService.findUser(resetToken.getXUser().getUsername());
            //userService.save(xUser);
            mav.addObject("user",xUser);
            mav.addObject("username",xUser.getUsername());
            mav.setViewName("resetPassword");
        }else {
            mav.addObject("message","The link is invalid or broken!");
            mav.setViewName("forgotPassResult");
        }
        return mav;
    }

    @PostMapping("/reset-password")
    public ModelAndView resetPassword(ModelAndView mav, XUser xUser){
        if(xUser.getUsername() != null){
            XUser tokenUser = userService.findUser(xUser.getUsername());
            tokenUser.setPassword(encoder.encode(xUser.getPassword()));
            userService.save(tokenUser);
            mav.addObject("message","Password successfully reset. You can now login.");
        }else {
            mav.addObject("message","The link is invalid or broken!");
        }
        mav.setViewName("forgotPassResult");
        return mav;
    }



}
