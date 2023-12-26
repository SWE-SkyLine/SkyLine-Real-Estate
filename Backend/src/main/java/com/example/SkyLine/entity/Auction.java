package com.example.SkyLine.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Setter
@Getter
@DiscriminatorColumn(name = "postType", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue(value = "auction")
public class Auction extends Post{
    private Date start_time;
    private Date end_time;
    private boolean status;
    @OneToMany(mappedBy = "auction")
    private List<Bid> bids = new ArrayList<>();
}
