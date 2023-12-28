package com.example.SkyLine.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.example.SkyLine.entity.Auction;
import com.example.SkyLine.entity.Post;
import com.example.SkyLine.entity.User;
import com.example.SkyLine.enums.EstateTypeEnum;
import com.example.SkyLine.enums.UserRoleEnum;
import com.example.SkyLine.repository.PostRepository;
import com.example.SkyLine.repository.UserRepository;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.multipart.MultipartFile;

@ContextConfiguration(classes = {PostCreationService.class})
@ExtendWith(SpringExtension.class)
class PostCreationServiceTest {
    @Autowired
    private PostCreationService postCreationService;

    @MockBean
    private PostRepository postRepository;

    @MockBean
    private UserRepository userRepository;

    /**
     * Method under test:
     * {@link PostCreationService#createPost(Post, MultipartFile[], String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testCreatePost() throws IOException {
        // TODO: Complete this test.
        //   Reason: R011 Sandboxing policy violation.
        //   Diffblue Cover ran code in your project that tried
        //     to access files (file 'uploads/1-', permission 'write').
        //   Diffblue Cover's default sandboxing policy disallows this in order to prevent
        //   your code from damaging your system environment.
        //   See https://diff.blue/R011 to resolve this issue.

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
        when(postRepository.save(Mockito.<Post>any())).thenReturn(post);

        User client2 = new User();
        client2.setEmail("jane.doe@example.org");
        client2.setEnable(true);
        client2.setFirstName("Jane");
        client2.setId(1);
        client2.setIs_enable(true);
        client2.setLastName("Doe");
        client2.setPassword("iloveyou");
        client2.setPhoneNumber("6625550144");
        client2.setProfile_photo("AXAXAXAX".getBytes("UTF-8"));
        client2.setUserRole(UserRoleEnum.SUPERADMIN);
        client2.setVerificationCode("Verification Code");
        client2.setVerificationCodeForgetPassword(1);

        Post post2 = new Post();
        post2.setAddress("42 Main St");
        post2.setArea((short) 1);
        post2.setBathroom((byte) 'A');
        post2.setBedroom((byte) 'A');
        post2.setCity("Oxford");
        post2.setClient(client2);
        post2.setDescription("The characteristics of someone or something");
        post2.setEstateType(EstateTypeEnum.APARTMENT);
        post2.setExpiryDate(mock(Date.class));
        post2.setId(1);
        post2.setLevel((byte) 'A');
        post2.setMapLink("Map Link");
        post2.setPhotos(new ArrayList<>());
        post2.setPrice(1);
        post2.setPublishDate(mock(Date.class));
        post2.setRent(true);
        post2.setTitle("Dr");
        postCreationService.createPost(post2,
                new MultipartFile[]{new MockMultipartFile("Name", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")))},
                "1234");
    }

    /**
     * Method under test:
     * {@link PostCreationService#createAuction(Auction, MultipartFile[], String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testCreateAuction() throws IOException {
        // TODO: Complete this test.
        //   Reason: R011 Sandboxing policy violation.
        //   Diffblue Cover ran code in your project that tried
        //     to access files (file 'uploads/1-', permission 'write').
        //   Diffblue Cover's default sandboxing policy disallows this in order to prevent
        //   your code from damaging your system environment.
        //   See https://diff.blue/R011 to resolve this issue.

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

        User client2 = new User();
        client2.setEmail("jane.doe@example.org");
        client2.setEnable(true);
        client2.setFirstName("Jane");
        client2.setId(1);
        client2.setIs_enable(true);
        client2.setLastName("Doe");
        client2.setPassword("iloveyou");
        client2.setPhoneNumber("6625550144");
        client2.setProfile_photo("AXAXAXAX".getBytes("UTF-8"));
        client2.setUserRole(UserRoleEnum.SUPERADMIN);
        client2.setVerificationCode("Verification Code");
        client2.setVerificationCodeForgetPassword(1);

        Auction auction = new Auction();
        auction.setAddress("42 Main St");
        auction.setArea((short) 1);
        auction.setBathroom((byte) 'A');
        auction.setBedroom((byte) 'A');
        auction.setBids(new ArrayList<>());
        auction.setCity("Oxford");
        auction.setClient(client2);
        auction.setDescription("The characteristics of someone or something");
        auction.setEnd_time("End time");
        auction.setEstateType(EstateTypeEnum.APARTMENT);
        auction.setExpiryDate(mock(Date.class));
        auction.setId(1);
        auction.setLevel((byte) 'A');
        auction.setMapLink("Map Link");
        auction.setPhotos(new ArrayList<>());
        auction.setPrice(1);
        auction.setPublishDate(mock(Date.class));
        auction.setRent(true);
        auction.setStart_bid("Start bid");
        auction.setStart_time("Start time");
        auction.setStatus(true);
        auction.setTitle("Dr");
        auction.setAddress("42 Main St");
        auction.setArea((short) 1);
        auction.setBathroom((byte) 'A');
        auction.setBedroom((byte) 'A');
        auction.setCity("Oxford");
        auction.setClient(client);
        auction.setDescription("The characteristics of someone or something");
        auction.setEstateType(EstateTypeEnum.APARTMENT);
        auction.setExpiryDate(mock(Date.class));
        auction.setId(1);
        auction.setLevel((byte) 'A');
        auction.setMapLink("Map Link");
        auction.setPhotos(new ArrayList<>());
        auction.setPrice(1);
        auction.setPublishDate(mock(Date.class));
        auction.setRent(true);
        auction.setTitle("Dr");
        when(postRepository.save(Mockito.<Post>any())).thenReturn(auction);

        User client3 = new User();
        client3.setEmail("jane.doe@example.org");
        client3.setEnable(true);
        client3.setFirstName("Jane");
        client3.setId(1);
        client3.setIs_enable(true);
        client3.setLastName("Doe");
        client3.setPassword("iloveyou");
        client3.setPhoneNumber("6625550144");
        client3.setProfile_photo("AXAXAXAX".getBytes("UTF-8"));
        client3.setUserRole(UserRoleEnum.SUPERADMIN);
        client3.setVerificationCode("Verification Code");
        client3.setVerificationCodeForgetPassword(1);

        Auction auction2 = new Auction();
        auction2.setAddress("42 Main St");
        auction2.setArea((short) 1);
        auction2.setBathroom((byte) 'A');
        auction2.setBedroom((byte) 'A');
        auction2.setBids(new ArrayList<>());
        auction2.setCity("Oxford");
        auction2.setClient(client3);
        auction2.setDescription("The characteristics of someone or something");
        auction2.setEnd_time("End time");
        auction2.setEstateType(EstateTypeEnum.APARTMENT);
        auction2.setExpiryDate(mock(Date.class));
        auction2.setId(1);
        auction2.setLevel((byte) 'A');
        auction2.setMapLink("Map Link");
        auction2.setPhotos(new ArrayList<>());
        auction2.setPrice(1);
        auction2.setPublishDate(mock(Date.class));
        auction2.setRent(true);
        auction2.setStart_bid("Start bid");
        auction2.setStart_time("Start time");
        auction2.setStatus(true);
        auction2.setTitle("Dr");
        postCreationService.createAuction(auction2,
                new MultipartFile[]{new MockMultipartFile("Name", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")))},
                "1234");
    }

    /**
     * Method under test: {@link PostCreationService#PostToRetrievalEntity(List)}
     */
    @Test
    void testPostToRetrievalEntity() throws MalformedURLException {
        assertTrue(postCreationService.PostToRetrievalEntity(new ArrayList<>()).isEmpty());
    }

