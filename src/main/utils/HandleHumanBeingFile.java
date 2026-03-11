package main.utils;

import main.objects.HumanBeing;

import java.util.Collection;

public interface HandleHumanBeingFile {
    public void readFileAndLoadHumanBeing(CollectionManager collectionManager);
    public void save(HumanBeing human);
    public void saveOne(HumanBeing human);
    public void saveAll(Collection<HumanBeing> collection);
}
