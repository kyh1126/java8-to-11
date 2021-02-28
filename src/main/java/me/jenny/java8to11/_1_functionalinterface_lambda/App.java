package me.jenny.java8to11._1_functionalinterface_lambda;

import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class App {
    public static void main(String[] args) {
        Greeting greeting = new Greeting();
//        UnaryOperator<String> hiUnaryOperator = (s) -> "hi" + s;
        // 어떤 객체의 static method 참조
        UnaryOperator<String> hiUnaryOperator = Greeting::hi;

        // 어떤 객체의 인스턴스 method 참조
        UnaryOperator<String> hello = greeting::hello;

        // 임의 객체의 인스턴스 method 참조
        String[] names = {"a", "b", "c"};
//        Arrays.sort(names, String::compareToIgnoreCase);
        Arrays.sort(names, (o1, o2) -> 1);
//        Arrays.sort(names, (o1, o2) -> -1);
        System.out.println(Arrays.toString(names));

        // 생성자 참조
        Supplier<Greeting> newSupplier = Greeting::new;
        Greeting newGreeting = newSupplier.get();

        Function<String, Greeting> newFunction = Greeting::new;
        Greeting newGreeting2 = newFunction.apply("jenny!");
        System.out.println(newGreeting2.getName());

    }
}
