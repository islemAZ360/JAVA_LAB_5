package main.utils.commands;

import main.objects.HumanBeing;
import main.utils.CollectionManager;
import main.utils.Command;

public class RemoveGreaterCommand implements Command {

    private final CollectionManager collectionManager;

    public RemoveGreaterCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public String getName() {
        return "remove_greater";
    }

    @Override
    public String getDescription() {
        return "remove_greater id : удалить элементы с ID больше указанного";
    }

    @Override
    public void execute(String[] args) {
        // Kiểm tra tham số
        if (args.length < 2) {
            System.out.println("Использование: remove_greater id");
            System.out.println("Пример: remove_greater 10");
            System.out.println("(удалить все элементы с ID больше 10)");
            return;
        }

        try {
            // Lấy ID từ tham số
            long id = Long.parseLong(args[1]);

            // Tìm element với ID đó
            HumanBeing element = collectionManager.getHumanById(id);

            // Kiểm tra element có tồn tại không
            if (element == null) {
                System.out.println("Элемент с ID " + id + " не найден.");
                return;
            }

            // Lưu kích thước cũ
            int oldSize = collectionManager.size();

            // Xóa các element lớn hơn
            collectionManager.removeGreater(element);

            // Tính số lượng đã xóa
            int removedCount = oldSize - collectionManager.size();

            // Thông báo kết quả
            if (removedCount > 0) {
                System.out.println("Удалено элементов с ID больше " + id + ": " + removedCount);
                System.out.println("Текущий размер коллекции: " + collectionManager.size());
            } else {
                System.out.println("Нет элементов с ID больше " + id);
            }

        } catch (NumberFormatException e) {
            System.out.println("Ошибка: ID должен быть числом.");
        } catch (Exception e) {
            System.out.println("Ошибка при выполнении команды: " + e.getMessage());
        }
    }
}