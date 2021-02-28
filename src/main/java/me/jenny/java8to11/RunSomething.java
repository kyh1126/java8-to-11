package me.jenny.java8to11;

@FunctionalInterface    // 함수형 인터페이스 라는걸 명시해서 견고하게 관리 가능하다.
public interface RunSomething {
    // 추상 메서드가 이거 딱 하나만 있으면 그 인터페이스는 함수형 인터페이스다.
    // abstract 가 숨어있다.
    void doIt();

    // 이렇게 다른 형태의 메소드가 있어도 그 인터페이스는 여전히 함수형 인터페이스다.
    // 중요한건 오로지 추상 메소드가 몇개 있냐?
    // public 가 숨어있다.
    static void printName() {
        System.out.println("Jenny");
    }

    default void printAge() {
        System.out.println("32");
    }
}
