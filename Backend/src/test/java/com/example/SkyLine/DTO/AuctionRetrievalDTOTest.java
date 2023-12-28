package com.example.SkyLine.DTO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import com.example.SkyLine.entity.Auction;
import com.example.SkyLine.entity.Bid;
import com.example.SkyLine.entity.Photo;
import com.example.SkyLine.entity.User;
import com.example.SkyLine.enums.EstateTypeEnum;
import com.example.SkyLine.enums.UserRoleEnum;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.sql.Date;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class AuctionRetrievalDTOTest {
    /**
     * Method under test: {@link AuctionRetrievalDTO#AuctionRetrievalDTO(Auction)}
     */
    @Test
    void testConstructor() throws UnsupportedEncodingException, MalformedURLException {
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
        ArrayList<Bid> bids = new ArrayList<>();
        auction.setBids(bids);
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
        AuctionRetrievalDTO actualAuctionRetrievalDTO = new AuctionRetrievalDTO(auction);
        assertEquals("42 Main St", actualAuctionRetrievalDTO.getAddress());
        assertEquals("Dr", actualAuctionRetrievalDTO.getTitle());
        assertEquals("End time", actualAuctionRetrievalDTO.getEnd_time());
        assertEquals("Jane Doe", actualAuctionRetrievalDTO.getFullName());
        assertEquals("Map Link", actualAuctionRetrievalDTO.getMapLink());
        assertEquals("Oxford", actualAuctionRetrievalDTO.getCity());
        assertEquals("Start bid", actualAuctionRetrievalDTO.getStart_bid());
        assertEquals("The characteristics of someone or something", actualAuctionRetrievalDTO.getDescription());
        assertEquals("auction", actualAuctionRetrievalDTO.getPost_type());
        assertEquals(1, actualAuctionRetrievalDTO.getId());
        assertEquals(1, actualAuctionRetrievalDTO.getPostCreatorUID());
        assertEquals(1, actualAuctionRetrievalDTO.getPrice());
        assertEquals((short) 1, actualAuctionRetrievalDTO.getArea());
        assertEquals(EstateTypeEnum.APARTMENT, actualAuctionRetrievalDTO.getEstateType());
        assertTrue(actualAuctionRetrievalDTO.isRent());
        assertEquals(bids, actualAuctionRetrievalDTO.getPhotosByteArray());
        assertEquals('A', actualAuctionRetrievalDTO.getBathroom());
        assertEquals('A', actualAuctionRetrievalDTO.getBedroom());
        assertEquals('A', actualAuctionRetrievalDTO.getLevel());
        Date expectedExpiryDate = auction.expiryDate;
        assertSame(expectedExpiryDate, actualAuctionRetrievalDTO.getExpiryDate());
        Date expectedPublishDate = auction.publishDate;
        assertSame(expectedPublishDate, actualAuctionRetrievalDTO.getPublishDate());
    }

    /**
     * Method under test: {@link AuctionRetrievalDTO#AuctionRetrievalDTO(Auction)}
     */
    @Test
    void testConstructor2() throws UnsupportedEncodingException, MalformedURLException {
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

        Photo photo = new Photo();
        photo.setId(1);

        ArrayList<Photo> photos = new ArrayList<>();
        photos.add(photo);

        Auction auction = new Auction();
        auction.setAddress("42 Main St");
        auction.setArea((short) 1);
        auction.setBathroom((byte) 'A');
        auction.setBedroom((byte) 'A');
        ArrayList<Bid> bids = new ArrayList<>();
        auction.setBids(bids);
        auction.setCity("Oxford");
        auction.setClient(client);
        auction.setDescription("The characteristics of someone or something");
        auction.setEnd_time("End time");
        auction.setEstateType(EstateTypeEnum.APARTMENT);
        auction.setExpiryDate(mock(Date.class));
        auction.setId(1);
        auction.setLevel((byte) 'A');
        auction.setMapLink("Map Link");
        auction.setPrice(1);
        auction.setPublishDate(mock(Date.class));
        auction.setRent(true);
        auction.setStart_bid("Start bid");
        auction.setStart_time("Start time");
        auction.setStatus(true);
        auction.setTitle("Dr");
        auction.setPhotos(photos);
        AuctionRetrievalDTO actualAuctionRetrievalDTO = new AuctionRetrievalDTO(auction);
        assertEquals("42 Main St", actualAuctionRetrievalDTO.getAddress());
        assertEquals("Dr", actualAuctionRetrievalDTO.getTitle());
        assertEquals("End time", actualAuctionRetrievalDTO.getEnd_time());
        assertEquals("Jane Doe", actualAuctionRetrievalDTO.getFullName());
        assertEquals("Map Link", actualAuctionRetrievalDTO.getMapLink());
        assertEquals("Oxford", actualAuctionRetrievalDTO.getCity());
        assertEquals("Start bid", actualAuctionRetrievalDTO.getStart_bid());
        assertEquals("The characteristics of someone or something", actualAuctionRetrievalDTO.getDescription());
        assertEquals("auction", actualAuctionRetrievalDTO.getPost_type());
        assertEquals(1, actualAuctionRetrievalDTO.getId());
        assertEquals(1, actualAuctionRetrievalDTO.getPostCreatorUID());
        assertEquals(1, actualAuctionRetrievalDTO.getPrice());
        assertEquals((short) 1, actualAuctionRetrievalDTO.getArea());
        assertEquals(EstateTypeEnum.APARTMENT, actualAuctionRetrievalDTO.getEstateType());
        assertTrue(actualAuctionRetrievalDTO.isRent());
        assertEquals(bids, actualAuctionRetrievalDTO.getPhotosByteArray());
        assertEquals('A', actualAuctionRetrievalDTO.getBathroom());
        assertEquals('A', actualAuctionRetrievalDTO.getBedroom());
        assertEquals('A', actualAuctionRetrievalDTO.getLevel());
        Date expectedExpiryDate = auction.expiryDate;
        assertSame(expectedExpiryDate, actualAuctionRetrievalDTO.getExpiryDate());
        Date expectedPublishDate = auction.publishDate;
        assertSame(expectedPublishDate, actualAuctionRetrievalDTO.getPublishDate());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link AuctionRetrievalDTO#getAddress()}
     *   <li>{@link AuctionRetrievalDTO#getArea()}
     *   <li>{@link AuctionRetrievalDTO#getBathroom()}
     *   <li>{@link AuctionRetrievalDTO#getBedroom()}
     *   <li>{@link AuctionRetrievalDTO#getCity()}
     *   <li>{@link AuctionRetrievalDTO#getDescription()}
     *   <li>{@link AuctionRetrievalDTO#getEnd_time()}
     *   <li>{@link AuctionRetrievalDTO#getEstateType()}
     *   <li>{@link AuctionRetrievalDTO#getExpiryDate()}
     *   <li>{@link AuctionRetrievalDTO#getFullName()}
     *   <li>{@link AuctionRetrievalDTO#getId()}
     *   <li>{@link AuctionRetrievalDTO#getLevel()}
     *   <li>{@link AuctionRetrievalDTO#getMapLink()}
     *   <li>{@link AuctionRetrievalDTO#getPostCreatorUID()}
     *   <li>{@link AuctionRetrievalDTO#getPrice()}
     *   <li>{@link AuctionRetrievalDTO#getPublishDate()}
     *   <li>{@link AuctionRetrievalDTO#getStart_bid()}
     *   <li>{@link AuctionRetrievalDTO#getTitle()}
     *   <li>{@link AuctionRetrievalDTO#isRent()}
     * </ul>
     */
    @Test
    void testGetAddress() throws UnsupportedEncodingException, MalformedURLException {
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
        AuctionRetrievalDTO auctionRetrievalDTO = new AuctionRetrievalDTO(auction);
        String actualAddress = auctionRetrievalDTO.getAddress();
        short actualArea = auctionRetrievalDTO.getArea();
        byte actualBathroom = auctionRetrievalDTO.getBathroom();
        byte actualBedroom = auctionRetrievalDTO.getBedroom();
        String actualCity = auctionRetrievalDTO.getCity();
        String actualDescription = auctionRetrievalDTO.getDescription();
        String actualEnd_time = auctionRetrievalDTO.getEnd_time();
        EstateTypeEnum actualEstateType = auctionRetrievalDTO.getEstateType();
        Date actualExpiryDate = auctionRetrievalDTO.getExpiryDate();
        String actualFullName = auctionRetrievalDTO.getFullName();
        int actualId = auctionRetrievalDTO.getId();
        byte actualLevel = auctionRetrievalDTO.getLevel();
        String actualMapLink = auctionRetrievalDTO.getMapLink();
        int actualPostCreatorUID = auctionRetrievalDTO.getPostCreatorUID();
        int actualPrice = auctionRetrievalDTO.getPrice();
        Date actualPublishDate = auctionRetrievalDTO.getPublishDate();
        String actualStart_bid = auctionRetrievalDTO.getStart_bid();
        String actualTitle = auctionRetrievalDTO.getTitle();
        assertEquals("42 Main St", actualAddress);
        assertEquals("Dr", actualTitle);
        assertEquals("End time", actualEnd_time);
        assertEquals("Jane Doe", actualFullName);
        assertEquals("Map Link", actualMapLink);
        assertEquals("Oxford", actualCity);
        assertEquals("Start bid", actualStart_bid);
        assertEquals("The characteristics of someone or something", actualDescription);
        assertEquals(1, actualId);
        assertEquals(1, actualPostCreatorUID);
        assertEquals(1, actualPrice);
        assertEquals((short) 1, actualArea);
        assertEquals(EstateTypeEnum.APARTMENT, actualEstateType);
        assertTrue(auctionRetrievalDTO.isRent());
        assertEquals('A', actualBathroom);
        assertEquals('A', actualBedroom);
        assertEquals('A', actualLevel);
        assertSame(auction.expiryDate, actualExpiryDate);
        assertSame(auction.publishDate, actualPublishDate);
    }
}
