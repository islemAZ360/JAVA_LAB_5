package main.utils.commands;

import main.utils.Command;
import main.utils.CollectionManager;

public class InfoCommand implements Command {

    private final CollectionManager collectionManager;

    public InfoCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public String getName() {
        return "info";
    }

    @Override
    public String getDescription() {
        return "вывести информацию о коллекции";
    }

    @Override
    public void execute(String[] args) {
        System.out.println("Тип коллекции: " + collectionManager.getClass().getSimpleName());
        System.out.println("Дата инициализации: " + collectionManager.getCreationTime());
        System.out.println("Количество элементов: " + collectionManager.size());
    }
}