package com.ynmio.LoanApp.controller;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalControllerAdvice {

        @ModelAttribute("isAuthenticated")
        public boolean isAuthenticated(Model model){
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            boolean isAuthenticated = auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated();
            model.addAttribute("isAuthenticated", isAuthenticated);
            if(isAuthenticated){
                return true;
            }
            return false;
        }

}
