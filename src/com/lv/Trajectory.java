package com.lv;

import java.util.ArrayList;

public class Trajectory {

    private ArrayList<Point> points;

    public Trajectory() {
        this.points = new ArrayList<>();
    }

    public ArrayList<Point> getPoints() {
        return points;
    }

    public void setPoints(ArrayList<Point> points) {
        this.points = points;
    }
}
