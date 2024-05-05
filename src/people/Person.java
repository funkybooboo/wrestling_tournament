package people;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class Person {
    private int age;
    private String name;
    private String school;
    private int moral;

    public Person() {
    }

    public Person(int age, String name, String school) {
        this.age = age;
        this.name = name;
        this.school = school;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public String getSchool() {
        return school;
    }

    public void setMoral(int moral) {
        this.moral = moral;
    }
    public int getMoral() {
        return this.moral;
    }
    public void changeMoral(int howMuch) {
        moral += howMuch;
    }

}
