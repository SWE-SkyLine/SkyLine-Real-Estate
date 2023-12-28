package com.example.SkyLine.DTO;

import com.example.SkyLine.entity.Auction;
import com.example.SkyLine.entity.Bid;
import com.example.SkyLine.entity.Photo;
import com.example.SkyLine.enums.EstateTypeEnum;
import lombok.Getter;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.util.StreamUtils;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
@Getter

public class AuctionRetrievalDTO {
    final private int id;
    final  private Date publishDate;
    final private Date expiryDate;
    final  private String title;
    final private int price;
    final  private Boolean rent;
    final private short area;
    final private String description;
    final  private EstateTypeEnum estateType;
    final  private byte bedroom;
    final private byte bathroom;
    final private byte level;
    final private String mapLink;
    final  private String address;
    final  private String city;
    final private String fullName;
    final private int postCreatorUID;
    final private String start_bid;
    final private String end_time;
    final private String post_type;

    public List<byte[]> photosByteArray = new ArrayList<>();


    public AuctionRetrievalDTO(Auction auction) throws MalformedURLException {
        this.id = auction.getId();
        this.publishDate = auction.getPublishDate();
        this.expiryDate = auction.getExpiryDate();
        this.title = auction.getTitle();
        this.price = auction.getPrice();
        this.rent = auction.getRent();
        this.area = auction.getArea();
        this.description = auction.getDescription();
        this.estateType = auction.getEstateType();
        this.bedroom = auction.getBedroom();
        this.bathroom = auction.getBathroom();
        this.level = auction.getLevel();
        this.mapLink = auction.getMapLink();
        this.address = auction.getAddress();
        this.city = auction.getCity();
        this.postCreatorUID = auction.getClient().getId();
        this.start_bid= auction.getStart_bid();
        this.end_time= auction.getEnd_time();
        this.fullName = auction.getClient().getFirstName() + " " + auction.getClient().getLastName();
        this.post_type="auction";

        try {
            for (Photo photo : auction.getPhotos()) {
                Path filePath = Paths.get(photo.getPostPhotoURL()).toAbsolutePath();
                Resource resource = new UrlResource(Paths.get(photo.getPostPhotoURL()).toUri());
                photosByteArray.add(StreamUtils.copyToByteArray(resource.getInputStream()));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage() + "I AM IN DTO");
        }


    }
    public int getId() {
        return id;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public String getTitle() {
        return title;
    }

    public int getPrice() {
        return price;
    }

    public Boolean isRent() {
        return rent;
    }

    public short getArea() {
        return area;
    }

    public String getDescription() {
        return description;
    }

    public EstateTypeEnum getEstateType() {
        return estateType;
    }

    public byte getBedroom() {
        return bedroom;
    }

    public byte getBathroom() {
        return bathroom;
    }

    public byte getLevel() {
        return level;
    }

    public String getMapLink() {
        return mapLink;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getFullName() {
        return fullName;
    }


    public String getStart_bid() {
        return start_bid;
    }

    public int getPostCreatorUID() {
        return postCreatorUID;
    }

    public String getEnd_time() {
        return end_time;
    }
}
