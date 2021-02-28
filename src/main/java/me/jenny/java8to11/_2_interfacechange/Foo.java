package me.jenny.java8to11._2_interfacechange;

public interface Foo {
    void printName();

    // 추가 요구사항으로 인해 추상메소드 추가 -> 기존 구현 객체 다 변경 필요
//    void printNameUpperCase();

    // default 메소드가 구현 객체에서 잘 동작하는지 보장 못해서(NPE) 문서화를 잘 해야한다. @implSpec

    /**
     * @implSpec 이 구현체는 getName()으로 가져온 문자열을 대문자로 바꿔준다.
     */
    default void printNameUpperCase() {
        System.out.println(getName().toUpperCase());
    }

    String getName();

    // Object 가 제공하는 기능 (equals, hasCode)는 기본 메소드로 제공할 수 없다.
    // 구현체가 재정의해야 한다.

    static void printAnything() {
        System.out.println("static method in Foo interface!");
    }
}
