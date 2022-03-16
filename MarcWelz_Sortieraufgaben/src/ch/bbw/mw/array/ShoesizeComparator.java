package ch.bbw.mw.array;

import java.util.Comparator;

/**
 * @author Welz Marc
 * @version 11.10.2021
 */

public class ShoesizeComparator implements Comparator<Person> {

    @Override
    public int compare(Person o1, Person o2) {
        return Integer.compare(o1.getShoesize(), o2.getShoesize());
    }
}
