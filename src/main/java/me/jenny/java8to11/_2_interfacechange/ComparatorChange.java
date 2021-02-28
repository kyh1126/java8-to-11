package me.jenny.java8to11._2_interfacechange;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ComparatorChange {
    public static void main(String[] args) {
        List<String> name = new ArrayList<>();
        name.add("jenny");
        name.add("kyh1126");
        name.add("ncucu");
        name.add("foo");

        // 4. Comparator
        // - reversed()
        // - thenComparing(): comparator 에 추가 정렬 조건 주고싶을 때 사용
        // - static reverseOrder()/naturalOrder()
        // - static nullsFirst()/nullsLast()
        // - static comparing()
        Comparator<String> comparator = String::compareToIgnoreCase;
//        name.sort(comparator.reversed());
        name.sort(comparator.reversed().thenComparing(String::length));
        name.forEach(System.out::println);

        // 이러한 특성들로 인하여 Interface - Abstract class (Adapter class) 상속 방식 API 에서 (스프링5.0 -> 자바8 부터)
        // 비침투적인 방식으로 사용 가능해진 Interface 를 직접 사용할 수 있게되어 Adapter class 들이 대거 deprecated 되었다.
    }
}
