package com.example.SkyLine.entity;

import com.example.SkyLine.enums.EstateTypeEnum;

import lombok.*;

@Getter
@Setter
public class FilterData {
    public FilterData(Integer priceFrom, Integer priceTo, EstateTypeEnum estateType, Boolean rent) {
        this.estateType = estateType;
        this.priceFrom = priceFrom;
        this.priceTo = priceTo;
        this.rent = rent;
    }
    
    private Integer priceFrom;
    private Integer priceTo;
    private EstateTypeEnum estateType;
    private Boolean rent; 

    public Boolean isRent(){
        return rent;
    }
}
