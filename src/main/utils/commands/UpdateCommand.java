package main.utils.commands;

import main.objects.HumanBeing;
import main.utils.CollectionManager;
import main.utils.Command;
import main.utils.InputManager;

public class UpdateCommand implements Command {

    private final CollectionManager collectionManager;
    private final InputManager inputManager;

    public UpdateCommand(CollectionManager collectionManager, InputManager inputManager) {
        this.collectionManager = collectionManager;
        this.inputManager = inputManager;
    }

    @Override
    public String getName() {
        return "update";
    }

    @Override
    public String getDescription() {
        return "update id {element} : обновить элемент по ID";
    }

    @Override
    public void execute(String[] args) {

        if (args.length == 0) {
            System.out.println("Введите ID.");
            return;
        }

        if (args.length < 2) {
            System.out.println("Использование: update id");
            return;
        }

        long id = Long.parseLong(args[1]); // args[1] is ID

        HumanBeing newHuman = inputManager.readHumanBeing();

        boolean updated = collectionManager.update(id, newHuman);

        if (updated) {
            System.out.println("Элемент обновлен.");
        } else {
            System.out.println("Элемент с таким ID не найден.");
        }
    }
}