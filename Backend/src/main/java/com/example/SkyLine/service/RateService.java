package com.example.SkyLine.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SkyLine.DTO.RateDTO;
import com.example.SkyLine.entity.Rating;
import com.example.SkyLine.entity.RatingKey;
import com.example.SkyLine.repository.RatingRepository;

@Service
public class RateService {
    @Autowired
    RatingRepository ratingRepository;

    public void rate(RateDTO rating){

        // adjust user and rated user as user and target
        RatingKey key = new RatingKey();
        key.setTargetId(rating.getTargetId());
        key.setUserId(rating.getUserId());

        // convert DTO object into database entity object
        Rating toSave = new Rating();
        toSave.setId(key);
        toSave.setRate(rating.getRate().getValue());

        // save to database
        ratingRepository.save(toSave);
    }

    public double getAvRate(int targetId){
        return ratingRepository.findAverageRatingByTargetId(targetId);
    }

    public int getRate(int userId, int targetId){
        return ratingRepository.findByIdUserIdAndIdTargetId(userId, targetId).getRate();
    }

}
