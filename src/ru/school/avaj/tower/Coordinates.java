package ru.school.avaj.tower;

public class Coordinates {
    private int longitude;
    private int latitude;
    private int height;

    public Coordinates(int longitude, int latitude, int height) {
        this.longitude = max(longitude, 1);

        this.latitude = max(latitude, 1);

        if (height > 100) {
            this.height = 100;
        } else this.height = max(height, 0);
    }

    private int max(int a, int b) {
        return (a >= b) ? a : b;
    }

    public int getLongitude() {
        return longitude;
    }

    public int getLatitude() {
        return latitude;
    }

    public int getHeight() {
        return height;
    }

    public int getCell() {
        return longitude + latitude + height;
    }
}
