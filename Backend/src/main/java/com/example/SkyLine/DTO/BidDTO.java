package com.example.SkyLine.DTO;

import com.example.SkyLine.entity.Bid;
import com.example.SkyLine.repository.UserRepository;
import lombok.Data;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

@Data
public class BidDTO {

    private int client_id;
    private int id;
    private int bid_price;
    private int auction_id;


    @Override
    public String toString() {
        return "BidDTO{" +
                "client_id=" + client_id +
                ", id=" + id +
                ", bid_price=" + bid_price +
                ", auction_id=" + auction_id +
                '}';
    }

    public BidDTO(Bid bid) {
        this.id = bid.getId();
        this.bid_price=bid.getBidPrice();
    }
    public BidDTO() {
    }

    public int getClient_id() {
        return client_id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBid_price() {
        return bid_price;
    }

    public void setBid_price(int bid_price) {
        this.bid_price = bid_price;
    }

    public int getAuction_id() {
        return auction_id;
    }

    public void setAuction_id(int auction_id) {
        this.auction_id = auction_id;
    }




}