    /**
     * Method under test: {@link PostCreationService#PostToRetrievalEntity(List)}
     */
    @Test
    void testPostToRetrievalEntity2() throws UnsupportedEncodingException, MalformedURLException {
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

        ArrayList<Post> posts = new ArrayList<>();
        posts.add(post);
        assertEquals(1, postCreationService.PostToRetrievalEntity(posts).size());
    }

    /**
     * Method under test: {@link PostCreationService#PostToRetrievalEntity(List)}
     */
    @Test
    void testPostToRetrievalEntity3() throws UnsupportedEncodingException, MalformedURLException {
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

        User client2 = new User();
        client2.setEmail("john.smith@example.org");
        client2.setEnable(false);
        client2.setFirstName("John");
        client2.setId(2);
        client2.setIs_enable(false);
        client2.setLastName("Smith");
        client2.setPassword("post");
        client2.setPhoneNumber("8605550118");
        client2.setProfile_photo(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1});
        client2.setUserRole(UserRoleEnum.ADMIN);
        client2.setVerificationCode("Verification Code");
        client2.setVerificationCodeForgetPassword(0);

        Post post2 = new Post();
        post2.setAddress("17 High St");
        post2.setArea((short) 0);
        post2.setBathroom((byte) 1);
        post2.setBedroom((byte) 1);
        post2.setCity("London");
        post2.setClient(client2);
        post2.setDescription("post");
        post2.setEstateType(EstateTypeEnum.HOUSE);
        post2.setExpiryDate(mock(Date.class));
        post2.setId(2);
        post2.setLevel((byte) 1);
        post2.setMapLink("Map Link");
        post2.setPhotos(new ArrayList<>());
        post2.setPrice(0);
        post2.setPublishDate(mock(Date.class));
        post2.setRent(false);
        post2.setTitle("Mr");

