package com.example.oop_project_semester2;

public class Racket {
    private int racketWidth;

    private int racketHeight;

    public Racket(int height, int width){
        this.racketHeight = height;
        this.racketWidth = width;
    }

    public int getRacketHeight() {
        return racketHeight;
    }

    public int getRacketWidth() {
        return racketWidth;
    }

    public void setRacketHeight(int racketHeight) {
        this.racketHeight = racketHeight;
    }

    public void setRacketWidth(int racketWidth) {
        this.racketWidth = racketWidth;
    }
}
