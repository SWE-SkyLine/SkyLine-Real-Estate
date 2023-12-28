package com.example.SkyLine.security;

import com.example.SkyLine.entity.User;
import com.example.SkyLine.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsConfig {
    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return new UserDetailsService(){
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                User user = userRepository.findUserByEmail(username); //email
//                System.out.println("I am in user details service the user email is : " + user.getEmail());
                if (user == null) throw new UsernameNotFoundException("This email is not found");
                return org.springframework.security.core.userdetails.User.withUsername(user.getEmail())
                        .password(user.getPassword())
                        .roles(user.getUserRole().toString())
                        .disabled(!user.isEnable())
                        .build();
            }
        };
    }

}
