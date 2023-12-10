package com.example.SkyLine.DTO;

import java.util.List;

public class PostDTO {
    private String title;
    private int price;
    private boolean isRent;
    private int area;
    private String description;
    private String estateType;
    private String mapLink;
    private int bedroom;
    private int bathroom;
    private int level;
    private List<String> photoes;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isIsRent() {
        return isRent;
    }

    public void setIsRent(boolean isRent) {
        this.isRent = isRent;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEstateType() {
        return estateType;
    }

    public void setEstateType(String estateType) {
        this.estateType = estateType;
    }

    public String getMapLink() {
        return mapLink;
    }

    public void setMapLink(String mapLink) {
        this.mapLink = mapLink;
    }

    public int getBedroom() {
        return bedroom;
    }

    public void setBedroom(int bedroom) {
        this.bedroom = bedroom;
    }

    public int getBathroom() {
        return bathroom;
    }

    public void setBathroom(int bathroom) {
        this.bathroom = bathroom;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public List<String> getPhotoes() {
        return photoes;
    }

    public void setPhotoes(List<String> photoes) {
        this.photoes = photoes;
    }

    @Override
    public String toString() {
        return "PostDTO{" +
                "title='" + title + '\'' +
                ", price=" + price +
                ", isRent=" + isRent +
                ", area=" + area +
                ", description='" + description + '\'' +
                ", estateType='" + estateType + '\'' +
                ", mapLink='" + mapLink + '\'' +
                ", bedroom=" + bedroom +
                ", bathroom=" + bathroom +
                ", level=" + level +
                ", photoes=" + photoes +
                '}';
    }
}
