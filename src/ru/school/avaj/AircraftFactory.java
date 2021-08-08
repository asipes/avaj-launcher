package ru.school.avaj;

import ru.school.avaj.aircraft.Baloon;
import ru.school.avaj.aircraft.Flyable;
import ru.school.avaj.aircraft.Helicopter;
import ru.school.avaj.aircraft.JetPlane;
import ru.school.avaj.exception.AvajLauncherException;
import ru.school.avaj.tower.Coordinates;

public class AircraftFactory {
    public static Flyable newAircraft(String type, String name, int longitude, int latitude, int height) throws AvajLauncherException {

        Coordinates coordinates = new Coordinates(longitude, latitude, height);

        if ("JetPlane".equalsIgnoreCase(type)) {
            return new JetPlane(name, coordinates);
        } else if ("Helicopter".equalsIgnoreCase(type)) {
            return new Helicopter(name, coordinates);
        } else if ("Baloon".equalsIgnoreCase(type)) {
            return new Baloon(name, coordinates);
        } else {
            throw new AvajLauncherException("Unknown aircraft");
        }
    }
}
