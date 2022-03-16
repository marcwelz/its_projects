package ch.bbw.mw.byLanguage;

/**
 * @author Welz Marc
 * @version 11.10.2021
 */

public class SortAlgorithm {

    public SortAlgorithm() {

    }

    public void insertionsort(Person[] unsortedArray) {
        for (int i = 1; i < unsortedArray.length; ++i) {
            Person person = unsortedArray[i];

            int j = i - 1;
            while (j >= 0 && unsortedArray[j].compareTo(person) > 0) {
                unsortedArray[j + 1] = unsortedArray[j];
                j = j - 1;
            }
            unsortedArray[j + 1] = person;
        }
    }

}
