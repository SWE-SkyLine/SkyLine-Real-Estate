package com.example.SkyLine.service;

import com.example.SkyLine.DTO.BidDTO;
import com.example.SkyLine.DTO.PostRetrievalDTO;
import com.example.SkyLine.entity.Bid;
import com.example.SkyLine.entity.Post;
import com.example.SkyLine.repository.BidAuctionsRepository;
import com.example.SkyLine.repository.ClientRepository;
import com.example.SkyLine.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class BidService {

    @Autowired
    private BidAuctionsRepository bidAuctionsRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private PostRepository postRepository;

    public boolean addBid(BidDTO bid) {
        try {
            // Save the bid to the database
            System.out.println(bid.toString());
            Bid newbid=new Bid();
            newbid.setClient(clientRepository.findById(bid.getClient_id()));
            newbid.setBidPrice(bid.getBid_price());
            newbid.setAuction(postRepository.findById(bid.getAuction_id()));
            System.out.println(newbid.getBidPrice());
            bidAuctionsRepository.save(newbid);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

//    public List<Bid> getBids(int Auction_id){
//        List<Bid> Bids= bidAuctionsRepository.findAllByAuction_Id(Auction_id);
//        return Bids;
//    }

//    public List<BidDTO> bidsToRetrievalEntity(List<Bid> bids) throws MalformedURLException {
//        List<BidDTO> retrievalDTOS = new ArrayList<>();
//        for (Bid b : bids) {
//            retrievalDTOS.add(new BidDTO(b));
//        }
//        return retrievalDTOS;
//    }

}