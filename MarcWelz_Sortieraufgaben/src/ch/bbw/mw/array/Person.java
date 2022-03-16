package ch.bbw.mw.array;

/**
 * @author Welz Marc
 * @version 11.10.2021
 */

public class Person {
    private String firstname;
    private String lastname;
    private double height;
    private int shoesize;
    private int age;

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
