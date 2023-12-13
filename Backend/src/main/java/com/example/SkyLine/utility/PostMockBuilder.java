package com.example.SkyLine.entity;

import com.example.SkyLine.enums.EstateTypeEnum;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class PostMockBuilder {

    public static Post buildMockPost() {
        return new Post()
                .setId(1)
                .setPublishDate(new Date(System.currentTimeMillis()))
                .setExpiryDate(new Date(System.currentTimeMillis() + 86400000)) // Adding one day for expiry
                .setTitle("Mock Post")
                .setPrice(1000)
                .setRent(true)
                .setArea((short) 150)
                .setDescription("Mock description")
                .setEstateType(EstateTypeEnum.APARTMENT)
                .setBedroom((byte) 2)
                .setBathroom((byte) 1)
                .setLevel((byte) 2)
                .setMapLink("Mock map link")
                .setAddress("Mock Address")
                .setCity("Mock City")
                .setPhotos(buildMockPhotos());
    }

    private static List<Photo> buildMockPhotos() {
        List<Photo> photos = new ArrayList<>();
        // Add logic to create mock Photo objects if needed
        return photos;
    }
}
