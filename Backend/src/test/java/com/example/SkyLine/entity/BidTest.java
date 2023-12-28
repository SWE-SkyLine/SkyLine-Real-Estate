package com.example.SkyLine.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;

import com.example.SkyLine.enums.EstateTypeEnum;
import com.example.SkyLine.enums.UserRoleEnum;

import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class BidTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Bid#setAuction(Auction)}
     *   <li>{@link Bid#setBidPrice(int)}
     *   <li>{@link Bid#setClient(Client)}
     *   <li>{@link Bid#setId(int)}
     *   <li>{@link Bid#getAuction()}
     *   <li>{@link Bid#getBidPrice()}
     *   <li>{@link Bid#getClient()}
     *   <li>{@link Bid#getId()}
     * </ul>
     */
    @Test
    void testSetAuction() throws UnsupportedEncodingException {
        Bid bid = new Bid();

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
        bid.setAuction(auction);
        bid.setBidPrice(1);
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
        bid.setClient(client2);
        bid.setId(1);
        Auction actualAuction = bid.getAuction();
        int actualBidPrice = bid.getBidPrice();
        Client actualClient = bid.getClient();
        assertEquals(1, actualBidPrice);
        assertEquals(1, bid.getId());
        assertSame(auction, actualAuction);
        assertSame(client2, actualClient);
    }
}
