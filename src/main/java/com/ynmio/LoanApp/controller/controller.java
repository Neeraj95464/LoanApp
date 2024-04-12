package com.ynmio.LoanApp.controller;

import com.ynmio.LoanApp.dao.UserRepository;
import com.ynmio.LoanApp.model.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class controller {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @GetMapping("/home")
    public String publicUse(){
        return "home";
    }

    @GetMapping("/login")
    public String login(){
        return "custom_login";
    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }
    @PostMapping("/do_register")
    public String register(@ModelAttribute("user") MyUser user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "home";
    }
    @GetMapping("/about")
    public String about(){
        return "about";
    }

    @GetMapping("/user")
    public String normalUser(){
        return "normalUser";
    }
    @GetMapping("/admin")
    public String adminPage(){
        return "adminPage.html";
    }

    @GetMapping("/services")
    public String services(){
        return "services";
    }
    @GetMapping("/invest")
    public String invest(){
        return "invest";
    }
    @GetMapping("/borrow")
    public String borrow(){
        return "borrow";
    }
    @GetMapping("/latter")
    public String latter(){
        return "latter";
    }
}

