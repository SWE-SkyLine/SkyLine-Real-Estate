package com.example.SkyLine.AuctionTest;

import com.example.SkyLine.DTO.BidDTO;
import com.example.SkyLine.entity.Bid;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class BidDTOTest {

    @Test
    public void testBidDTOConstructor() {
        Bid bid = createSampleBid();
        BidDTO bidDTO = new BidDTO(bid);

        assertNotNull(bidDTO);
        assertEquals(bid.getId(), bidDTO.getId());
        assertEquals(bid.getBidPrice(), bidDTO.getBid_price());
    }

    @Test
    public void testBidDTOGettersAndSetters() {
        BidDTO bidDTO = new BidDTO();

        // Set values using setters
        bidDTO.setId(1);
        bidDTO.setBid_price(500);

        // Perform assertions using getters
        assertEquals(1, bidDTO.getId());
        assertEquals(500, bidDTO.getBid_price());
    }

    // Additional tests for other getters and setters if needed

    private Bid createSampleBid() {
        Bid bid = new Bid();
        bid.setId(1);
        bid.setBidPrice(500);
        // Set other properties for Bid class
        return bid;
    }
}
