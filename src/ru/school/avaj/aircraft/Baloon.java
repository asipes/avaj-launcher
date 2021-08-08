package ru.school.avaj.aircraft;

import ru.school.avaj.tower.Coordinates;
import ru.school.avaj.tower.WeatherTower;

public class Baloon extends Aircraft implements Flyable {
    private WeatherTower weatherTower;

    public Baloon(String name, Coordinates coordinates) {
        super(name, coordinates);
        this.type = "Baloon";

        phrases.put("SUN", "Oh my god, what a beautiful day!");
        phrases.put("RAIN", "I think I'll grow gills soon...");
        phrases.put("FOG", "Time to quit smoking, hehe hehe!");
        phrases.put("SNOW", "I think I froze my... ears!");

        behavior.put("SUN", new int[]{2, 0, 4});
        behavior.put("RAIN", new int[]{0, 0, -5});
        behavior.put("FOG", new int[]{0, 0, -3});
        behavior.put("SNOW", new int[]{0, 0, -15});
    }

    public void updateConditions() {
        String currentWeather = weatherTower.getWeather(coordinates);
        setCoordinates(behavior.get(currentWeather));

        sendFlyingPhrase(currentWeather);

        if (coordinates.getHeight() == 0) {
            weatherTower.unregister(this);
            sendLandingPhrase();
            weatherTower.sendUnregistredPhrase(toString());
        }
    }

    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
        this.weatherTower.register(this);
        this.weatherTower.sendRegistredPhrase(toString());
    }
}
