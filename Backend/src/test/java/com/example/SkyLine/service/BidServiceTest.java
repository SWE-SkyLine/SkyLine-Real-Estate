package com.example.SkyLine.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.SkyLine.DTO.BidRetrievalDTO;
import com.example.SkyLine.entity.Auction;
import com.example.SkyLine.entity.Bid;
import com.example.SkyLine.entity.Client;
import com.example.SkyLine.entity.Company;
import com.example.SkyLine.entity.User;
import com.example.SkyLine.enums.EstateTypeEnum;
import com.example.SkyLine.enums.UserRoleEnum;
import com.example.SkyLine.repository.BidAuctionsRepository;
import com.example.SkyLine.repository.ClientRepository;
import com.example.SkyLine.repository.PostRepository;

import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {BidService.class})
@ExtendWith(SpringExtension.class)
class BidServiceTest {
    @MockBean
    private BidAuctionsRepository bidAuctionsRepository;

    @Autowired
    private BidService bidService;

    @MockBean
    private ClientRepository clientRepository;

    @MockBean
    private PostRepository postRepository;

    /**
     * Method under test: {@link BidService#addBid(int, int, int)}
     */
    @Test
    void testAddBid() throws UnsupportedEncodingException {
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

        Company company = new Company();
        company.setClients(new ArrayList<>());
        company.setEmail("jane.doe@example.org");
        company.setEnable(true);
        company.setFirstName("Jane");
        company.setId(1);
        company.setIs_enable(true);
        company.setLastName("Doe");
        company.setPassword("iloveyou");
        company.setPhoneNumber("6625550144");
        company.setProfile_photo("AXAXAXAX".getBytes("UTF-8"));
        company.setUserRole(UserRoleEnum.SUPERADMIN);
        company.setVerificationCode("Verification Code");
        company.setVerificationCodeForgetPassword(1);

        Client client2 = new Client();
        client2.setBids(new ArrayList<>());
        client2.setCompany(company);
        client2.setEmail("jane.doe@example.org");
        client2.setEnable(true);
        client2.setFirstName("Jane");
        client2.setId(1);
        client2.setIs_enable(true);
        client2.setLastName("Doe");
        client2.setPassword("iloveyou");
        client2.setPhoneNumber("6625550144");
        client2.setPosts(new ArrayList<>());
        client2.setProfile_photo("AXAXAXAX".getBytes("UTF-8"));
        client2.setUserRole(UserRoleEnum.SUPERADMIN);
        client2.setVerificationCode("Verification Code");
        client2.setVerificationCodeForgetPassword(1);

        Bid bid = new Bid();
        bid.setAuction(auction);
        bid.setBidPrice(1);
        bid.setClient(client2);
        bid.setId(1);
        when(bidAuctionsRepository.save(Mockito.<Bid>any())).thenReturn(bid);

        Company company2 = new Company();
        company2.setClients(new ArrayList<>());
        company2.setEmail("jane.doe@example.org");
        company2.setEnable(true);
        company2.setFirstName("Jane");
        company2.setId(1);
        company2.setIs_enable(true);
        company2.setLastName("Doe");
        company2.setPassword("iloveyou");
        company2.setPhoneNumber("6625550144");
        company2.setProfile_photo("AXAXAXAX".getBytes("UTF-8"));
        company2.setUserRole(UserRoleEnum.SUPERADMIN);
        company2.setVerificationCode("Verification Code");
        company2.setVerificationCodeForgetPassword(1);

        Client client3 = new Client();
        client3.setBids(new ArrayList<>());
        client3.setCompany(company2);
        client3.setEmail("jane.doe@example.org");
        client3.setEnable(true);
        client3.setFirstName("Jane");
        client3.setId(1);
        client3.setIs_enable(true);
        client3.setLastName("Doe");
        client3.setPassword("iloveyou");
        client3.setPhoneNumber("6625550144");
        client3.setPosts(new ArrayList<>());
        client3.setProfile_photo("AXAXAXAX".getBytes("UTF-8"));
        client3.setUserRole(UserRoleEnum.SUPERADMIN);
        client3.setVerificationCode("Verification Code");
        client3.setVerificationCodeForgetPassword(1);
        when(clientRepository.findById(anyInt())).thenReturn(client3);

        User client4 = new User();
        client4.setEmail("jane.doe@example.org");
        client4.setEnable(true);
        client4.setFirstName("Jane");
        client4.setId(1);
        client4.setIs_enable(true);
        client4.setLastName("Doe");
        client4.setPassword("iloveyou");
        client4.setPhoneNumber("6625550144");
        client4.setProfile_photo("AXAXAXAX".getBytes("UTF-8"));
        client4.setUserRole(UserRoleEnum.SUPERADMIN);
        client4.setVerificationCode("Verification Code");
        client4.setVerificationCodeForgetPassword(1);

        Auction auction2 = new Auction();
        auction2.setAddress("42 Main St");
        auction2.setArea((short) 1);
        auction2.setBathroom((byte) 'A');
        auction2.setBedroom((byte) 'A');
        auction2.setBids(new ArrayList<>());
        auction2.setCity("Oxford");
        auction2.setClient(client4);
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
        when(postRepository.findById(anyInt())).thenReturn(auction2);
        boolean actualAddBidResult = bidService.addBid(1, 1, 1);
        verify(clientRepository).findById(anyInt());
        verify(postRepository).findById(anyInt());
        verify(bidAuctionsRepository).save(Mockito.<Bid>any());
        assertTrue(actualAddBidResult);
    }

