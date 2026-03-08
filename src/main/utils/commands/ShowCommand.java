package main.utils.commands;

import main.utils.Command;
import main.utils.CollectionManager;
import main.objects.HumanBeing;

public class ShowCommand implements Command {

    private final CollectionManager collectionManager;

    public ShowCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public String getName() {
        return "show";
    }

    @Override
    public String getDescription() {
        return "вывести все элементы коллекции";
    }

    @Override
    public void execute(String[] args) {
        if (collectionManager.isEmpty()) {
            System.out.println("Коллекция пуста.");
            return;
        }

        for (HumanBeing human : collectionManager) {
            System.out.println(human);
        }
    }
}