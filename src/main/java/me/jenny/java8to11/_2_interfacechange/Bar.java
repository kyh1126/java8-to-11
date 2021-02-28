package me.jenny.java8to11._2_interfacechange;

public interface Bar extends Foo {
    // Bar 에서 재정의하는 upperCase 기본 구현체 -> 추상 메소드
    void printNameUpperCase();

    // 부모의 default 메소드가 여기도 있으면 diamond problem.. Compile 에러 발생!
    // 충돌하는 default 메소드가 있는 경우에는 override 해야한다.
}
