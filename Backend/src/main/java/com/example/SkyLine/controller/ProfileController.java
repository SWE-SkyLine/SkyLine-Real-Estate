package com.example.SkyLine.controller;

import java.util.Optional;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.SkyLine.service.ProfileService;
import com.example.SkyLine.utility.Profile;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    public void profileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/getProfile/{id}")
    public Optional<Profile> getProfile(@PathVariable Integer id) {
        System.out.println("id: " + id);
        return profileService.getProfileData(id);
    }

    @PostMapping("/{id}/updateProfilePhoto")
    public ResponseEntity<String> updateProfilePhoto(
            @PathVariable Integer id,
            @RequestParam("profilePhoto") MultipartFile profilePhoto) {

        if (profilePhoto.isEmpty()) {
            return ResponseEntity.badRequest().body("Profile photo is required");
        }

        try {
            byte[] profilePhotoData = profilePhoto.getBytes();
            profileService.updateProfilePhoto(id, profilePhotoData);
            return ResponseEntity.ok("Profile photo updated successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR)
                    .body("Failed to update profile photo");
        }
    }

    @RequestMapping(value = "/{id}/updateProfileInfo", method = RequestMethod.POST)
    public ResponseEntity<String> updateProfileInfo(@PathVariable Integer id, @RequestBody Profile updatedProfile) {
        try {

            profileService.updateProfileInfo(id, updatedProfile);

            return ResponseEntity.ok("Profile information updated successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR)
                    .body("Failed to update profile information");
        }
    }

    



}
