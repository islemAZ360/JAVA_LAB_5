package main.utils;

import main.objects.Const;
import main.objects.HumanBeing;

import java.util.Collection;
import java.util.Comparator;
import java.util.TreeSet;
import java.util.function.Predicate;

public class CollectionManager extends TreeSet<HumanBeing> {
    private final java.time.LocalDateTime creationTime = java.time.LocalDateTime.now();

    public CollectionManager() {
        super(new Comparator<HumanBeing>() {
            @Override
            public int compare(HumanBeing o1, HumanBeing o2) {
                return Long.compare(o1.getId(), o2.getId());
            }

            @Override
            public boolean equals(Object obj) {
                return super.equals(obj);
            }
        });
    }

    @Override
    public int size() {
        return super.size();
    }

    @Override
    public boolean isEmpty() {
        return super.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return super.contains(o);
    }

    @Override
    public boolean add(HumanBeing humanBeing) {
        return super.add(humanBeing);
    }

    @Override
    public boolean addAll(Collection<? extends HumanBeing> c) {
        return super.addAll(c);
    }

    @Override
    public boolean remove(Object o) {
        return super.remove(o);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return super.removeAll(c);
    }

    @Override
    public void clear() {
        super.clear();
    }

    public String getCollectionInfo() {
        return this.toString();
    }

    public String toString() {
        return "Информации о коллекции:" +
                "\n>> Тип: " + this.getClass().getGenericSuperclass().getTypeName() +
                "\n>> Дата инициализации: " + this.creationTime +
                "\n>> Количество элементов: " + this.size();
    }

    public void show() {
        System.out.printf("%-4s | %-20s | %-12s | %-12s | %-30s | %-10s | %-14s | %-11s | %14s | %16s | %10s | %7s\n", Const.FILEHEADER);

        for (HumanBeing human: this) {
            System.out.printf("%-4s | %-20s | %-12s | %-12s | %-30s | %-10s | %-14s | %-11s | %14s | %16s | %10s | %7s\n", HumanBeingReader.extractInfo(human).split(","));
        }
    }


//    Поиск по id но фиксированный, не можем переиспользовать
//    public HumanBeing getHumanById(long id) {
//        for (HumanBeing h : this) {
//            if(id == h.getId()) {
//                return h;
//            };
//        };
//        return null;
//    }

//  Используем Stream чтобы фильтровать первый элемент, который удовлетворяет условиям
//  База
    public HumanBeing getHumanBy(Predicate<HumanBeing> filter) {
        return this.stream()
                .filter(filter)
                .findFirst()
                .orElse(null); // Возврат null при ненайденном элементе
    }

    public HumanBeing getHumanById(long id) {
        return this.getHumanBy(human -> human.getId() == id);
    }
    public HumanBeing getHumanByName(String name) {
//        Redundant and long variant
//        return this.getHumanBy(human -> human.getName().toLowerCase().equals(name.toLowerCase());
        return this.getHumanBy(human -> human.getName().equalsIgnoreCase(name));
    }
    public boolean removeById(long id) {
        HumanBeing human = getHumanById(id);
        if (human != null) {
            return this.remove(human);
        }
        return false;
    }

    public boolean update(long id, HumanBeing newHuman) {
        HumanBeing oldHuman = getHumanById(id);
        if (oldHuman == null) {
            return false;
        }

        // Xóa element cũ
//        this.remove(oldHuman);
        try {
            oldHuman.setName(newHuman.getName());
            oldHuman.setCoordinates(newHuman.getCoordinates());
            oldHuman.setRealHero(newHuman.isRealHero());
            oldHuman.setHasToothpick(newHuman.isHasToothpick());
            oldHuman.setImpactSpeed(newHuman.getImpactSpeed());
            oldHuman.setSoundtrackName(newHuman.getSoundtrackName());
            oldHuman.setMinutesOfWaiting(newHuman.getMinutesOfWaiting());
            oldHuman.setWeaponType(newHuman.getWeaponType());
            oldHuman.setCar(newHuman.getCar());
        } catch (Exception e) {
            System.err.println("bug from update method in CollectionManager");
        }

        // Tạo element mới với ID và creationDate cũ
        // Cần thêm constructor trong HumanBeing để copy ID và creationDate
//        HumanBeing updatedHuman = new HumanBeing(
//                newHuman.getName(),
//                newHuman.getCoordinates(),
//                newHuman.isRealHero(),
//                newHuman.isHasToothpick(),
//                newHuman.getImpactSpeed(),
//                newHuman.getSoundtrackName(),
//                newHuman.getMinutesOfWaiting(),
//                newHuman.getWeaponType(),
//                newHuman.getCar()
//        );



        // Cần set ID và creationDate cũ (hiện tại không thể do private)
        return true;
    }

    public HumanBeing getMax() {
        if (this.isEmpty()) return null;
        return this.stream()
                .max(Comparator.comparingLong(HumanBeing::getId))
                .orElse(null);
    }

    public Long getMaxId() {
        return getMax().getId();
    }

    public HumanBeing getMin() {
        if (this.isEmpty()) return null;
        return this.stream()
                .min(Comparator.comparingLong(HumanBeing::getId))
                .orElse(null);
    }

    public void removeGreater(HumanBeing element) {
        // Tạo list tạm để tránh ConcurrentModificationException
        java.util.ArrayList<HumanBeing> toRemove = new java.util.ArrayList<>();

        for (HumanBeing human : this) {
            if (human.compareTo(element) > 0) { // So sánh theo ID (lớn hơn)
                toRemove.add(human);
            }
        }

        this.removeAll(toRemove);
    }
}

