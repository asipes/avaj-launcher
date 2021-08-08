package ru.school.avaj.aircraft;

import ru.school.avaj.tower.Coordinates;
import ru.school.avaj.tower.WeatherTower;

public class JetPlane extends Aircraft implements Flyable{
    private WeatherTower weatherTower;

    public JetPlane(String name, Coordinates coordinates) {
        super(name, coordinates);
        this.type = "JetPlane";

        phrases.put("SUN", "The sun blinds our eyes, next time we will fly at night!");
        phrases.put("RAIN", "It seems like it rains forever...");
        phrases.put("FOG", "We go on instruments!");
        phrases.put("SNOW", "Only snow was not enough for us...");

        behavior.put("SUN", new int[]{0, 10, 2});
        behavior.put("RAIN", new int[]{0, 5, 0});
        behavior.put("FOG", new int[]{0, 1, 0});
        behavior.put("SNOW", new int[]{0, 0, -7});
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
