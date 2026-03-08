package main.utils.commands;

import main.utils.Command;
import main.utils.CommandManager;

public class HelpCommand implements Command {
    private final CommandManager commandManager;

    public HelpCommand(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getDescription() {
        return "вывести справку по доступным командам";
    }

    @Override
    public void execute(String[] args) {
        System.out.println("Доступные команды:");
        //dung ham lamda
//        commandManager.getCommands().values().forEach(command ->
//                System.out.println(command.getName() + " : " + command.getDescription())
//        );
    }
}
