package inheritance;

public class Perro extends Animal {
    public Perro(String name, int age) {
        super(name, age);
    }

    @Override
    public void makeSound() {
        System.out.println("Guau guau");
    }

}
