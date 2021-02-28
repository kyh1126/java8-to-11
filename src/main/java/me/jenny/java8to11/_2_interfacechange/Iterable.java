package me.jenny.java8to11._2_interfacechange;

import java.util.ArrayList;
import java.util.List;
import java.util.Spliterator;

public class Iterable {
    public static void main(String[] args) {
        List<String> name = new ArrayList<>();
        name.add("jenny");
        name.add("kyh1126");
        name.add("ncucu");
        name.add("foo");

        // 1. forEach
//        name.forEach(s -> {
//            System.out.println(s);
//        });
        name.forEach(System.out::println);

        // 2. spliterator: iterator 랑 비슷한데, 쪼갤 수 있는 기능이 있다.
        //                 hasNext 대신 tryAdvance 로 Function 을 주는데
        //                 해당하는게 없으면 false 되어 종료, 있으면 계속 돌게된다.
        Spliterator<String> spliterator = name.spliterator();       // 2
        // trySplit: 쪼갤 수 있으면 가능한 한 2개로 쪼개진다.
        Spliterator<String> spliterator1 = spliterator.trySplit();  // 1
        while (spliterator.tryAdvance(System.out::println)) {
            System.out.println("돌아라 돌아라 ~");
        }
        System.out.println("--------------------");
        // 반으로 쪼개져서 jenny, kyh1126 나온다.
        while (spliterator1.tryAdvance(System.out::println)) {
            System.out.println("돌아라 돌아라2 ~");
        }

    }
}
