package com.ynmio.LoanApp.service;

import com.ynmio.LoanApp.dao.UserRepository;
import com.ynmio.LoanApp.model.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService{
        @Autowired
        UserRepository userRepository;

        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            Optional<MyUser> user=userRepository.findUserByName(username);
            if(user.isPresent()){
                MyUser myUserObj =user.get();
                return User.builder().username(myUserObj.getName())
                        .password(myUserObj.getPassword())
                        .roles(getRole(myUserObj)).build();
            }
            else {
                throw new UsernameNotFoundException(username);
            }
        }
        private String getRole(MyUser myUser) {
            if (myUser.getRole() == null) {
                return "USER";
            }
            return myUser.getRole();
        }
}
