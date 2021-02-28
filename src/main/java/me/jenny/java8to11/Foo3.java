package me.jenny.java8to11;

import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

public class Foo3 {
    public static void main(String[] args) {
        BinaryOperator<Integer> binaryOperator = (Integer a, Integer b) -> a * b;
        System.out.println("binaryOperator: " + binaryOperator);


    }

    private void run() {
//        final int baseNumber = 10;
        int baseNumber = 10;    // effective final, 자바 8부터 생략 가능.

        // 로컬 클래스
        class LocalClass {
            void printBaseNumber() {
                System.out.println("intConsumer: " + baseNumber);
            }
        }

        // 익명 클래스
        Consumer<Integer> consumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                System.out.println(baseNumber);
            }
        };

        // 람다
        IntConsumer intConsumer = (i) -> {
        // 익명 클래스는 새로 scope 을 만들지만, 람다는 람다를 감싸고 있는 scope 와 같다.
//        IntConsumer intConsumer = (baseNumber) -> {
//            baseNumber = baseNumber + 2;
            System.out.println("intConsumer: " + baseNumber);
        };

        /*
          변수 캡처 (Variable Capture)
          1. 로컬 변수 캡처
          : final 이거나 effective final 인 경우에만 참조할 수 있다.
            그렇지 않을 경우 concurrency 문제가 생길 수 있어서 컴파일가 방지한다.
          2. effective final
          : 이것도 역시 자바 8부터 지원하는 기능으로 “사실상" final 인 변수.
            final 키워드 사용하지 않은 변수를 익명 클래스 구현체 또는 람다에서 참조할 수 있다.
          3. 익명 클래스 구현체와 달리 ‘쉐도윙’하지 않는다.
          : 익명 클래스는 새로 scope 를 만들지만, 람다는 람다를 감싸고 있는 scope 와 같다.
         */
        intConsumer.accept(baseNumber);

    }
}
