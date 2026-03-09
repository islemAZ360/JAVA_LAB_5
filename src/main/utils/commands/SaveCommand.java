package main.utils.commands;

import main.utils.Command;
import main.utils.FileManager;
import main.utils.CollectionManager;

public class SaveCommand implements Command {

    private final CollectionManager collectionManager;
    private final FileManager fileManager;

    public SaveCommand(CollectionManager collectionManager, FileManager fileManager) {
        this.collectionManager = collectionManager;
        this.fileManager = fileManager;
    }

    @Override
    public String getName() {
        return "save";
    }

    @Override
    public String getDescription() {
        return "сохранить коллекцию в файл";
    }

    @Override
    public void execute(String[] args) {

        fileManager.save(collectionManager.getCollection());

        System.out.println("Коллекция сохранена в файл.");
    }
}