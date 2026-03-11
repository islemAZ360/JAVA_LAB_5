package main.utils;

import main.objects.HumanBeing;
import main.objects.Const;
import java.io.*;
import java.util.Collection;
import java.util.Scanner;

/**
 * Класс для работы с файлом (CSV).
 * Отвечает за чтение данных при запуске и сохранение коллекции.
 */
public class FileManager {
    private final String filePath;
    // Không cần giữ writer như biến instance nữa

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
    public File readFile() {
        File file = new File(filePath);

        int currentMaxId = 0;

        // Проверяем, существует ли файл перед чтением
        if (!file.exists()) {
            System.out.println("Файл не найден. Будет создан новый при сохранении.");
            return null;
        }
        return file;
    }

    public void readFileAndLoadHumanBeing(CollectionManager collectionManager) {
        try (Scanner scanner = new Scanner(this.readFile())) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) continue;

                try {
                    long currentId = HumanBeingReader.extractIdFromLine(line);

//                  // if id exists, then do not add to collection
                    if(collectionManager.getHumanById(currentId) == null) {
                        // Используем вспомогательный класс для конвертации строки в объект
                        HumanBeing human = HumanBeingReader.convertLine(line);
                        collectionManager.add(human);
                        human.setValueHumanCount(collectionManager.getMaxId());
                    } else {
                        System.out.println("Id " + currentId + " already existed!");
                    }

                } catch (Exception e) {
                    System.out.println("Ошибка при чтении строки: " + e.getMessage());
                }

            }
            System.out.println("Коллекция успешно загружена из файла.");
        } catch (FileNotFoundException e) {
            System.out.println("Ошибка: файл не найден.");
        }
    }

//    public void readFileAndLoadCommand(CollectionManager collectionManager, CommandManager commandManager) {
//        try (Scanner scanner = new Scanner(this.readFile())) {
//
//            while (scanner.hasNextLine()) {
//                String line = scanner.nextLine().trim();
//
//                // bỏ qua dòng trống
//                if (line.isEmpty()) continue;
//
//                try {
//                    System.out.println(">>> " + line);
//
//                    // gửi command cho CommandManager xử lý
//                    commandManager.executeCommand(line);
//
//                } catch (Exception e) {
//                    System.out.println("Ошибка при выполнении команды: " + e.getMessage());
//                }
//            }
//
//            System.out.println("Скрипт успешно выполнен.");
//
//        } catch (FileNotFoundException e) {
//            System.out.println("Ошибка: файл не найден.");
//        }
//    }

    /**
     * Сохраняет один объект HumanBeing в файл (режим добавления)
     */
    public void save(HumanBeing human) {
        // Формируем строку CSV
        String csvLine = HumanBeingReader.extractInfo(human);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(csvLine);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении в файл: " + e.getMessage());
        }
    }

    /**
     * Сохраняет один объект и выводит сообщение
     */
    public void saveOne(HumanBeing human) {
        this.save(human);
        System.out.println("Human info успешно сохранена в файл.");
    }

    /**
     * Сохраняет всю коллекцию в файл (перезаписывает файл)
     */
    public void saveAll(Collection<HumanBeing> collection) {
        // Kiểm tra thư mục cha có tồn tại không
        File file = new File(filePath);
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs(); // Tạo thư mục nếu chưa tồn tại
        }

        // Sử dụng try-with-resources để tự động đóng writer
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false))) {
            // false = перезаписать файл, не добавлять

            // Ghi header (nếu muốn)
            // writer.write(String.join(",", Const.FILEHEADER));
            // writer.newLine();

            // Ghi từng element trong collection
            for (HumanBeing human : collection) {
                String csvLine = HumanBeingReader.extractInfo(human);
                writer.write(csvLine);
                writer.newLine();
            }

            writer.flush();
            System.out.println("Коллекция успешно сохранена в файл. Всего элементов: " + collection.size());

        } catch (IOException e) {
            System.out.println("Ошибка при сохранении коллекции в файл: " + e.getMessage());
        }
    }

    /**
     * Getter для пути к файлу
     */
    public String getFilePath() {
        return filePath;
    }
}