package com.example.SkyLine.service;

import com.example.SkyLine.DTO.BidDTO;
import com.example.SkyLine.DTO.BidRetrievalDTO;
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
import java.util.Comparator;
import java.util.List;

@Service
public class BidService {

    @Autowired
    private BidAuctionsRepository bidAuctionsRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private PostRepository postRepository;

    public boolean addBid(int client_id,int bid_price,int auction_id) {
        try {
            // Save the bid to the database
            Bid newbid=new Bid();
            newbid.setClient(clientRepository.findById(client_id));
            newbid.setBidPrice(bid_price);
            newbid.setAuction(postRepository.findById(auction_id));
            System.out.println(newbid.getBidPrice());
            bidAuctionsRepository.save(newbid);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    public ArrayList<BidRetrievalDTO> getBidsOfAuction(int Auction_id){

        try {
            ArrayList<Bid> bids = bidAuctionsRepository.findAllByAuction_Id(Auction_id);
            Comparator<Bid> bidComparator = Comparator.comparing(Bid::getBidPrice, Comparator.reverseOrder());
            bids.sort(bidComparator);
            List<Bid> sorted_bids = bids.subList(0, Math.min(bids.size(), 10));
            ArrayList<BidRetrievalDTO> b=new ArrayList<>() ;

            for (Bid bid : sorted_bids) {
                b.add(new BidRetrievalDTO(bid));
            }
            return b;

        }
        catch (Exception e){
            return null;
        }

    }

//    public List<BidDTO> bidsToRetrievalEntity(List<Bid> bids) throws MalformedURLException {
//        List<BidDTO> retrievalDTOS = new ArrayList<>();
//        for (Bid b : bids) {
//            retrievalDTOS.add(new BidDTO(b));
//        }
//        return retrievalDTOS;
//    }

}