package com.example.SkyLine.RatingTest;

import com.example.SkyLine.DTO.RateDTO;
import com.example.SkyLine.controller.RatingController;
import com.example.SkyLine.enums.RatingEnum;
import com.example.SkyLine.service.RateService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RatingControllerTest {

    @Mock
    private RateService rateService;

    @InjectMocks
    private RatingController ratingController;

    @Test
    void testSuccessfulRating() {
        // Arrange
        RateDTO rateDTO = new RateDTO();
        rateDTO.setTargetId(1);
        rateDTO.setUserId(3);
        rateDTO.setRate(RatingEnum.ONE);
        doNothing().when(rateService).rate(rateDTO);

        // Act
        ResponseEntity<?> responseEntity = ratingController.rateUser(rateDTO);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        // assertEquals("Ticket sent successfully", responseEntity.getBody());
        verify(rateService, times(1)).rate(rateDTO);
    }

    @Test
    void testFailRating() {
        // Arrange
        RateDTO rateDTO = new RateDTO();
        doThrow(new RuntimeException("Test exception")).when(rateService).rate(rateDTO);

        // Act
        ResponseEntity<?> responseEntity = ratingController.rateUser(rateDTO);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        // assertEquals("Ticket sent successfully", responseEntity.getBody());
        verify(rateService, times(1)).rate(rateDTO);
    }

    @Test
    void testSuccessfulGetAvg() {
        doReturn(4.5).when(rateService).getAvRate(1);

        // Act
        ResponseEntity<?> responseEntity = ratingController.getAvgRateFor(1);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void testGetAvgRatingFail() {
        doThrow(new RuntimeException("Test exception")).when(rateService).getAvRate(1);

        // Act
        ResponseEntity<?> responseEntity = ratingController.getAvgRateFor(1);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }

    @Test
    void testSuucessfulGetRate(){
        doReturn((byte)3).when(rateService).getRate(1, 2);
        // Act
        ResponseEntity<?> responseEntity = ratingController.getRate(1,2);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        
    }

    @Test
    void testFailGetRate(){
        doThrow(new RuntimeException("Test exception")).when(rateService).getRate(1, 2);
        // Act
        ResponseEntity<?> responseEntity = ratingController.getRate(1, 2);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }

    
}