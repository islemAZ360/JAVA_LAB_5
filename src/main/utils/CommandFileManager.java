package main.utils;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class CommandFileManager extends FileManager implements HandleCommandFile {
    public CommandFileManager(String filePath) {
        super(filePath);
    }

    public void readFileAndRunScripts(
            CollectionManager collectionManager,
            CommandManager commandManager,
            HumanBeingFileManager humanBeingFileManager
    ) {
        try (Scanner scanner = new Scanner(this.readFile())) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();

                // bỏ qua dòng trống
                if (line.isEmpty()) continue;

                try {
                    System.out.println(">>> Reading and executing line: " + line);

                    // gửi command cho CommandManager xử lý
                    commandManager.executeCommand(line.split("\\s+"));

                } catch (Exception e) {
                    System.out.println("Ошибка при выполнении команды: " + e.getMessage());
                }
            }

            System.out.println("Скрипт успешно выполнен.");

        } catch (FileNotFoundException e) {
            System.out.println("Ошибка: файл не найден.");
        }
    }
}
