package me.jenny.java8to11;

import java.util.function.*;

public class Foo2 {
    public static void main(String[] args) {
        int val = 2;

        Plus10 plus10 = new Plus10();
        System.out.println("Plus10 apply: " + plus10.apply(val));

        Function<Integer, Integer> multiple = (i) -> i * 2;
        System.out.println("Function apply: " + multiple.apply(val));

        // compose: 입력값을 가지고 함수 적용한 후에 입력값으로 사용한다.
        Function<Integer, Integer> compose = plus10.compose(multiple);
        System.out.println("compose: " + compose.apply(val));

        // andThen: 수신객체 먼저 적용한 후에 입력값 함수 적용한다.
        Function<Integer, Integer> andThen = plus10.andThen(multiple);
        System.out.println("andThen: " + andThen.apply(val));

        // Consumer: void 리턴. 타입을 받아서 수행만 한다.
        Consumer<Integer> consumer = (i) -> System.out.println(i);
        System.out.print("consumer: ");
        consumer.accept(val);

        // Supplier: 어떤 값을 하나 가져오는 함수 인터페이스. 내가 받아올 값의 타입을 정의.
        Supplier<Integer> get10 = () -> val;
        System.out.println("Supplier: " + get10.get());

        // Predicate: 어떤 인자 값 하나 받아서 true/false 리턴해주는 함수 인터페이스
        //            and, or, not 가능.
        Predicate<String> predicate = (s) -> s.startsWith("jenny");
        System.out.println("Predicate: " + predicate.test("jenny.kk"));

        // UnaryOperator: 입력값, 결과값이 하나이고 타입이 같은 경우 사용 가능.
        UnaryOperator<Integer> unaryOperator = (i) -> i + 10;
        System.out.println("unaryOperator: " + unaryOperator.apply(val));

    }
}