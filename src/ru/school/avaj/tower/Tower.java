package ru.school.avaj.tower;

import ru.school.avaj.aircraft.Flyable;
import ru.school.avaj.logger.FlyingLogger;

import java.util.ArrayList;
import java.util.List;

public class Tower {
    private final List<Flyable> observers = new ArrayList<>();

    public void register(Flyable flyable) {
        if (!observers.contains(flyable)) {
            observers.add(flyable);
        }
    }

    public void sendUnregistredPhrase(String aircraft) {
        FlyingLogger.log("Tower says: " + aircraft + " unregistered to weather tower.");
    }

    public void sendRegistredPhrase(String aircraft) {
        FlyingLogger.log("Tower says: " + aircraft + " registered to weather tower.");
    }

    public void unregister(Flyable flyable) {
        observers.remove(flyable);
    }

    protected void conditionsChanged() {
        for (int i = 0; i < observers.size(); i++) {
            observers.get(i).updateConditions();
        }
    }
}
