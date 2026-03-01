package main.utils;

import java.util.HashMap;
import java.util.Map;

public class CommandManager {
    // #1
    private final Map<String, Command> commands = new HashMap<>();

    // #2

    public void registerCommand(String commandName, Command command) {
        commands.put(commandName, command);
    }

    // #3

    public void executeCommand(String[] args) {

        if (args.length == 0 || args[0].trim().isEmpty()) {
            return;
        }

        String commandName = args[0].toLowerCase();

        Command command = commands.get(commandName);

        if (command == null) {
            System.out.println("Неизвестная команда: '" + commandName + "'. Введите 'help' для справки.");
        } else {
            try {
                command.execute(args);
            } catch (Exception e) {
            System.out.println("Произошла ошибка при выполнении команды: " + e.getMessage());
            }
        }
    }

    public Map<String, Command> getCommands() {
        return commands;
    }
}
