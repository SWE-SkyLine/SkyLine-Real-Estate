package com.example.SkyLine.repository;

import com.example.SkyLine.DTO.BidDTO;
import com.example.SkyLine.entity.Bid;
import com.example.SkyLine.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.List;

public interface BidAuctionsRepository extends JpaRepository<Bid, Integer> {

    ArrayList<Bid> findAllByAuction_Id(int auctionId);

}
