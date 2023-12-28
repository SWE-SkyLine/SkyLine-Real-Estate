package com.example.SkyLine.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.example.SkyLine.DTO.EditPostDTO;
import com.example.SkyLine.entity.Post;
import com.example.SkyLine.entity.User;
import com.example.SkyLine.enums.EstateTypeEnum;
import com.example.SkyLine.enums.UserRoleEnum;
import com.example.SkyLine.service.PostService;
import com.example.SkyLine.service.ProfileService;
import com.example.SkyLine.utility.Profile;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {ProfileController.class})
@ExtendWith(SpringExtension.class)
class ProfileControllerTest {
    @MockBean
    private PostService postService;

    @Autowired
    private ProfileController profileController;

    @MockBean
    private ProfileService profileService;

    /**
     * Method under test:
     * {@link ProfileController#updateProfileInfo(Integer, Profile)}
     */
    @Test
    void testUpdateProfileInfo() throws Exception {
        doNothing().when(profileService).updateProfileInfo(Mockito.<Integer>any(), Mockito.<Profile>any());

        Profile profile = new Profile();
        profile.setAccount_type("3");
        profile.setEmail("jane.doe@example.org");
        profile.setFirstName("Jane");
        profile.setLastName("Doe");
        profile.setMobile("Mobile");
        profile.setProfile_photo("AXAXAXAX".getBytes("UTF-8"));
        String content = (new ObjectMapper()).writeValueAsString(profile);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/profile/{id}/updateProfileInfo", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(profileController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Profile information updated successfully"));
    }

    /**
     * Method under test: {@link ProfileController#getProfile(Integer)}
     */
    @Test
    void testGetProfile() throws Exception {
        Profile profile = new Profile();
        profile.setAccount_type("3");
        profile.setEmail("jane.doe@example.org");
        profile.setFirstName("Jane");
        profile.setLastName("Doe");
        profile.setMobile("Mobile");
        profile.setProfile_photo("AXAXAXAX".getBytes("UTF-8"));
        Optional<Profile> ofResult = Optional.of(profile);
        when(profileService.getProfileData(Mockito.<Integer>any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/profile/getProfile/{id}", 1);
        MockMvcBuilders.standaloneSetup(profileController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"profile_photo\":\"QVhBWEFYQVg=\",\"firstName\":\"Jane\",\"lastName\":\"Doe\",\"account_type\":\"3\",\"mobile\":\"Mobile"
                                        + "\",\"email\":\"jane.doe@example.org\"}"));
    }

    /**
     * Method under test: {@link ProfileController#updatePost(EditPostDTO)}
     */
    @Test
    void testUpdatePost() throws Exception {
        User client = new User();
        client.setEmail("jane.doe@example.org");
        client.setEnable(true);
        client.setFirstName("Jane");
        client.setId(1);
        client.setIs_enable(true);
        client.setLastName("Doe");
        client.setPassword("iloveyou");
        client.setPhoneNumber("6625550144");
        client.setProfile_photo("AXAXAXAX".getBytes("UTF-8"));
        client.setUserRole(UserRoleEnum.SUPERADMIN);
        client.setVerificationCode("Verification Code");
        client.setVerificationCodeForgetPassword(1);

        Post post = new Post();
        post.setAddress("42 Main St");
        post.setArea((short) 1);
        post.setBathroom((byte) 'A');
        post.setBedroom((byte) 'A');
        post.setCity("Oxford");
        post.setClient(client);
        post.setDescription("The characteristics of someone or something");
        post.setEstateType(EstateTypeEnum.APARTMENT);
        post.setExpiryDate(mock(Date.class));
        post.setId(1);
        post.setLevel((byte) 'A');
        post.setMapLink("Map Link");
        post.setPhotos(new ArrayList<>());
        post.setPrice(1);
        post.setPublishDate(mock(Date.class));
        post.setRent(true);
        post.setTitle("Dr");
        doNothing().when(postService).updatePost(Mockito.<Post>any());
        when(postService.getPostById(Mockito.<Integer>any())).thenReturn(post);

        EditPostDTO editPostDTO = new EditPostDTO();
        editPostDTO.setAddress("42 Main St");
        editPostDTO.setArea((short) 1);
        editPostDTO.setBedroom((byte) 'A');
        editPostDTO.setDescription("The characteristics of someone or something");
        editPostDTO.setId(1);
        editPostDTO.setLevel((byte) 'A');
        editPostDTO.setMapLink("Map Link");
        editPostDTO.setPrice(1);
        editPostDTO.setTitle("Dr");
        String content = (new ObjectMapper()).writeValueAsString(editPostDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/profile/updatePost")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(profileController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Post updated successfully"));
    }
}
