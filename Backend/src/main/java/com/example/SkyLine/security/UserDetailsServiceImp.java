//package com.example.SkyLine.security;
//
//import com.example.SkyLine.entity.User;
//import com.example.SkyLine.repository.UserRepository;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//@Service
//public class UserDetailsServiceImp implements UserDetailsService {
//    @Autowired
//    private  PasswordEncoder passwordEncoder;
//    @Autowired
//    private  UserRepository userRepository;
//
//
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//       User user = userRepository.findUserByEmail(email);
//
//        System.out.println("I am in user details service the user email is : " + user.getEmail());
//        if (user == null) throw new UsernameNotFoundException("This email is not found");
//        return org.springframework.security.core.userdetails.User.withUsername(user.getEmail())
//                .password(user.getPassword())
//                .roles("ROLE_" + user.getUserRole().toString())
//                .disabled(!user.isEnable())
//                .build();
//    }
//}
