package ru.school.avaj.aircraft;

import ru.school.avaj.logger.FlyingLogger;
import ru.school.avaj.tower.Coordinates;

import java.util.HashMap;

public class Aircraft {
    protected long id;
    protected String name;
    protected String type = "Aircraft";
    protected Coordinates coordinates;
    private static long idCounter = 1L;
    protected final HashMap<String, int[]> behavior = new HashMap<>();
    protected final HashMap<String, String> phrases = new HashMap<>();

    protected Aircraft(String name, Coordinates coordinates) {
        this.id = nextId();
        this.name = name;
        this.coordinates = coordinates;
    }

    private long nextId() {
        return idCounter++;
    }

    protected void setCoordinates(int[] coordinates) {
        this.coordinates =  new Coordinates(
                this.coordinates.getLongitude() + coordinates[0],
                this.coordinates.getLatitude() + coordinates[1],
                this.coordinates.getHeight() + coordinates[2]);
    }

    public String toString() {
        return type + "#" + name + "(" + id + ")";
    }

    protected void sendFlyingPhrase(String currentWeather) {
        FlyingLogger.log(toString() + ": " + phrases.get(currentWeather));
    }

    protected void sendLandingPhrase() {
        FlyingLogger.log(toString() + ": Landing in " + coordinates.getLongitude() + " " + coordinates.getLatitude() + " " + coordinates.getHeight());
    }
}
