package com.example.SkyLine.DTO;

import com.example.SkyLine.entity.Bid;
import com.example.SkyLine.repository.UserRepository;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@Data
public class BidRetrievalDTO {
    private int id;
    private int bid_price;
    private int auction_id;
    private  String client;

    private  int client_id;


    public BidRetrievalDTO(Bid bid) {
        this.id = bid.getId();
        this.bid_price=bid.getBidPrice();
        this.auction_id=bid.getAuction().getId();
        this.client=bid.getClient().getEmail();
        this.client_id=bid.getClient().getId();
    }
}
