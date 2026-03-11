package main.utils.commands;

import main.objects.HumanBeing;
import main.utils.CollectionManager;
import main.utils.Command;
import main.utils.InputManager;

/**
 * Команда add {element}: добавляет новый элемент в коллекцию.
 * Элемент читается с помощью ElementReader.
 * Lưu ý: Command này chỉ thêm vào collection, không tự động save vào file.
 * User phải gọi save riêng để lưu.
 */
public class AddCommand implements Command {
    private final CollectionManager collectionManager;
    private final InputManager inputManager;

    public AddCommand(CollectionManager collectionManager, InputManager inputManager) {
        this.collectionManager = collectionManager;
        this.inputManager = inputManager;
    }

    @Override
    public String getName() {
        return "add";
    }

    @Override
    public String getDescription() {
        return "add {element} : добавить новый элемент в коллекцию (не сохраняет в файл)";
    }

    @Override
    public void execute(String[] args) {
        try {
            HumanBeing humanBeing = this.inputManager.readHumanBeing();

            boolean added = collectionManager.add(humanBeing);

            if (added) {
                System.out.println("Элемент успешно добавлен в коллекцию. ID: " + humanBeing.getId());
                System.out.println("Всего элементов: " + collectionManager.size());
                System.out.println("Не забудьте ввести 'save' для сохранения в файл.");
            } else {
                System.out.println("Не удалось добавить элемент.");
            }

        } catch (IllegalArgumentException e) {
            System.err.println("Ошибка при создании элемента: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Непредвиденная ошибка: " + e.getMessage());
        }
    }
}