package ru.school.avaj.aircraft;

import ru.school.avaj.tower.WeatherTower;

public interface Flyable {
    void updateConditions();
    void registerTower(WeatherTower weatherTower);
}
