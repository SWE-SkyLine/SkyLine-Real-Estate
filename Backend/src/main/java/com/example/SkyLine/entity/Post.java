package com.example.SkyLine.entity;

import com.example.SkyLine.enums.EstateTypeEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Entity
@Setter
@Getter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "postType", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue(value = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String title;
    private int price;
    private boolean rent;

    private short area;

    private String description;

    @Enumerated(value=EnumType.STRING)
    private EstateTypeEnum estateTypeEnum;

    @Column(name="map_link")
    private String mapLink;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="post_id")
private List<Photo> photos = new ArrayList<Photo>();

}
