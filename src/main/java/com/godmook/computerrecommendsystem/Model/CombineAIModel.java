package com.godmook.computerrecommendsystem.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CombineAIModel {
    int cpu_id;
    int gpu_id;
    int mainboard_id;
    int ram_id;
    int power_id;
    int case_id;
    int ssd_id;
    int cooler_id;
    double score;
    int price;
    String socket;

    public int getCpu_id() {
        return cpu_id;
    }

    public void setCpu_id(int cpu_id) {
        this.cpu_id = cpu_id;
    }

    public int getGpu_id() {
        return gpu_id;
    }

    public void setGpu_id(int gpu_id) {
        this.gpu_id = gpu_id;
    }

    public int getMainboard_id() {
        return mainboard_id;
    }

    public void setMainboard_id(int mainboard_id) {
        this.mainboard_id = mainboard_id;
    }

    public int getRam_id() {
        return ram_id;
    }

    public void setRam_id(int ram_id) {
        this.ram_id = ram_id;
    }

    public int getPower_id() {
        return power_id;
    }

    public void setPower_id(int power_id) {
        this.power_id = power_id;
    }

    public int getCase_id() {
        return case_id;
    }

    public void setCase_id(int case_id) {
        this.case_id = case_id;
    }

    public int getSsd_id() {
        return ssd_id;
    }

    public void setSsd_id(int ssd_id) {
        this.ssd_id = ssd_id;
    }

    public int getCooler_id() {
        return cooler_id;
    }

    public void setCooler_id(int cooler_id) {
        this.cooler_id = cooler_id;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getSocket() {
        return socket;
    }

    public void setSocket(String socket) {
        this.socket = socket;
    }
}
