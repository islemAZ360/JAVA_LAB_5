package main;

import main.objects.*;

public class Main {
    public static void main(String[] args) {
        try {
            Car bugatti = new Car(true);
            Coordinates coors = new Coordinates(100, 165);
            HumanBeing human = new HumanBeing(
                    "Duong",
                    coors,
                    true,
                    true,
                    555,
                    "Bip Bip",
                    2,
                    WeaponType.HAMMER,
                    bugatti
            );
//            System.out.println(bugatti);
//            System.out.println(coors);
//            System.out.println(human.getCreationDate());
        } catch (Exception E) {
            System.out.println("Error: " + E.getMessage());
        }
    };
}