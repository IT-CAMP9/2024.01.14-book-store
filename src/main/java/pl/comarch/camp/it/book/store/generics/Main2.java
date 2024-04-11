package pl.comarch.camp.it.book.store.generics;

public class Main2 {
    public static void main(String[] args) {
        Pet p = new Dog();

        Box<Pet> petBox = new Box<Pet>();
        Box<Dog> godBox = new Box<Dog>();

        //Box<Pet> petBox2 = new Box<Dog>();

        /*Box<?> pudelko = metoda();
        pudelko.cos = null;*/

        Box<Dog> pudelko = metoda();
        pudelko.cos = new Dog();
        pudelko.a = 5;
    }

    public static Box<Dog> metoda() {
        return new Box<Dog>();
    }
}
