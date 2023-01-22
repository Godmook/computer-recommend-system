package com.godmook.computerrecommendsystem.Model;

public class PowerModel {
    int id;
    String name;
    int rank;
    String image;
    int price;
    String shop_link;
    String shop_name;
    String shop_logo;
    double score;
    int happy_id;
    int happy_price;
    double total_score;
    int power;
    String details;
    int hits;

    public int getHits() {
        return hits;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getShop_link() {
        return shop_link;
    }

    public void setShop_link(String shop_link) {
        this.shop_link = shop_link;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getShop_logo() {
        return shop_logo;
    }

    public void setShop_logo(String shop_logo) {
        this.shop_logo = shop_logo;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public int getHappy_id() {
        return happy_id;
    }

    public void setHappy_id(int happy_id) {
        this.happy_id = happy_id;
    }

    public int getHappy_price() {
        return happy_price;
    }

    public void setHappy_price(int happy_price) {
        this.happy_price = happy_price;
    }

    public double getTotal_score() {
        return total_score;
    }

    public void setTotal_score(double total_score) {
        this.total_score = total_score;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
