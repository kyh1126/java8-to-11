package me.jenny.java8to11._2_interfacechange;

public class App {
    public static void main(String[] args) {
        Foo foo = new DefaultFoo("jenny");
        foo.printName();
        foo.printNameUpperCase();

        Foo.printAnything();
    }
}
