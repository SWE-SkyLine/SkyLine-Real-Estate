package com.example.SkyLine.service;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.SkyLine.entity.User;
import com.example.SkyLine.enums.UserRoleEnum;
import com.example.SkyLine.repository.UserRepository;
import com.example.SkyLine.utility.Profile;

import java.io.UnsupportedEncodingException;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ProfileService.class})
@ExtendWith(SpringExtension.class)
class ProfileServiceTest {
    @Autowired
    private ProfileService profileService;

    @MockBean
    private UserRepository userRepository;

    /**
     * Method under test: {@link ProfileService#getProfileData(Integer)}
     */
    @Test
    void testGetProfileData() throws UnsupportedEncodingException {
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setEnable(true);
        user.setFirstName("Jane");
        user.setId(1);
        user.setIs_enable(true);
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setPhoneNumber("6625550144");
        user.setProfile_photo("AXAXAXAX".getBytes("UTF-8"));
        user.setUserRole(UserRoleEnum.SUPERADMIN);
        user.setVerificationCode("Verification Code");
        user.setVerificationCodeForgetPassword(1);
        Optional<User> ofResult = Optional.of(user);
        when(userRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);
        Optional<Profile> actualProfileData = profileService.getProfileData(1);
        verify(userRepository).findById(Mockito.<Integer>any());
        Profile getResult = actualProfileData.get();
        assertEquals("6625550144", getResult.getMobile());
        assertEquals("Doe", getResult.getLastName());
        assertEquals("Jane", getResult.getFirstName());
        assertEquals("SUPERADMIN", getResult.getAccount_type());
        assertEquals("jane.doe@example.org", getResult.getEmail());
        assertTrue(actualProfileData.isPresent());
        byte[] expectedProfile_photo = "AXAXAXAX".getBytes("UTF-8");
        assertArrayEquals(expectedProfile_photo, getResult.getProfile_photo());
    }

    /**
     * Method under test: {@link ProfileService#getProfileData(Integer)}
     */
    @Test
    void testGetProfileData2() {
        Optional<User> emptyResult = Optional.empty();
        when(userRepository.findById(Mockito.<Integer>any())).thenReturn(emptyResult);
        Optional<Profile> actualProfileData = profileService.getProfileData(1);
        verify(userRepository).findById(Mockito.<Integer>any());
        assertFalse(actualProfileData.isPresent());
        assertSame(emptyResult, actualProfileData);
    }

    /**
     * Method under test: {@link ProfileService#updateProfilePhoto(Integer, byte[])}
     */
    @Test
    void testUpdateProfilePhoto() throws UnsupportedEncodingException {
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setEnable(true);
        user.setFirstName("Jane");
        user.setId(1);
        user.setIs_enable(true);
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setPhoneNumber("6625550144");
        user.setProfile_photo("AXAXAXAX".getBytes("UTF-8"));
        user.setUserRole(UserRoleEnum.SUPERADMIN);
        user.setVerificationCode("Verification Code");
        user.setVerificationCodeForgetPassword(1);
        Optional<User> ofResult = Optional.of(user);

        User user2 = new User();
        user2.setEmail("jane.doe@example.org");
        user2.setEnable(true);
        user2.setFirstName("Jane");
        user2.setId(1);
        user2.setIs_enable(true);
        user2.setLastName("Doe");
        user2.setPassword("iloveyou");
        user2.setPhoneNumber("6625550144");
        user2.setProfile_photo("AXAXAXAX".getBytes("UTF-8"));
        user2.setUserRole(UserRoleEnum.SUPERADMIN);
        user2.setVerificationCode("Verification Code");
        user2.setVerificationCodeForgetPassword(1);
        when(userRepository.save(Mockito.<User>any())).thenReturn(user2);
        when(userRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);
        profileService.updateProfilePhoto(1, "AXAXAXAX".getBytes("UTF-8"));
        verify(userRepository).findById(Mockito.<Integer>any());
        verify(userRepository).save(Mockito.<User>any());
    }

    /**
     * Method under test: {@link ProfileService#updateProfilePhoto(Integer, byte[])}
     */
    @Test
    void testUpdateProfilePhoto2() throws UnsupportedEncodingException {
        Optional<User> emptyResult = Optional.empty();
        when(userRepository.findById(Mockito.<Integer>any())).thenReturn(emptyResult);
        profileService.updateProfilePhoto(1, "AXAXAXAX".getBytes("UTF-8"));
        verify(userRepository).findById(Mockito.<Integer>any());
    }

    /**
     * Method under test: {@link ProfileService#updateProfileInfo(Integer, Profile)}
     */
    @Test
    void testUpdateProfileInfo() throws UnsupportedEncodingException {
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setEnable(true);
        user.setFirstName("Jane");
        user.setId(1);
        user.setIs_enable(true);
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setPhoneNumber("6625550144");
        user.setProfile_photo("AXAXAXAX".getBytes("UTF-8"));
        user.setUserRole(UserRoleEnum.SUPERADMIN);
        user.setVerificationCode("Verification Code");
        user.setVerificationCodeForgetPassword(1);
        Optional<User> ofResult = Optional.of(user);

        User user2 = new User();
        user2.setEmail("jane.doe@example.org");
        user2.setEnable(true);
        user2.setFirstName("Jane");
        user2.setId(1);
        user2.setIs_enable(true);
        user2.setLastName("Doe");
        user2.setPassword("iloveyou");
        user2.setPhoneNumber("6625550144");
        user2.setProfile_photo("AXAXAXAX".getBytes("UTF-8"));
        user2.setUserRole(UserRoleEnum.SUPERADMIN);
        user2.setVerificationCode("Verification Code");
        user2.setVerificationCodeForgetPassword(1);
        when(userRepository.save(Mockito.<User>any())).thenReturn(user2);
        when(userRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        Profile profileInfo = new Profile();
        profileInfo.setAccount_type("3");
        profileInfo.setEmail("jane.doe@example.org");
        profileInfo.setFirstName("Jane");
        profileInfo.setLastName("Doe");
        profileInfo.setMobile("Mobile");
        profileInfo.setProfile_photo("AXAXAXAX".getBytes("UTF-8"));
        profileService.updateProfileInfo(1, profileInfo);
        verify(userRepository).findById(Mockito.<Integer>any());
        verify(userRepository).save(Mockito.<User>any());
    }

    /**
     * Method under test: {@link ProfileService#updateProfileInfo(Integer, Profile)}
     */
    @Test
    void testUpdateProfileInfo2() throws UnsupportedEncodingException {
        Optional<User> emptyResult = Optional.empty();
        when(userRepository.findById(Mockito.<Integer>any())).thenReturn(emptyResult);

        Profile profileInfo = new Profile();
        profileInfo.setAccount_type("3");
        profileInfo.setEmail("jane.doe@example.org");
        profileInfo.setFirstName("Jane");
        profileInfo.setLastName("Doe");
        profileInfo.setMobile("Mobile");
        profileInfo.setProfile_photo("AXAXAXAX".getBytes("UTF-8"));
        profileService.updateProfileInfo(1, profileInfo);
        verify(userRepository).findById(Mockito.<Integer>any());
        assertEquals("3", profileInfo.getAccount_type());
        assertEquals("Doe", profileInfo.getLastName());
        assertEquals("Jane", profileInfo.getFirstName());
        assertEquals("Mobile", profileInfo.getMobile());
        assertEquals("jane.doe@example.org", profileInfo.getEmail());
        byte[] expectedProfile_photo = "AXAXAXAX".getBytes("UTF-8");
        assertArrayEquals(expectedProfile_photo, profileInfo.getProfile_photo());
    }
}
