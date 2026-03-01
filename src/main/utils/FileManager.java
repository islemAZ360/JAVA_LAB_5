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
    private BufferedWriter writer;

    /**
     * Конструктор принимает путь к файлу из Main.
     */
    public FileManager(String filePath) {
        this.filePath = filePath;
        try {
            this.writer = new BufferedWriter(new FileWriter(this.filePath, true));
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении в файл: " + e.getMessage());
        }
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
    private void save(HumanBeing human) {
        // Формируем строку CSV (порядок полей должен совпадать с Reader)
        String csvLine = HumanBeingReader.extractInfo(human);
        try {
            this.writer.write(csvLine);
            this.writer.newLine(); // Переход на новую строку
            this.writer.flush(); // Очистка буфера
        } catch (IOException E) {
            System.out.println("Ошибка при сохранении в файл: " + E.getMessage());
        }
    }

    public void saveOne(HumanBeing human) {
        this.save(human);
        System.out.println("Human info успешно сохранена в файл.");
    }

    public void saveAll(Collection<HumanBeing> collection) {
        for (HumanBeing human : collection) {
            this.save(human);
        }
        System.out.println("Коллекция успешно сохранена в файл.");
    }
}
