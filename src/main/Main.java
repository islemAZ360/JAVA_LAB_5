package main;

import main.objects.Const;
import main.objects.HumanBeing;
import main.utils.CollectionManager;
import main.utils.CommandManager;
import main.utils.FileManager;
import main.utils.InputManager;
import main.utils.commands.*;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 * Главный класс приложения (Main).
 * Точка входа в программу. Здесь происходит инициализация и запуск интерактивного режима.
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);
        // 2. Инициализация менеджеров (Initialization)
        InputManager inputManager = new InputManager(scanner);
        CollectionManager collectionManager = new CollectionManager();
        CommandManager commandManager = new CommandManager();
        String filePath = "";
        String username = "";

        //  1. Определение файла с данными (Data File Setup)
        // Логика: Если путь передан через аргументы командной строки, используем его.
        // Иначе используем файл по умолчанию (как делал Person 1)

        if (args.length == 1) {
            filePath = args[0];
        } else if (args.length == 2) {
            username = args[1];
        } else {
            filePath = Const.FILEPATH;
            username = "";
            System.err.println("Внимание: Путь к файлу не передан через аргументы. Используется файл по умолчанию: " + filePath);
        }
        try {
            // --- 3. Загрузка коллекции из файла (Logic from Person 1) ---
            try {
                main.utils.FileManager fileManager = new FileManager(filePath);
                fileManager.readFile(collectionManager);
                System.out.println("Коллекция успешно загружена. Количество элементов: " + collectionManager.size());
            } catch (Exception e) {
                System.out.println("Ошибка при чтении файла (или файл не найден). Запуск с пустой коллекцией.");
            }

            FileManager fileManager = new FileManager(filePath);

            commandManager.registerCommand("help",new HelpCommand(commandManager));
            commandManager.registerCommand("info",new InfoCommand(collectionManager));
            commandManager.registerCommand("show",new ShowCommand(collectionManager));
            commandManager.registerCommand("add",new AddCommand(collectionManager, inputManager, fileManager));

//            System.out.printf("\n~~~~~~ Welcome %s to our pro java system! ~~~~~~\n", username);
//            System.out.println("Choose any below variant: [number]");
//            System.out.println("1. Get HumanBeing by id");
//            System.out.println("2. Get HumanBeing by name");
//            System.out.print("Enter your option: ");
//            int option = Integer.parseInt(scanner.nextLine());

//            switch (option) {
//                case 1:
//                    System.out.print("Enter id: ");
//                    System.out.println(collectionManager.getHumanById(Integer.parseInt(scanner.nextLine())));
//                    break;
//                case 2:
//                    System.out.print("Enter name: ");
//                    System.out.println(collectionManager.getHumanByName(scanner.nextLine()));
//                    break;
//                default:
//                    System.out.println("Option " + option + " does not exists!");
//                    break;
//            }

            //  5. Интерактивный цикл (Interactive Loop - Logic from Person 2)
            System.out.println("\nПрограмма готова к работе! Введите 'help' для подержки || 'exit' для выхода.");

            while (true) {
                // Вывод приглашения к вводу
                System.out.print(">>> ");

                // Проверка на конец ввода (Ctrl+D)
                if (!scanner.hasNextLine()) break;

                String input = scanner.nextLine().trim();

                // Пропуск пустых строк
                if (input.isEmpty()) continue;

                // Разделение ввода на команду и аргументы
                String[] commandArgs = input.split("\\s+");

                String command = commandArgs[0].toLowerCase();

                if (command.equals("exit")) {
                    System.out.println("Завершение работы программы...");
                    break;
                }

                // Выполнение команды через менеджер
                commandManager.executeCommand(commandArgs);
            }

            scanner.close();

        } catch (Exception E) {
            System.err.println("Error: " + E.getMessage());
        }
    }
}