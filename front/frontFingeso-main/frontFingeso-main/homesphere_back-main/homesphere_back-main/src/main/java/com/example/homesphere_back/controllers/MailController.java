package com.example.homesphere_back.controllers;

import com.example.homesphere_back.services.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mail")
public class MailController {
    @Autowired
    MailService mailService;

    @PostMapping("/send")
    public void sendEmail(@RequestParam String email,
                          String subject,
                          String body){
        mailService.sendEmail(email, subject, body);
    }
}
