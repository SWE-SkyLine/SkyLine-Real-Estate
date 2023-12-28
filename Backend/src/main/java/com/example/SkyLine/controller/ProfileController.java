package com.example.SkyLine.controller;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.apache.http.HttpStatus;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
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
import org.springframework.beans.BeanUtils;

import com.example.SkyLine.DTO.EditPostDTO;
import com.example.SkyLine.entity.Post;
import com.example.SkyLine.service.PostService;
import com.example.SkyLine.service.ProfileService;
import com.example.SkyLine.utility.Profile;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @Autowired
    private PostService PostService;

    public void profileController(ProfileService profileService, PostService PostService) {
        this.profileService = profileService;
        this.PostService = PostService;
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

    @PostMapping("/updatePost")
    public ResponseEntity<String> updatePost(@RequestBody EditPostDTO updateRequest) {
        try {
            Integer postId = updateRequest.getId();
            Post existingPost = PostService.getPostById(postId);

            if (existingPost == null) {
                return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body("Post not found");
            }

            // takes the request object and ignores the 3rd argument and copies the
            // properties to the existing post
            BeanUtils.copyProperties(updateRequest, existingPost, getNullPropertyNames(updateRequest));
            PostService.updatePost(existingPost);

            return ResponseEntity.ok("Post updated successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR)
                    .body("Failed to update post");
        }
    }

    // this is helper function to check if the field is null or not
    private String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null)
                emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

}
