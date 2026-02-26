package main.utils;

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
}

