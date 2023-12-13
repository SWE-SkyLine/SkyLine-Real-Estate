package com.example.SkyLine.entity;

import com.example.SkyLine.enums.EstateTypeEnum;

import lombok.*;

@Getter
@Setter
public class FilterData {

    private Integer area;
    private EstateTypeEnum estateType;
    private boolean rent; 
}
