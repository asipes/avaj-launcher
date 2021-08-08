package ru.school.avaj;

import ru.school.avaj.aircraft.Flyable;
import ru.school.avaj.exception.AvajLauncherException;
import ru.school.avaj.logger.FlyingLogger;
import ru.school.avaj.tower.WeatherTower;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Simulator {
    private FlyingLogger logger = new FlyingLogger("simulation.txt");
    private BufferedReader reader;
    private WeatherTower weatherTower;
    private List<Flyable> aircraft = new ArrayList<>();

    public static void main(String[] args) {

        try {
            if (args.length != 1) {
                throw new AvajLauncherException("[ERROR]: The script file is missing.");
            }
            Simulator simulator = new Simulator();
            simulator.readScenario(args[0]);

            if (simulator.aircraft.size() == 0) {
                throw new AvajLauncherException("[ERROR]: There is no description of the aircraft.");
            }
            int numberOfSimulations = simulator.weatherTower.getNumberOfSimulations();
            simulator.registerAircraft();
            simulator.run();

            FlyingLogger.log("\nAll simulations completed successfully.");
            FlyingLogger.close();
            System.out.println("You have completed " + numberOfSimulations + " flight simulations." +
                    "\nResult saved in simulation.txt file.");


        } catch (AvajLauncherException | IOException e) {
            e.printStackTrace();
        }

    }

    private void readScenario(String filePath) throws IOException, AvajLauncherException {
        reader = new BufferedReader(new FileReader(filePath));
        int lineCount = 0;
        String lineBuffer;

        while (true) {
            lineBuffer = reader.readLine();
            lineCount++;
            if (lineBuffer == null) {
                throw new AvajLauncherException("[ERROR]: Empty file.");
            }
            if (!lineBuffer.isEmpty()) {
                int numberOfSimulations = Integer.parseInt(lineBuffer);
                if (numberOfSimulations > -1) {
                    weatherTower = new WeatherTower(numberOfSimulations);
                    break;
                } else {
                    throw new AvajLauncherException("[ERROR]: Script line #" + lineCount + " is not valid. The number of simulations must be positive.");
                }
            }
        }

        while (lineBuffer != null) {
            lineBuffer = reader.readLine();
            lineCount++;

            if (lineBuffer == null || lineBuffer.isEmpty()) {
                continue;
            }

            String attributes[] = lineBuffer.split(" ");
            if (attributes.length == 0) {
                continue;
            }
            if (attributes.length != 5) {
                throw new AvajLauncherException("[ERROR]: Script line #" + lineCount + " is not valid, 5 aircraft parameters are expected, not " + attributes.length + ".");
            }

            int longitude;
            int latitude;
            int height;
            try {
                longitude = Integer.parseInt(attributes[2]);
                latitude = Integer.parseInt(attributes[3]);
                height = Integer.parseInt(attributes[4]);
            } catch (Exception e) {
                throw new AvajLauncherException("[ERROR]: Script line #" + lineCount + " is not valid, coordinates must be integer.");
            }

            if (longitude < 1 || latitude < 1 || height < 1) {
                throw new AvajLauncherException("[ERROR]: Script line #" + lineCount + " is not valid, coordinates must be positive.");
            }
            aircraft.add(AircraftFactory.newAircraft(attributes[0], attributes[1], longitude, latitude, height));
        }
        reader.close();
    }

    private void registerAircraft() {
        FlyingLogger.log("Registration aircraft:\n");
        for (Flyable flyable : aircraft) {
            flyable.registerTower(weatherTower);
        }
        FlyingLogger.log("\nAll aircraft registered.");
    }

    private void run() {
        int simulationCount = 0;

        while (weatherTower.getNumberOfSimulations() != 0) {
            FlyingLogger.log("\n---------------------------------------- Simulation #" + ++simulationCount + " has started ----");
            weatherTower.changeWeather();
            weatherTower.decrementNumberOfSimulations();
            FlyingLogger.log("---------------------------------------- Simulation #" + simulationCount + " is over --------");
        }
    }
}
