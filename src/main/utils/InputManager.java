package main.utils;
import main.objects.Car;
import main.objects.Coordinates;
import main.objects.HumanBeing;
import main.objects.WeaponType;

import java.util.Scanner;
import java.util.NoSuchElementException;

public class InputManager {

    private final Scanner scanner;

    public InputManager(Scanner scanner) {
        this.scanner = scanner;

    }

    // read name
    public String readName() {
        while (true) {
            System.out.print("Введите имя: ");


            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println("Ошибка: имя не может быть пустым!");
            } else {
                return input;
            }
        }
    }
// read Координаты

    // # X

    public int readX() {
        while (true) {
            System.out.print("Введите координату X (X > -162): ");
            String input = scanner.nextLine().trim();

            try {

                int x = Integer.parseInt(input);

                if (x <= -162) {
                    System.out.println("Ошибка: X должен быть больше -162!");
                } else {
                    return x;
                }
            } catch (NumberFormatException e) {

                System.out.println("Ошибка: введите целое число!");
            }
        }
    }

    // # Y

    public long readY() {
        while (true) {
            System.out.println("Введите координату Y (Макс. 440): ");

            String input = scanner.nextLine().trim();

            try {
                long y = Long.parseLong(input);
                if (y > 440) {
                    System.out.println("Ошибка: значение Y не может превышать 440!");
                } else {
                    return y;
                }
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите целое число (типа long)!");
            }
        }
    }

    // realHero ??
    public Boolean readBoolean(String message) {
        while (true) {

            System.out.println(message + " (true/false): ");
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equals("true")) return true;
            if (input.equals("false")) return false;

            System.out.println("Ошибка: введите 'true' или 'false'!");

        }
    }

    // # impactSpeed
    public double readImpactSpeed() {
        while (true) {
            System.out.print("Введите скорость удара (impactSpeed > -432): ");
            String input = scanner.nextLine().trim();
            try {
                double speed = Double.parseDouble(input);
                if (speed <= -432) {
                    System.out.println("Ошибка: значение должно быть больше -432!");
                } else {
                    return speed;
                }
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите число (double)!");
            }
        }
    }

    // # WeaponType
    public WeaponType readWeaponType() {
        while (true) {
            System.out.println("Выберите тип оружия: HAMMER, RIFLE, KNIFE, MACHINE_GUN");
            System.out.print("Оставьте пустым для null: ");
            String input = scanner.nextLine().trim().toUpperCase();

            if (input.isEmpty()) return null;

            try {
                return WeaponType.valueOf(input); // يحول النص إلى قيمة من الـ Enum
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка: такого типа оружия нет في القائمة!");
            }
        }
    }
    public Car readCar() {
        System.out.print("У него есть машина? (yes/no): ");
        String answer = scanner.nextLine().trim().toLowerCase();

        if (answer.equals("yes")) {
            boolean isCool = readBoolean("Машина крутая?");
            return new Car(isCool); // ننشئ كائن سيارة جديد
        }
        return null; // إذا قال لا، نرجع null (لا توجد سيارة)
    }
    // # read Sound track Name
    public String readSoundtrackName() {
        while (true) {
            System.out.print("Введите название саундтрека: ");
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Ошибка: название не может быть пустым!");
            } else {
                return input;
            }
        }
    }
    // # read Minutes Of Waiting
    public int readMinutesOfWaiting() {
        while (true) {
            System.out.print("Введите время ожидания (минуты): ");
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите целое число!");
            }
        }
    }

    // # readHumanBeing()
    public HumanBeing readHumanBeing() {

        String name = readName();
        Coordinates coords = new Coordinates(readX(), readY());
        boolean hero = readBoolean("Он настоящий герой?");
        boolean toothpick = readBoolean("У него есть зубочистка?");
        double speed = readImpactSpeed();

        String soundtrack = readSoundtrackName();
        int minutes = readMinutesOfWaiting();
        WeaponType weapon = readWeaponType();
        Car car = readCar();


        return new HumanBeing(name, coords, hero, toothpick, speed, soundtrack, minutes, weapon, car);
    }
}

