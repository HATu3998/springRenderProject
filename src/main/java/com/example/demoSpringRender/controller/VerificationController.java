package com.example.demoSpringRender.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Random;


@Controller
public class VerificationController {


    @Autowired
    private EmailService emailService;

    private String verificationCode = "";

    @GetMapping("/send-verification-code")
    public String sendVerificationCodePage() {
        return "send-verification-code";
    }

    @PostMapping("/send-verification-code")
    public String sendVerificationCode(@RequestParam("email") String email) {
        // Generate a random verification code
        verificationCode = generateVerificationCode();
        emailService.sendVerificationCode(email, verificationCode);
        return "verify-code";
    }

    @GetMapping("/verify-code")
    public String verifyCodePage() {
        return "verify-code";
    }

    @PostMapping("/verify-code")
    public String verifyCode(@RequestParam("code") String code, Model model) {
        if (code.equals(verificationCode)) {
            return "success";
        } else {
            model.addAttribute("error", "Invalid verification code");
            return "verify-code";
        }
    }

    private String generateVerificationCode() {
        Random random = new Random();
        int code = 100000 + random.nextInt(900000);
        return String.valueOf(code);
    }

}
