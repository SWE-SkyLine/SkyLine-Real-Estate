package com.example.SkyLine.entity;

import com.example.SkyLine.enums.EstateTypeEnum;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@Accessors(chain = true)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "postType", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue(value = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    public Date publishDate;
    public Date expiryDate;

    private String title;
    private int price;
    private Boolean rent;
    private short area;
    private String description;

    @Enumerated(value = EnumType.STRING)
    private EstateTypeEnum estateType;
    private byte bedroom;
    private byte bathroom;
    private byte level;

    @Column(name = "map_link")
    private String mapLink;
    private String address;
    private String city;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "post_id")
    private List<Photo> photos = new ArrayList<Photo>();

    @ManyToOne
    @JoinColumn(name = "client_id")
    private User client;

}
