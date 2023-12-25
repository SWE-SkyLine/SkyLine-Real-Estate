package com.example.SkyLine.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.SkyLine.repository.UserRepository;
import com.example.SkyLine.utility.Profile;

import jakarta.transaction.Transactional;

@Service
public class ProfileService {

    private UserRepository userRepository;

    public ProfileService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<Profile> getProfileData(Integer id) {
        return userRepository.findById(id).map(user -> {
            Profile profile = new Profile();
            profile.setFirstName(user.getFirstName());
            profile.setLastName(user.getLastName());
            profile.setAccount_type(user.getUserRole().name());
            profile.setEmail(user.getEmail());
            profile.setMobile(user.getPhoneNumber());
            profile.setProfile_photo(user.getProfile_photo());
            System.out.println("profile: " + profile.getProfile_photo());
            return profile;
        });
    }


    @Transactional
    public void updateProfilePhoto(Integer userId, byte[] newProfilePhoto) {
        userRepository.findById(userId).ifPresent(user -> {
            user.setProfile_photo(newProfilePhoto);
            userRepository.save(user);
        });
    }

}
