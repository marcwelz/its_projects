package ch.bbw.mw.byLanguage;

import java.text.Collator;
import java.util.Locale;

/**
 * @author Welz Marc
 * @version 11.10.2021
 */

public class Person implements Comparable<Person>{
    private String firstname;
    private String lastname;
    private double height;
    private int shoesize;
    private int age;

    private static Collator collator = Collator.getInstance(Locale.GERMAN);

    public Person(String firstname, String lastname, double height, int shoesize, int age) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.height = height;
        this.shoesize = shoesize;
        this.age = age;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public int getShoesize() {
        return shoesize;
    }

    public void setShoesize(int shoesize) {
        this.shoesize = shoesize;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public int compareTo(Person o) {
        if(collator.compare(this.getLastname(), o.getLastname()) > 0) {
            return 1;
        } else if(collator.compare(this.getLastname(), o.getLastname()) < 0) {
            return -1;
        }else {
            return collator.compare(this.getFirstname(), o.getFirstname());
        }
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", height=" + height +
                ", shoesize=" + shoesize +
                ", age=" + age +
                '}';
    }
}
