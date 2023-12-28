package com.example.SkyLine.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class AuctionTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Auction#setBids(List)}
     *   <li>{@link Auction#setEnd_time(String)}
     *   <li>{@link Auction#setStart_bid(String)}
     *   <li>{@link Auction#setStart_time(String)}
     *   <li>{@link Auction#setStatus(boolean)}
     *   <li>{@link Auction#getBids()}
     *   <li>{@link Auction#getEnd_time()}
     *   <li>{@link Auction#getStart_bid()}
     *   <li>{@link Auction#getStart_time()}
     *   <li>{@link Auction#isStatus()}
     * </ul>
     */
    @Test
    void testSetBids() {
        Auction auction = new Auction();
        ArrayList<Bid> bids = new ArrayList<>();
        auction.setBids(bids);
        auction.setEnd_time("End time");
        auction.setStart_bid("Start bid");
        auction.setStart_time("Start time");
        auction.setStatus(true);
        List<Bid> actualBids = auction.getBids();
        String actualEnd_time = auction.getEnd_time();
        String actualStart_bid = auction.getStart_bid();
        String actualStart_time = auction.getStart_time();
        assertEquals("End time", actualEnd_time);
        assertEquals("Start bid", actualStart_bid);
        assertEquals("Start time", actualStart_time);
        assertTrue(auction.isStatus());
        assertSame(bids, actualBids);
    }
}
