package com.example.SkyLine.utility;

import com.example.SkyLine.entity.Auction;
import com.example.SkyLine.entity.Post;
import com.example.SkyLine.enums.EstateTypeEnum;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.sql.Date;

public class ContollerDataToPostAdapter {
    public static Post contollerDataToPost(String title,
                                           String price,
                                           String isRent,
                                           String area,
                                           String description,
                                           String estateType,
                                           String mapLink,
                                           String address,
                                           String city,
                                           String bedroom,
                                           String bathroom,
                                           String level
                                           ) {
        // create entity without photoes due to concurrency problems if I fetch the coming id of the post
        Post post = new Post()
                .setPublishDate(Date.valueOf(LocalDate.now()))
                .setExpiryDate(Date.valueOf(LocalDate.now().plusMonths(2)))
                .setTitle(title)
                .setPrice(Integer.parseInt(price))
                .setRent(Boolean.parseBoolean(isRent))
                .setArea((short) Integer.parseInt(area))
                .setDescription(description)
                .setEstateType(EstateTypeEnum.valueOf(estateType))
                .setMapLink(mapLink)
                .setAddress(address)
                .setCity(city)
                .setBedroom((byte) Integer.parseInt(bedroom))
                .setBathroom((byte) Integer.parseInt(bathroom))
                .setLevel((byte) Integer.parseInt(level));

        return post;
    }
    public static Auction contollerDataToAuction(String title,
                                                 String price,
                                                 String isRent,
                                                 String area,
                                                 String description,
                                                 String estateType,
                                                 String mapLink,
                                                 String address,
                                                 String city,
                                                 String bedroom,
                                                 String bathroom,
                                                 String level,
                                                 String end_time,
                                                 String start_time

    ) {
        // create entity without photoes due to concurrency problems if I fetch the coming id of the post
        Auction auction = new Auction();
        auction.setPublishDate(Date.valueOf(LocalDate.now()));
        auction.setExpiryDate(Date.valueOf(LocalDate.now().plusMonths(2)));
        auction.setTitle(title);
        auction.setPrice(Integer.parseInt(price));
        auction.setRent(Boolean.parseBoolean(isRent));
        auction.setArea((short) Integer.parseInt(area));
        auction.setDescription(description);
        auction.setEstateType(EstateTypeEnum.valueOf(estateType));
        auction.setMapLink(mapLink);
        auction.setAddress(address);
        auction.setCity(city);
        auction.setBedroom((byte) Integer.parseInt(bedroom));
        auction.setBathroom((byte) Integer.parseInt(bathroom));
        auction.setLevel((byte) Integer.parseInt(level));
        auction.setStart_time(start_time);
        auction.setEnd_time(end_time);
        auction.setStatus(true);

        return auction ;
    }
}
