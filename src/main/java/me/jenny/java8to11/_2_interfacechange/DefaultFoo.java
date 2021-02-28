package me.jenny.java8to11._2_interfacechange;

public class DefaultFoo implements Foo {
    private String name;

    public DefaultFoo(String name) {
        this.name = name;
    }

    @Override
    public void printName() {
        System.out.println(this.name);
    }

    @Override
    public String getName() {
        return this.name;
    }

    // default method 재정의 가능
    @Override
    public void printNameUpperCase() {
        System.out.println("난 재정의 된 " + getName());
    }
}
