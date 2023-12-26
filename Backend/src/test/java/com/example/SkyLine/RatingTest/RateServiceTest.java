package com.example.SkyLine.RatingTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.example.SkyLine.DTO.RateDTO;
import com.example.SkyLine.entity.Rating;
import com.example.SkyLine.entity.RatingKey;
import com.example.SkyLine.enums.RatingEnum;
import com.example.SkyLine.repository.RatingRepository;
import com.example.SkyLine.service.RateService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class RateServiceTest {

    @Mock
    private RatingRepository ratingRepository;

    @InjectMocks
    private RateService rateService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRate() {
        RateDTO rateDTO = new RateDTO();
        rateDTO.setUserId(1);
        rateDTO.setTargetId(2);
        rateDTO.setRate(RatingEnum.FIVE);

        RatingKey key = new RatingKey();
        key.setTargetId(rateDTO.getTargetId());
        key.setUserId(rateDTO.getUserId());

        Rating expectedRating = new Rating();
        expectedRating.setId(key);
        expectedRating.setRate(rateDTO.getRate().getValue());

        when(ratingRepository.save(any())).thenReturn(expectedRating);

        rateService.rate(rateDTO);

        verify(ratingRepository, times(1)).save(any());
    }

    @Test
    public void testGetAvRate() {
        int targetId = 2;
        double expectedAverage = 3.5;

        when(ratingRepository.findAverageRatingByTargetId(targetId)).thenReturn(expectedAverage);

        double actualAverage = rateService.getAvRate(targetId);

        verify(ratingRepository, times(1)).findAverageRatingByTargetId(targetId);

        assertEquals(expectedAverage, actualAverage, 0.001);
    }

    @Test
    public void testGetRate() {
        int userId = 1;
        int targetId = 2;
        byte expectedRate = 4;

        Rating rating = new Rating();
        rating.setRate(expectedRate);

        when(ratingRepository.findByIdUserIdAndIdTargetId(userId, targetId)).thenReturn(rating);

        byte actualRate = rateService.getRate(userId, targetId);

        verify(ratingRepository, times(1)).findByIdUserIdAndIdTargetId(userId, targetId);

        assertEquals(expectedRate, actualRate);
    }
}
