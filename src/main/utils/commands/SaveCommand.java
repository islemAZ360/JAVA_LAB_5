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
        return "save : сохранить коллекцию в файл";
    }

    @Override
    public void execute(String[] args) {
        if (collectionManager.isEmpty()) {
            System.out.println("Коллекция пуста. Файл будет очищен.");
            // Vẫn gọi saveAll để tạo file rỗng hoặc xóa nội dung
        }

        fileManager.saveAll(collectionManager);
        // Không cần in thêm vì saveAll đã in thông báo
    }
}