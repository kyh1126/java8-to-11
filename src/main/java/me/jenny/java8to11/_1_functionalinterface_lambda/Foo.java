package me.jenny.java8to11._1_functionalinterface_lambda;

public class Foo {
    public static void main(String[] args) {
        // 익명 내부 클래스
        /*RunSomething runSomething = new RunSomething() {
            @Override
            public void doIt() {
                System.out.println("기존");
            }
        };*/
        // 자바의 함수형 프로그래밍
        // 1. 특수한 형태의 First class object 다.
        // 2. 고차함수다.(함수가 함수를 매개변수로 받을 수 있고 함수를 리턴할 수 있다)
        // 3. 순수함수: 사이드 이펙트(함수 밖의 값 변경하지 않음)가 없고, 상태가 없다.(함수 밖 값을 사용하지 않는다)
        // 4. 불변성
        RunSomething runSomething = () -> System.out.println("기존"); // 람다형(한 줄)
        runSomething.doIt();
    }
}
