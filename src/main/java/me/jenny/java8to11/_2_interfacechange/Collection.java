package me.jenny.java8to11._2_interfacechange;

import java.util.ArrayList;
import java.util.List;

public class Collection {
    public static void main(String[] args) {
        List<String> name = new ArrayList<>();
        name.add("jenny");
        name.add("kyh1126");
        name.add("ncucu");
        name.add("foo");

        // 1. stream/parallelStream
        // 모든 stream 들은 위의 spliterator 로 만들어져 있다.
        name.stream();

        // 2. removeIf(Predicate)
        name.removeIf(s -> s.startsWith("j"));

        // 3. spliterator

        name.sort(String::compareToIgnoreCase);
        name.forEach(System.out::println);
    }
}
