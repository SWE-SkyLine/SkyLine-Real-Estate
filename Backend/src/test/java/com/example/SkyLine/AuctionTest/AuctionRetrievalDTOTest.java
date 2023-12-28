package com.example.SkyLine.AuctionTest;

import com.example.SkyLine.DTO.AuctionRetrievalDTO;
import com.example.SkyLine.entity.Auction;
import com.example.SkyLine.entity.Bid;
import com.example.SkyLine.entity.Client;
import com.example.SkyLine.enums.EstateTypeEnum;
import org.junit.jupiter.api.Test;
import java.net.MalformedURLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AuctionRetrievalDTOTest {

    @Test
    public void testAuction() {
        Auction auction = createSampleAuction();

        assertEquals(1, auction.getId());
        assertEquals("Sample Auction", auction.getTitle());
        // Add more assertions for other properties of the Auction class
    }

    @Test
    public void testBid() {
        Bid bid = createSampleBid();

        assertEquals(1, bid.getId());
        // Add more assertions for other properties of the Bid class
    }

    @Test
    public void testAuctionRetrievalDTO() throws MalformedURLException {
        Auction auction = createSampleAuction();
        AuctionRetrievalDTO auctionDTO = new AuctionRetrievalDTO(auction);

        assertEquals(1, auctionDTO.getId());
        assertEquals("Sample Auction", auctionDTO.getTitle());
        // Add more assertions for other properties of the AuctionRetrievalDTO class

        // Add assertions for photosByteArray if applicable
        assertTrue(auctionDTO.getPhotosByteArray().isEmpty());
    }

    private Auction createSampleAuction() {
        Auction auction = new Auction();
        auction.setId(1);
        auction.setPublishDate(new Date(System.currentTimeMillis()));
        auction.setExpiryDate(new Date(System.currentTimeMillis() + 86400000L));
        auction.setTitle("Sample Auction");
        auction.setPrice(1000);
        auction.setRent(false);
        auction.setArea((short) 200);
        auction.setDescription("Sample Description");
        auction.setEstateType(EstateTypeEnum.APARTMENT);
        auction.setBedroom((byte) 2);
        auction.setBathroom((byte) 1);
        auction.setLevel((byte) 3);
        auction.setMapLink("https://example.com/map");
        auction.setAddress("123 Main Street");
        auction.setCity("Cityville");

        Client client = new Client();
        client.setId(1001);
        client.setFirstName("John");
        client.setLastName("Doe");
        auction.setClient(client);

        auction.setStart_bid("500");
        auction.setEnd_time("2023-12-31 23:59:59");

        // Add any necessary setup for photos, if applicable

        return auction;
    }

    private Bid createSampleBid() {
        Bid bid = new Bid();
        bid.setId(1);
        // Set other properties of the Bid class
        return bid;
    }

    // Auction Tests

    @Test
    public void testAuctionIdGetterAndSetter() {
        Auction auction = new Auction();
        auction.setId(1);
        assertEquals(1, auction.getId());
    }

    @Test
    public void testAuctionTitleGetterAndSetter() {
        Auction auction = new Auction();
        auction.setTitle("Sample Auction");
        assertEquals("Sample Auction", auction.getTitle());
    }

    @Test
    public void testAuctionPriceGetterAndSetter() {
        Auction auction = new Auction();
        auction.setPrice(1000);
        assertEquals(1000, auction.getPrice());
    }

    // Add similar test methods for other properties

    // Bid Tests

    @Test
    public void testBidIdGetterAndSetter() {
        Bid bid = new Bid();
        bid.setId(1);
        assertEquals(1, bid.getId());
    }

    // Add similar test methods for other properties in Bid class

    // AuctionRetrievalDTO Tests

    @Test
    public void testAuctionRetrievalDTOIdGetterAndSetter() throws MalformedURLException {
        Auction auction = createSampleAuction();
        AuctionRetrievalDTO auctionDTO = new AuctionRetrievalDTO(auction);

        assertEquals(1, auctionDTO.getId());
    }

    @Test
    public void testAuctionRetrievalDTOTitleGetterAndSetter() throws MalformedURLException {
        Auction auction = createSampleAuction();
        AuctionRetrievalDTO auctionDTO = new AuctionRetrievalDTO(auction);

        assertEquals("Sample Auction", auctionDTO.getTitle());
    }


}
