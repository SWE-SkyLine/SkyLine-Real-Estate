package com.example.SkyLine.AuctionTest;

import com.example.SkyLine.entity.Auction;
import com.example.SkyLine.entity.Bid;
import com.example.SkyLine.entity.Client;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BidTest {

    @Test
    public void testBidGetterAndSetter() {
        // Create an instance of Bid
        Bid bid = new Bid();

        // Set values using the setters
        bid.setBidPrice(150);

        // Check if values can be retrieved using getters
        assertEquals(150, bid.getBidPrice());
    }

    @Test
    public void testBidRelationships() {
        // Create instances of Bid, Client, and Auction
        Bid bid = new Bid();
        Client client = new Client();
        Auction auction = new Auction();

        // Set up the relationships
        bid.setClient(client);
        bid.setAuction(auction);

        // Check if relationships are set correctly
        assertEquals(client, bid.getClient());
        assertEquals(auction, bid.getAuction());
    }
}
