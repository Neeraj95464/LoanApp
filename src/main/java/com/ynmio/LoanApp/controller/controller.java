package com.ynmio.LoanApp.controller;

import com.ynmio.LoanApp.dao.UserRepository;
import com.ynmio.LoanApp.dao.borrowRepository;
import com.ynmio.LoanApp.model.Borrow;
import com.ynmio.LoanApp.model.MyUser;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
    borrowRepository borrowRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/home")
    public String home() {
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
    public String register(@ModelAttribute("user") MyUser user, HttpSession httpSession){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        String email=user.getEmail();
        boolean b= userRepository.existsByEmail(email);
        user.setRole("USER");
        if(b){
            httpSession.setAttribute("Message","Email already Exist");
            return "redirect:/register";
        }
        userRepository.save(user);
        return "redirect:/home";
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
    @PostMapping("/borrow")
    public String borrow(@ModelAttribute Borrow borrowData){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        borrowData.setEmail(userDetails.getUsername());
        borrowRepository.save(borrowData);
        return "redirect:/borrow";
    }
    @GetMapping("/latter")
    public String latter(){
        return "latter";
    }
}

