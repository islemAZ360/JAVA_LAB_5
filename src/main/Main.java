package main;

import main.utils.CollectionManager;
import main.utils.CommandManager;
import main.utils.FileManager;
import main.utils.InputManager;
import java.util.Scanner;

/**
 * Главный класс приложения (Main).
 * Точка входа в программу. Здесь происходит инициализация и запуск интерактивного режима.
 */
public class Main {
    public static void main(String[] args) {

        //  1. Определение файла с данными (Data File Setup)
        // Логика: Если путь передан через аргументы командной строки, используем его.
        // Иначе используем файл по умолчанию (как делал Person 1)

        String filePath = "data/data.csv";
        if (args.length > 0) {
            filePath = args[0];
        } else {
            System.out.println("Внимание: Путь к файлу не передан через аргументы. Используется файл по умолчанию: " + filePath);
        }

        try {
            // 2. Инициализация менеджеров (Initialization)
            Scanner scanner = new Scanner(System.in);
            InputManager inputManager = new InputManager(scanner);
            CollectionManager collectionManager = new CollectionManager();
            CommandManager commandManager = new CommandManager();

            // --- 3. Загрузка коллекции из файла (Logic from Person 1) ---
            try {
                main.FileManager fileManager = new FileManager(filePath);
                fileManager.readFile(collectionManager);
                System.out.println("Коллекция успешно загружена. Количество элементов: " + collectionManager.size());
            } catch (Exception e) {
                System.out.println("Ошибка при чтении файла (или файл не найден). Запуск с пустой коллекцией.");
            }

            // ------4. Регистрация команд (Command Registration - Placeholder for Person 3)-----
            // Здесь ваш коллега (Person 3) должен будет добавить команды.
            // Например:
            // commandManager.register("help", new HelpCommand(commandManager));
            // commandManager.register("add", new AddCommand(collectionManager, inputManager));
            // commandManager.register("show", new ShowCommand(collectionManager));

            //  5. Интерактивный цикл (Interactive Loop - Logic from Person 2)
            System.out.println("\nПрограмма готова к работе! Введите 'exit' для выхода.");

            while (true) {
                // Вывод приглашения к вводу
                System.out.print("> ");

                // Проверка на конец ввода (Ctrl+D)
                if (!scanner.hasNextLine()) break;

                String input = scanner.nextLine().trim();

                // Пропуск пустых строк
                if (input.isEmpty()) continue;

                // Разделение ввода на команду и аргументы
                String[] commandArgs = input.split("\\s+");

                // Команда выхода
                if (commandArgs[0].equalsIgnoreCase("exit")) {
                    System.out.println("Завершение работы программы...");
                    break;
                }

                // Выполнение команды через менеджер
                commandManager.executeCommand(commandArgs);
            }

            scanner.close();

        } catch (Exception e) {
            System.out.println("Критическая ошибка: " + e.getMessage());
        }
    }
}