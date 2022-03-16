package ch.bbw.mw.sortLastname;

/**
 * @author Welz Marc
 * @version 11.10.2021
 */

public class SortAlgorithm {

    public SortAlgorithm() {

    }

    public void insertionsort(Person[] arrayToSort) {
        for (int i = 1; i < arrayToSort.length; ++i) {
            Person key = arrayToSort[i];
            String personText = arrayToSort[i].getLastname();

            int j = i - 1;
            while (j >= 0 && arrayToSort[j].getLastname().compareTo(personText) > 0) {
                arrayToSort[j + 1] = arrayToSort[j];
                j = j - 1;
            }
            arrayToSort[j + 1] = key;
        }
    }
}
