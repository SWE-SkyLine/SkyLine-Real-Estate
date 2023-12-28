package com.example.SkyLine.entity;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Embeddable
public class RatingKey implements Serializable {

    public int userId;

    public int targetId;
}
