package com.example.SkyLine.DTO;

import lombok.*;

@Getter
@Setter
public class EditPostDTO {
    private Integer id;
    private String title;
    private String description;
    private byte bedroom;
    private String mapLink;
    private Integer price;
    private short area;
    private String address;
    private byte level;
}
