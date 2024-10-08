package poo.exercises;

public class Person {
    private String name; 
    private int age; 
    private String lastName;

    public Person(String name, int age, String lastName) {
        this.name = name;
        this.age = age;
        this.lastName = lastName;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String displayInfo() {
        return "Name: " + name + " Last Name: " + lastName + " Age: " + age;

    }

    

}
