package main.utils.commands;

import main.objects.HumanBeing;
import main.utils.CollectionManager;
import main.utils.Command;
import main.utils.InputManager;

public class AddIfMaxCommand implements Command {
    private final CollectionManager collectionManager;
    private final InputManager inputManager;

    public AddIfMaxCommand(CollectionManager collectionManager, InputManager inputManager) {
        this.collectionManager = collectionManager;
        this.inputManager = inputManager;
    }

    @Override
    public String getName() {
        return "add_if_max";
    }

    @Override
    public String getDescription() {
        return "add_if_max : добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции";
    }

    @Override
    public void execute(String[] args) {
        // Kiểm tra argument
        if (args.length < 2) {
            System.out.println("Ошибка: не указан ID. Использование: add_if_max {id}");
            return;
        }

        // Parse ID từ argument
        long newId;
        try {
            newId = Long.parseLong(args[1]);
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: ID должен быть целым числом");
            return;
        }

        // Kiểm tra ID có hợp lệ không (phải > 0)
        if (newId <= 0) {
            System.out.println("Ошибка: ID должен быть положительным числом");
            return;
        }

        // Lấy max ID hiện tại
        Long maxId = null;
        if (!collectionManager.isEmpty()) {
            maxId = collectionManager.getMaxId();
        }

        // Trường hợp collection rỗng
        if (maxId == null) {
            System.out.println("Коллекция пуста. Можно добавить элемент с любым ID.");
        }
        // So sánh ID mới với max ID
        else if (newId <= maxId) {
            System.out.println("ID " + newId + " не превышает максимальный ID в коллекции (" + maxId + ").");
            System.out.println("Элемент не будет добавлен.");
            return;
        } else {
            System.out.println("ID " + newId + " больше максимального ID (" + maxId + "). Можно добавлять элемент.");
        }

        // Kiểm tra xem ID đã tồn tại chưa
        if (collectionManager.getHumanById(newId) != null) {
            System.out.println("Ошибка: элемент с ID " + newId + " уже существует в коллекции!");
            return;
        }

        // Nếu vượt qua tất cả điều kiện, tiến hành nhập dữ liệu
        System.out.println("Введите данные для нового элемента с ID = " + newId + ":");
        HumanBeing newHuman = inputManager.createHumanBeingWithId(newId);

        // Thêm vào collection
        collectionManager.add(newHuman);
        System.out.println("Элемент с ID " + newId + " успешно добавлен в коллекцию.");
    }
}