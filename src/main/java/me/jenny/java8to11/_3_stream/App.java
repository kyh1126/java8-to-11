package me.jenny.java8to11._3_stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) {
        List<String> name = new ArrayList<>();
        name.add("jenny");
        name.add("kyh1126");
        name.add("ncucu");
        name.add("foo");

        // Stream:
        // 1. 데이터를 담고 있는 저장소 (컬렉션)이 아니다.
        // 2. Funtional in nature, 스트림이 처리하는 데이터 소스를 변경하지 않는다.
        // 3. 스트림으로 처리하는 데이터는 오직 한번만 처리한다.
        // 4. 무제한일 수도 있다. (Short Circuit 메소드를 사용해서 제한할 수 있다)
        // 5. 중개 오퍼레이션은 근본적으로 lazy 하다.
        // 6. 손쉽게 병렬 처리 할 수 있다.

        // 종료 오퍼레이션 이 와야 중개 오퍼레이션이 실행된다.
        name.stream().map(s -> {
            System.out.println(s);
            return s.toUpperCase();
        }).collect(Collectors.toList());

        // 병렬 처리는 스레드를 만들고 context switching 하는 비용이 더 들어서 오히려 느려질 수 있다.
        // 방대한 양이 아닌 이상.. 멀티 스레드로 다 빨라질거 같으면 reactive stream 나오지도 않았다.
        List<String> collect = name.parallelStream().map(s -> {
            System.out.println(s + " " + Thread.currentThread().getName());
            return s.toUpperCase();
        }).collect(Collectors.toList());
        collect.forEach(System.out::println);
    }
}