    /**
     * Method under test: {@link BidService#getBidsOfAuction(int)}
     */
    @Test
    void testGetBidsOfAuction() {
        when(bidAuctionsRepository.findAllByAuction_Id(anyInt())).thenReturn(new ArrayList<>());
        ArrayList<BidRetrievalDTO> actualBidsOfAuction = bidService.getBidsOfAuction(1);
        verify(bidAuctionsRepository).findAllByAuction_Id(anyInt());
        assertTrue(actualBidsOfAuction.isEmpty());
    }

    /**
     * Method under test: {@link BidService#getBidsOfAuction(int)}
     */
    @Test
    void testGetBidsOfAuction2() throws UnsupportedEncodingException {
        User client = new User();
        client.setEmail("jane.doe@example.org");
        client.setEnable(true);
        client.setFirstName("Jane");
        client.setId(1);
        client.setIs_enable(true);
        client.setLastName("Doe");
        client.setPassword("iloveyou");
        client.setPhoneNumber("6625550144");
        client.setProfile_photo("A\nA\nA\nA\n".getBytes("UTF-8"));
        client.setUserRole(UserRoleEnum.SUPERADMIN);
        client.setVerificationCode("Verification Code");
        client.setVerificationCodeForgetPassword(10);

        Auction auction = new Auction();
        auction.setAddress("42 Main St");
        auction.setArea((short) 10);
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
        auction.setPrice(10);
        auction.setPublishDate(mock(Date.class));
        auction.setRent(true);
        auction.setStart_bid("Start bid");
        auction.setStart_time("Start time");
        auction.setStatus(true);
        auction.setTitle("Dr");

        Company company = new Company();
        company.setClients(new ArrayList<>());
        company.setEmail("jane.doe@example.org");
        company.setEnable(true);
        company.setFirstName("Jane");
        company.setId(1);
        company.setIs_enable(true);
        company.setLastName("Doe");
        company.setPassword("iloveyou");
        company.setPhoneNumber("6625550144");
        company.setProfile_photo("A\nA\nA\nA\n".getBytes("UTF-8"));
        company.setUserRole(UserRoleEnum.SUPERADMIN);
        company.setVerificationCode("Verification Code");
        company.setVerificationCodeForgetPassword(10);

        Client client2 = new Client();
        client2.setBids(new ArrayList<>());
        client2.setCompany(company);
        client2.setEmail("jane.doe@example.org");
        client2.setEnable(true);
        client2.setFirstName("Jane");
        client2.setId(1);
        client2.setIs_enable(true);
        client2.setLastName("Doe");
        client2.setPassword("iloveyou");
        client2.setPhoneNumber("6625550144");
        client2.setPosts(new ArrayList<>());
        client2.setProfile_photo("A\nA\nA\nA\n".getBytes("UTF-8"));
        client2.setUserRole(UserRoleEnum.SUPERADMIN);
        client2.setVerificationCode("Verification Code");
        client2.setVerificationCodeForgetPassword(10);

        Bid bid = new Bid();
        bid.setAuction(auction);
        bid.setBidPrice(1);
        bid.setClient(client2);
        bid.setId(1);

        ArrayList<Bid> bidList = new ArrayList<>();
        bidList.add(bid);
        when(bidAuctionsRepository.findAllByAuction_Id(anyInt())).thenReturn(bidList);
        ArrayList<BidRetrievalDTO> actualBidsOfAuction = bidService.getBidsOfAuction(1);
        verify(bidAuctionsRepository).findAllByAuction_Id(anyInt());
        assertEquals(1, actualBidsOfAuction.size());
        BidRetrievalDTO getResult = actualBidsOfAuction.get(0);
        assertEquals("jane.doe@example.org", getResult.getClient());
        assertEquals(1, getResult.getBid_price());
        assertEquals(1, getResult.getClient_id());
        assertEquals(1, getResult.getId());
    }
}
