package ru.school.avaj.tower;

import java.util.Random;

public class WeatherProvider {
    private static WeatherProvider weatherProvider;
    private final Random random = new Random();
    private static String[] weather = { "SUN", "RAIN", "FOG", "SNOW" };

    private WeatherProvider() {

    }

    public static WeatherProvider getProvider() {
        if (weatherProvider == null) {
            weatherProvider = new WeatherProvider();
        }
        return weatherProvider;
    }

    public String getCurrentWeather(Coordinates coordinates) {
        return weather[(coordinates.getCell() + random.nextInt(4)) % 4];
    }
}
