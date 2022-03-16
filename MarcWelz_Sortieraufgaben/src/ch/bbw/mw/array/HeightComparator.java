package ch.bbw.mw.array;

import java.util.Comparator;

/**
 * @author Welz Marc
 * @version 11.10.2021
 */

public class HeightComparator implements Comparator<Person> {

    @Override
    public int compare(Person o1, Person o2) {
        return Double.compare(o1.getHeight(), o2.getHeight());
    }
}
