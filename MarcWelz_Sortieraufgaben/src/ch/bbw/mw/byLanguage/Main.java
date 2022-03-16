package ch.bbw.mw.byLanguage;

import java.text.Collator;
import java.util.Locale;
import java.util.stream.Stream;

/**
 * @author Welz Marc
 * @version 11.10.2021
 */

public class Main {

    private static Collator collator = Collator.getInstance(Locale.GERMAN);

    public static void main(String[] args) {

        Person[] persons = new Person[12];
        persons[0] = new Person("Zenia",  "Estes", 1.82, 37, 37);
        persons[1] = new Person("Callum",  "Day", 1.82, 44, 21);
        persons[2] = new Person("ÜÜ",  "ÜÜ", 1.64, 39, 26);
        persons[3] = new Person("UU",  "UU", 1.72, 42, 37);
        persons[4] = new Person("ÖÖ",  "ÖÖ", 1.54, 42, 34);
        persons[5] = new Person("Æ", "Æ", 1.70, 39, 27);
        persons[6] = new Person("OO",  "OO", 1.66, 39, 27);
        persons[7] = new Person("AB",  "AA", 1.54, 42, 34);
        persons[8] = new Person("AA",  "AA", 1.66, 39, 27);
        persons[9] = new Person("ÄÄ", "ÄÄ", 1.70, 39, 27);
        persons[10] = new Person("Æ", "Æ", 1.70, 39, 27);
        persons[11] = new Person("Ø", "Ø", 1.70, 39, 27);

        SortAlgorithm sortAlgorithm = new SortAlgorithm();

        System.out.println("---Start---");
        Stream.of(persons).limit(20).forEach(System.out::println);
        sortAlgorithm.insertionsort(persons);
        System.out.println("---Lastname---");
        Stream.of(persons).limit(20).forEach(System.out::println);

        //norway causes problems but wont crash the application
    }
}
