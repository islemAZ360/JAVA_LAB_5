package main.utils;

import main.objects.HumanBeing;
import java.io.*;
import java.util.Scanner;
import java.util.Collection;

/**
 * Класс для работы с файлом (CSV).
 * Отвечает за чтение данных при запуске и сохранение коллекции.
 */
public class FileManager {
    private final String filePath;

    /**
     * Конструктор принимает путь к файлу из Main.
     */
    public FileManager(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Считывает данные из файла и добавляет их в коллекцию.
     * Использует Scanner для чтения.
     */
    public void readFile(CollectionManager collectionManager) {
        File file = new File(filePath);

        // Проверяем, существует ли файл перед чтением
        if (!file.exists()) {
            System.out.println("Файл не найден. Будет создан новый при сохранении.");
            return;
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) continue;

                try {
                    // Используем вспомогательный класс для конвертации строки в объект
                    HumanBeing human = HumanBeingReader.convertLine(line);
                    collectionManager.add(human);
                } catch (Exception e) {
                    System.out.println("Ошибка при чтении строки: " + e.getMessage());
                }
            }
            System.out.println("Коллекция успешно загружена из файла.");
        } catch (FileNotFoundException e) {
            System.out.println("Ошибка: файл не найден.");
        }
    }

    /**
     * Сохраняет текущую коллекцию в файл в формате CSV.
     * Использует BufferedWriter для записи.
     */
    public void save(Collection<HumanBeing> collection) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (HumanBeing human : collection) {
                // Формируем строку CSV (порядок полей должен совпадать с Reader)
                String csvLine = String.format("%d,%s,%d,%d,%s,%b,%b,%f,%s,%d,%s,%b",
                        human.getId(),
                        human.getName(),
                        human.getCoordinates().getX(),
                        human.getCoordinates().getY(),
                        human.getCreationDate(),
                        human.isRealHero(),
                        human.isHasToothpick(),
                        human.getImpactSpeed(),
                        human.getSoundtrackName(),
                        human.getMinutesOfWaiting(),
                        (human.getWeaponType() == null ? "" : human.getWeaponType()),
                        (human.getCar() == null ? "false" : human.getCar().isCool())
                );

                writer.write(csvLine);
                writer.newLine(); // Переход на новую строку
            }
            writer.flush(); // Очистка буфера
            System.out.println("Коллекция успешно сохранена в файл.");
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении в файл: " + e.getMessage());
        }
    }
}