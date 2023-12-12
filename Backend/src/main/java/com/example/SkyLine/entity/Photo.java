package com.example.SkyLine.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "post_photo_path")
    private String postPhotoURL;

    public Photo(String postPhotoURL) {
        this.postPhotoURL = postPhotoURL;
    }
}
