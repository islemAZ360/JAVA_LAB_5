package main.utils;

import main.objects.Car;
import main.objects.Coordinates;
import main.objects.HumanBeing;
import main.objects.WeaponType;

public class HumanBeingReader {
    private static Car convertCar(boolean isCool) {
        return new Car(isCool);
    }

    private static Coordinates convertCoordinates(int x, long y) {
        return new Coordinates(x, y);
    }

    public static HumanBeing convertLine(String line) {
        String[] humanInfo = line.split(",");

        Car car = convertCar(Boolean.getBoolean(humanInfo[11]));
        Coordinates coordinates = convertCoordinates(
                Integer.parseInt(humanInfo[2]),
                Long.parseLong(humanInfo[3])
        );
        return new HumanBeing (
                humanInfo[1],
                coordinates,
                Boolean.parseBoolean(humanInfo[5]),
                Boolean.parseBoolean(humanInfo[6]),
                Double.parseDouble(humanInfo[7]),
                humanInfo[8],
                Integer.parseInt(humanInfo[9]),
                WeaponType.valueOf(humanInfo[10]),
                car
            );
        }
    }

