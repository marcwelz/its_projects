package ch.bbw.mw.array;

import java.util.Comparator;

/**
 * @author Welz Marc
 * @version 11.10.2021
 */

public class PersonComparator implements Comparator<Person> {

    @Override
    public int compare(Person o1, Person o2) {
        if(o1.getLastname().compareTo(o2.getLastname()) > 0) {
            return 1;
        } else if(o1.getLastname().compareTo(o2.getLastname()) < 0) {
            return -1;
        }else {
            return Integer.compare(o1.getFirstname().compareTo(o2.getFirstname()), 0);
        }
    }
}