        ArrayList<Post> posts = new ArrayList<>();
        posts.add(post2);
        posts.add(post);
        assertEquals(2, postCreationService.PostToRetrievalEntity(posts).size());
    }

    /**
     * Method under test: {@link PostCreationService#AuctionToRetrievalEntity(List)}
     */
    @Test
    void testAuctionToRetrievalEntity() throws MalformedURLException {
        assertTrue(postCreationService.AuctionToRetrievalEntity(new ArrayList<>()).isEmpty());
    }

    /**
     * Method under test: {@link PostCreationService#AuctionToRetrievalEntity(List)}
     */
    @Test
    void testAuctionToRetrievalEntity2() throws UnsupportedEncodingException, MalformedURLException {
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

        Auction auction = new Auction();
        auction.setAddress("42 Main St");
        auction.setArea((short) 1);
        auction.setBathroom((byte) 'A');
        auction.setBedroom((byte) 'A');
        auction.setBids(new ArrayList<>());
        auction.setCity("Oxford");
        auction.setClient(client);
        auction.setDescription("The characteristics of someone or something");
        auction.setEnd_time("End time");
        auction.setEstateType(EstateTypeEnum.APARTMENT);
        auction.setExpiryDate(mock(Date.class));
        auction.setId(1);
        auction.setLevel((byte) 'A');
        auction.setMapLink("Map Link");
        auction.setPhotos(new ArrayList<>());
        auction.setPrice(1);
        auction.setPublishDate(mock(Date.class));
        auction.setRent(true);
        auction.setStart_bid("Start bid");
        auction.setStart_time("Start time");
        auction.setStatus(true);
        auction.setTitle("Dr");

        ArrayList<Auction> auctions = new ArrayList<>();
        auctions.add(auction);
        assertEquals(1, postCreationService.AuctionToRetrievalEntity(auctions).size());
    }

    /**
     * Method under test: {@link PostCreationService#AuctionToRetrievalEntity(List)}
     */
    @Test
    void testAuctionToRetrievalEntity3() throws UnsupportedEncodingException, MalformedURLException {
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

        Auction auction = new Auction();
        auction.setAddress("42 Main St");
        auction.setArea((short) 1);
        auction.setBathroom((byte) 'A');
        auction.setBedroom((byte) 'A');
        auction.setBids(new ArrayList<>());
        auction.setCity("Oxford");
        auction.setClient(client);
        auction.setDescription("The characteristics of someone or something");
        auction.setEnd_time("End time");
        auction.setEstateType(EstateTypeEnum.APARTMENT);
        auction.setExpiryDate(mock(Date.class));
        auction.setId(1);
        auction.setLevel((byte) 'A');
        auction.setMapLink("Map Link");
        auction.setPhotos(new ArrayList<>());
        auction.setPrice(1);
        auction.setPublishDate(mock(Date.class));
        auction.setRent(true);
        auction.setStart_bid("Start bid");
        auction.setStart_time("Start time");
        auction.setStatus(true);
        auction.setTitle("Dr");

        User client2 = new User();
        client2.setEmail("john.smith@example.org");
        client2.setEnable(false);
        client2.setFirstName("John");
        client2.setId(2);
        client2.setIs_enable(false);
        client2.setLastName("Smith");
        client2.setPassword("auction");
        client2.setPhoneNumber("8605550118");
        client2.setProfile_photo(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1});
        client2.setUserRole(UserRoleEnum.ADMIN);
        client2.setVerificationCode("Verification Code");
        client2.setVerificationCodeForgetPassword(0);

        Auction auction2 = new Auction();
        auction2.setAddress("17 High St");
        auction2.setArea((short) 0);
        auction2.setBathroom((byte) 1);
        auction2.setBedroom((byte) 1);
        auction2.setBids(new ArrayList<>());
        auction2.setCity("London");
        auction2.setClient(client2);
        auction2.setDescription("auction");
        auction2.setEnd_time("End time");
        auction2.setEstateType(EstateTypeEnum.HOUSE);
        auction2.setExpiryDate(mock(Date.class));
        auction2.setId(2);
        auction2.setLevel((byte) 1);
        auction2.setMapLink("Map Link");
        auction2.setPhotos(new ArrayList<>());
        auction2.setPrice(0);
        auction2.setPublishDate(mock(Date.class));
        auction2.setRent(false);
        auction2.setStart_bid("Start bid");
        auction2.setStart_time("Start time");
        auction2.setStatus(false);
        auction2.setTitle("Mr");

        ArrayList<Auction> auctions = new ArrayList<>();
        auctions.add(auction2);
        auctions.add(auction);
        assertEquals(2, postCreationService.AuctionToRetrievalEntity(auctions).size());
    }
}
