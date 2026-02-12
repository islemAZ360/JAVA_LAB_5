package main.objects;
import java.util.concurrent.atomic.AtomicInteger;

public class HumanBeing implements Comparable<HumanBeing> {
    private static final AtomicInteger humanCount = new AtomicInteger(0); // The first ID is 1
    private final long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private final java.time.LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private boolean realHero;
    private boolean hasToothpick;
    private double impactSpeed; //Значение поля должно быть больше -432
    private String soundtrackName; //Поле не может быть null
    private int minutesOfWaiting;
    private WeaponType weaponType; //Поле может быть null
    private Car car; //Поле может быть null

    // Private для того, чтобы HumanBeing не можно создать без аргументов
    private HumanBeing() {
        //Automatic value and cant set value
        this.id = humanCount.incrementAndGet();
        this.creationDate = java.time.LocalDateTime.now();
    }

    public HumanBeing(
        String name,
        Coordinates coordinates,
        boolean realHero,
        boolean hasToothpick,
        double impactSpeed,
        String soundtrackName,
        int minutesOfWaiting,
        WeaponType weaponType,
        Car car
    ) {
        this();
        this.setName(name);
        this.setCoordinates(coordinates);
        this.setRealHero(realHero);
        this.setHasToothpick(hasToothpick);
        this.setImpactSpeed(impactSpeed);
        this.setSoundtrackName(soundtrackName);
        this.setMinutesOfWaiting(minutesOfWaiting);
        this.setWeaponType(weaponType);
        this.setCar(car);
    }

    public long getId() {
        return this.id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Поле 'name' не может быть null, строка не может быть пустой");
        }
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return this.coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        if (coordinates == null) {
            throw new IllegalArgumentException("Поле 'coordinates' не может быть null");
        }
        this.coordinates = coordinates;
    }

    public java.time.LocalDateTime getCreationDate() {
        return creationDate;
    }

    public boolean isRealHero() {
        return realHero;
    }

    public void setRealHero(boolean realHero) {
        this.realHero = realHero;
    }

    public boolean isHasToothpick() {
        return hasToothpick;
    }

    public void setHasToothpick(boolean hasToothpick) {
        this.hasToothpick = hasToothpick;
    }

    public double getImpactSpeed() {
        return impactSpeed;
    }

    public void setImpactSpeed(double impactSpeed) {
        if (impactSpeed <= -432) {
            throw new IllegalArgumentException("Значение поля 'impactSpeed' должно быть больше " + Const.MINVALUEIMPACTSPEED + ". Получено: " + impactSpeed);
        }
        this.impactSpeed = impactSpeed;
    }

    public String getSoundtrackName() {
        return soundtrackName;
    }

    public void setSoundtrackName(String soundtrackName) {
        if (soundtrackName == null) {
            throw new IllegalArgumentException("Значение поля 'soundtrackName' не может быть null");
        }
        this.soundtrackName = soundtrackName;
    }

    public int getMinutesOfWaiting() {
        return minutesOfWaiting;
    }

    public void setMinutesOfWaiting(int minutesOfWaiting) {
        if (minutesOfWaiting < 0) {
            throw new IllegalArgumentException("Значение поля 'minutesOfWaiting' должно быть больше нуля. Получено: " + impactSpeed);
        }
        this.minutesOfWaiting = minutesOfWaiting;
    }

    public WeaponType getWeaponType() {
        return weaponType;
    }

    public void setWeaponType(WeaponType weaponType) {
        this.weaponType = weaponType;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public boolean equals(Object newHuman) {
        if(this == newHuman) { return true; };
        if (newHuman == null || this.getClass() != newHuman.getClass()) { return false; };
        HumanBeing thatHuman = (HumanBeing) newHuman;
        return id == thatHuman.getId();
    }

    @Override
    public int hashCode() {
        return Long.hashCode(id);
    }

    @Override
    public int compareTo(HumanBeing comparingHuman) {
        return Long.compare(this.id, comparingHuman.getId());
//        return this.getId().compareTo(comparingHuman.getId());
    };
}
