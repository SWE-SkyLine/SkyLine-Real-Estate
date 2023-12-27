package com.example.SkyLine.DTO;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class LogInResponseDTO {
    private int id;
    private String name;
    private String email;
    private String jwtToken;
}
