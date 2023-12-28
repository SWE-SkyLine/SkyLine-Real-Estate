package com.example.SkyLine.AuctionTest;

import com.example.SkyLine.entity.Auction;
import com.example.SkyLine.entity.Bid;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AuctionTest {

    @Test
    public void testAuctionGetterAndSetter() {
        // Create an instance of Auction
        Auction auction = new Auction();

        // Set values using the setters
        auction.setStart_time("2023-01-01 10:00:00");
        auction.setEnd_time("2023-01-02 10:00:00");
        auction.setStatus(true);
        auction.setStart_bid("100");

        // Check if values can be retrieved using getters
        assertEquals("2023-01-01 10:00:00", auction.getStart_time());
        assertEquals("2023-01-02 10:00:00", auction.getEnd_time());
        assertTrue(auction.isStatus());
        assertEquals("100", auction.getStart_bid());
    }

    @Test
    public void testAuctionBids() {
        // Create an instance of Auction
        Auction auction = new Auction();

        // Create a Bid instance
        Bid bid = new Bid();
        bid.setBidPrice(150);

        // Add the bid to the auction
        auction.getBids().add(bid);

        // Check if the bid is present in the auction's bid list
        assertTrue(auction.getBids().contains(bid));
        assertEquals(1, auction.getBids().size());
    }
}
