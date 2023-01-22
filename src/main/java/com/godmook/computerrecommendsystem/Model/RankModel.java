package com.godmook.computerrecommendsystem.Model;

public class RankModel {
    String name;
    double total_score;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getScore() {
        return total_score;
    }

    public void setScore(double score) {
        this.total_score = score;
    }
}
