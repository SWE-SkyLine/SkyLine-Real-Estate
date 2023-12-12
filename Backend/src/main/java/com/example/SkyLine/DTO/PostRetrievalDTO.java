package com.example.SkyLine.DTO;

import com.example.SkyLine.entity.Photo;
import com.example.SkyLine.entity.Post;
import com.example.SkyLine.enums.EstateTypeEnum;
import jakarta.persistence.*;

import lombok.Getter;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.util.StreamUtils;

import java.io.File;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Getter
public class PostRetrievalDTO {
    private int id;
    private Date publishDate;
    private Date expiryDate;
    private String title;
    private int price;
    private boolean rent;
    private short area;
    private String description;
    private EstateTypeEnum estateType;
    private byte bedroom;
    private byte bathroom;
    private byte level;
    private String mapLink;
    private String address;
    private String city;
    public List<byte[]> photosByteArray = new ArrayList<>();

    public PostRetrievalDTO(Post post) throws MalformedURLException {
        this.id = post.getId();
        this.publishDate = post.getPublishDate();
        this.expiryDate = post.getExpiryDate();
        this.title = post.getTitle();
        this.price = post.getPrice();
        this.rent = post.isRent();
        this.area = post.getArea();
        this.description = post.getDescription();
        this.estateType = post.getEstateType();
        this.bedroom = post.getBedroom();
        this.bathroom = post.getBathroom();
        this.level = post.getLevel();
        this.mapLink = post.getMapLink();
        this.address = post.getAddress();
        this.city = post.getCity();

        try{
            for (Photo photo : post.getPhotos()) {
                Resource resource = new UrlResource(Paths.get(photo.getPostPhotoURL()).toUri());
                photosByteArray.add(StreamUtils.copyToByteArray(resource.getInputStream()));
            }
        }
        catch (Exception e){
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

    public boolean isRent() {
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


}