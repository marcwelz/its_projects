package ch.bbw.mw.sortFirstAndLastname;

/**
 * @author Welz Marc
 * @version 11.10.2021
 */

public class SortAlgorithm {

    public SortAlgorithm() {

    }

    public void insertionsort(Person[] arrayToSort) {
        for (int i = 1; i < arrayToSort.length; ++i) {
            Person person = arrayToSort[i];

            int j = i - 1;
            while (j >= 0 && arrayToSort[j].compareTo(person) > 0) {
                arrayToSort[j + 1] = arrayToSort[j];
                j = j - 1;
            }
            arrayToSort[j + 1] = person;
        }
    }

}
