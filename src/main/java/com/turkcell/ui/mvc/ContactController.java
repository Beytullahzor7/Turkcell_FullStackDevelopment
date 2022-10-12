package com.turkcell.ui.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ContactController {

    @Autowired
    private JavaMailSender mailSender;

    // http://localhost:8080/send/mail
    @GetMapping
    @ResponseBody
    public String mailSendMethod() {
        return null;
    }
}
