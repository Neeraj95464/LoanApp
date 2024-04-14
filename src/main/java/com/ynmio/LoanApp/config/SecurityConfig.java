package com.ynmio.LoanApp.config;

import com.ynmio.LoanApp.handler.LoginSuccessfullHandler;
import com.ynmio.LoanApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

        @Autowired
        UserService userService;

        @Bean
        public PasswordEncoder passwordEncoder(){
            return new BCryptPasswordEncoder();
        }

        @Bean
        public UserDetailsService userDetailsService(){
            return userService;
        }
        @Bean
        public AuthenticationProvider authenticationProvider(){
            DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();
            daoAuthenticationProvider.setUserDetailsService(userService);
            daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
            return daoAuthenticationProvider;
        }

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
             httpSecurity
                    .csrf(AbstractHttpConfigurer::disable)
                    .authorizeHttpRequests(registry->{
                        registry.requestMatchers("/home/**","/register/**").permitAll();
                        registry.requestMatchers("/admin/**").hasRole("ADMIN");
                        registry.requestMatchers("/user/**").hasRole("USER");
                        registry.requestMatchers("/invest/**").authenticated();
                        registry.requestMatchers("/borrow/**").authenticated();
                        registry.anyRequest().permitAll();
                    }).formLogin(formLogin -> formLogin
                            .loginPage("/login")
                            .successHandler(new LoginSuccessfullHandler())
                            .permitAll());
                    return  httpSecurity.build();
        }
    }
//(httpSecurityFormLoginConfigurer -> {
//        httpSecurityFormLoginConfigurer.loginPage("/login").permitAll();
//                })
