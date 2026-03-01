package main.utils.commands;

import main.objects.HumanBeing;
import main.utils.CollectionManager;
import main.utils.Command;
import main.utils.FileManager;
import main.utils.InputManager;

/**
 * Команда add {element}: добавляет новый элемент в коллекцию.
 * Элемент читается с помощью ElementReader.
 */
public class AddCommand implements Command {
    private final CollectionManager collectionManager;
    private final InputManager inputManager;
    private final FileManager fileManager;

    public AddCommand(CollectionManager collectionManager, InputManager inputManager, FileManager fileManager) {
        this.collectionManager = collectionManager;
        this.inputManager = inputManager;
        this.fileManager = fileManager;
    }

    @Override
    public String getName() {
        return "add";
    }

    @Override
    public String getDescription() {
        return "add {element} : добавить новый элемент в коллекцию";
    }

    @Override
    public void execute(String[] args) {
        HumanBeing humanBeing = this.inputManager.readHumanBeing();
        collectionManager.add(humanBeing);
        fileManager.saveOne(humanBeing);
        System.out.println("Элемент добавлен.");
    }
}