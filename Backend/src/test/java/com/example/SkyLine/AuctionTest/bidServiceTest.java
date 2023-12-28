package com.example.SkyLine.AuctionTest;

import com.example.SkyLine.DTO.BidRetrievalDTO;
import com.example.SkyLine.service.BidService;
import com.example.SkyLine.repository.BidAuctionsRepository;
import com.example.SkyLine.repository.ClientRepository;
import com.example.SkyLine.repository.PostRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class bidServiceTest {

    @InjectMocks
    private BidService bidService;

    @Mock
    private BidAuctionsRepository bidAuctionsRepository;

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private PostRepository postRepository;

    @Test
    public void testGetBidsOfAuction_existauction() {
        // Arrange
        int auctionId = 902;

        ArrayList<BidRetrievalDTO> result = bidService.getBidsOfAuction(auctionId);
        assertNotEquals(null, result);
    }
    @Test
    public void testGetBidsOfAuction() {
        // Arrange
        int auctionId = 777777;

        ArrayList<BidRetrievalDTO> result = bidService.getBidsOfAuction(auctionId);
        assertEquals(new ArrayList<>(), result);
    }


    @Test
    public void testAddBid() {
        // Arrange
        int clientId = 7;
        int bidPrice = 80000;
        int auctionId = 902;


        // Act
        boolean result = bidService.addBid(clientId, bidPrice, auctionId);
        assertTrue(result);
    }



    // Add more test methods as needed for other functionalities in BidService
}
