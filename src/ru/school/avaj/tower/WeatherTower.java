package ru.school.avaj.tower;

public class WeatherTower extends Tower{
    private int numberOfSimulations;

    public WeatherTower(int numberOfSimulations) {
        this.numberOfSimulations = numberOfSimulations;
    }

    public String getWeather(Coordinates coordinates) {
        return WeatherProvider.getProvider().getCurrentWeather(coordinates);
    }

    public int getNumberOfSimulations() {
        return numberOfSimulations;
    }

    public void decrementNumberOfSimulations() {
        numberOfSimulations--;
    }

    public void changeWeather() {
        conditionsChanged();
    }
}
