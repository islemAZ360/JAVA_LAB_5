package main.objects;

public class HumanBeing {
    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private boolean realHero;
    private boolean hasToothpick;
    private double impactSpeed; //Значение поля должно быть больше -432
    private String soundtrackName; //Поле не может быть null
    private int minutesOfWaiting;
    private WeaponType weaponType; //Поле может быть null
    private Car car; //Поле может быть null
}
