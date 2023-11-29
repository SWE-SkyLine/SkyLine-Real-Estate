package com.example.SkyLine.model;

import com.fasterxml.jackson.databind.ObjectMapper;

public class VerificationRequest {
    private String email;
    private int code;

    // Default constructor (required for JSON deserialization)
    public VerificationRequest() {
    }

    // Parameterized constructor
    public VerificationRequest(String email, int code) {
        this.email = email;
        this.code = code;
    }

    // Getter and Setter methods
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    // Example of deserialization method
    public static VerificationRequest fromJson(String jsonString) {
        try {
            // Create ObjectMapper instance
            ObjectMapper objectMapper = new ObjectMapper();

            // Deserialize JSON string to VerificationRequest object
            return objectMapper.readValue(jsonString, VerificationRequest.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Handle the exception appropriately in your application
        }
    }
}
