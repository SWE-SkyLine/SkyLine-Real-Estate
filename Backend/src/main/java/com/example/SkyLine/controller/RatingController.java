package com.example.SkyLine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SkyLine.DTO.RateDTO;
import com.example.SkyLine.service.RateService;
import org.springframework.web.bind.annotation.RequestParam;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class RatingController {

    @Autowired
    private RateService rateService;

    @PostMapping("/rate")
    public ResponseEntity<?> rateUser(@RequestBody RateDTO ratingDto) {
        try {
            rateService.rate(ratingDto);
            return ResponseEntity
                    .ok("user " + ratingDto.getTargetId() + " is rated by " + ratingDto.getRate().getValue());
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("User not found");
        }
    }

    @GetMapping("/avgRate")
    public ResponseEntity<?> getAvgRateFor(@RequestParam int targetId) {
        try {
            return ResponseEntity.ok(rateService.getAvgRate(targetId));
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(0);
        }
    }

    @GetMapping("/getRate")
    public ResponseEntity<?> getRate(@RequestParam int userId, @RequestParam int targetId) {
        try {
            return ResponseEntity.ok(rateService.getRate(userId, targetId));
        } catch (Exception e) {
            System.out.println();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(0);
        }
    }

}
