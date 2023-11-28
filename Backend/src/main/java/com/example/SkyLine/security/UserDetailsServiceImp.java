package com.example.SkyLine.security;

import com.example.SkyLine.entity.User;
import com.example.SkyLine.enums.UserRoleEnum;
import com.example.SkyLine.repository.UserRepository;
import com.example.SkyLine.utility.UserRoleEnumAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImp implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(email);
        if(user == null) throw new UsernameNotFoundException("This email is not found");
        UserDetails details  = org.springframework.security.core.userdetails.User.withUsername(user.getEmail())
                .password(user.getPassword())
                .roles(new UserRoleEnumAdapter().UserRoleEnumToString(user.getUserRole()))
                .build();
        return null;
    }
}
