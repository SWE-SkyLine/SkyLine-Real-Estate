package com.example.SkyLine.entity;

import com.example.SkyLine.enums.EstateTypeEnum;

import lombok.*;

@Getter
@Setter
public class FilterData {

    private Integer priceFrom;
    private Integer priceTo;
    private EstateTypeEnum estateType;
    private boolean rent; 
}
