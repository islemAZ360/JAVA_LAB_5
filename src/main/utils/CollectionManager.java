package main.utils;

import main.objects.HumanBeing;
import java.util.Comparator;
import java.util.TreeSet;

public class CollectionManager extends TreeSet<HumanBeing> {
    public CollectionManager() {
        super(new Comparator<HumanBeing>() {
            @Override
            public int compare(HumanBeing o1, HumanBeing o2) {
                return 0;
            }

            @Override
            public boolean equals(Object obj) {
                return super.equals(obj);
            }
        });
    }
}
