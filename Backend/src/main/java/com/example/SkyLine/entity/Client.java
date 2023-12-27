package com.example.SkyLine.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@DiscriminatorValue(value = "Client")
public class Client extends User {

    //    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name="client_id")
//    private List<Post> posts = new ArrayList<Post>();
    @OneToMany(mappedBy = "client",cascade = CascadeType.ALL)
    private List<Post> posts = new ArrayList<Post>();

    @OneToMany(mappedBy = "client")
    private List<Bid> bids = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;


}
