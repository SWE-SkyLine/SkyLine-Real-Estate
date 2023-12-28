package com.example.SkyLine.DTO;

import com.example.SkyLine.entity.Bid;
import lombok.Data;

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
        this.client=bid.getClient().getEmail();
        this.client_id=bid.getClient().getId();
    }

    @Override
    public String toString() {
        return "BidRetrievalDTO{" +
                "id=" + id +
                ", bid_price=" + bid_price +
                ", auction_id=" + auction_id +
                ", client='" + client + '\'' +
                ", client_id=" + client_id +
                '}';
    }
}
