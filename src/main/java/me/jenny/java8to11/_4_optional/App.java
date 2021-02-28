package me.jenny.java8to11._4_optional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class App {
    public static void main(String[] args) {
        List<OnlineClass2> springClasses = new ArrayList<>();
        springClasses.add(new OnlineClass2(1, "spring boot", true));
        springClasses.add(new OnlineClass2(5, "rest api development", false));

        Optional<OnlineClass2> spring = springClasses.stream()
                .filter(oc -> oc.getTitle().startsWith("spring"))
                .findFirst();

        Optional<OnlineClass2> jpa = springClasses.stream()
                .filter(oc -> oc.getTitle().startsWith("jpa"))
                .findFirst();

        // Optional 에 값이 있는지 없는지 확인하기
        // - isPresent()
        // - isEmpty() (Java 11부터 제공)
        boolean present = spring.isPresent();
        System.out.println("isPresent: " + present);

        // Optional 에 있는 값 가져오기
        // - get()
        // - 만약에 비어있는 Optional 에서 뭔가 꺼낸다면 NoSuchElement 런타임 예외 발생
        OnlineClass2 springGet = spring.get();
        System.out.println("Optional.get(): " + springGet.getTitle());

        // Optional 에 값이 있는 경우에 그 값을 가지고 ~~를 하라.
        // - ifPresent(Consumer)    ex> Spring 으로 시작하는 수업이 있으면 id를 출력하라
        spring.ifPresent(oc -> System.out.println("ifPresent: " + oc.getTitle()));

        // Optional 에 값이 있으면 가져오고 없는 경우에 ~~를 리턴하라.
        // - orElse(T)  ex> JPA 로 시작하는 수업이 없다면 비어있는 수업을 리턴하라.
        OnlineClass2 jpaOrElse = jpa.orElse(createNewClasses());
        System.out.println("jpa.orElse(): " + jpaOrElse.getTitle());
        // 값이 있더라도 orElse 실행이 된다.
        OnlineClass2 springOrElse = spring.orElse(createNewClasses());
        System.out.println("spring.orElse(): " + springOrElse.getTitle());

        // Optional 에 값이 있으면 가져오고 없는 경우에만 ~~를 하라.
        // - orElseGet(Supplier)    ex> JPA 로 시작하는 수업이 없다면 새로 만들어서 리턴하라.
        OnlineClass2 jpaOrElseGet = jpa.orElse(createNewClasses());
        System.out.println("jpa.orElseGet(): " + jpaOrElseGet.getTitle());
        // Supplier 로 인하여 lazy 하게 실행이 된다.
        OnlineClass2 springOrElseGet = spring.orElse(createNewClasses());
        System.out.println("spring.orElseGet(): " + springOrElseGet.getTitle());

        // Optional 에 값이 있으면 가져오고, 없는 경우(대안이 없는 경우) 에러를 던져라.
        // - orElseThrow(): 안에 Supplier 로 () -> new Exception() / IllegalStateException::new 같이 지정 가능하다.
        spring.orElseThrow();

        // Optional 에 들어있는 값 걸러내기
        // - Optional.filter(Predicate)
        Optional<OnlineClass2> empty = spring.filter(oc -> !oc.isClosed());
        System.out.println("Optional.filter: " + empty.isEmpty());

        // Optional 에 들어있는 값 변환하기
        // - Optional.map(Function): Stream 의 map 은 일대일.
        // - Optional.flatMap(Function): Optional 안에 들어있는 인스턴스가 Optional 인 경우에 사용하면 편리하다.
        Optional<Integer> integer = spring.map(OnlineClass2::getId);
        System.out.println("Optional.map: " + integer.isPresent());
        // Optional 안의 값도 Optional 일 경우!
        Optional<Progress> progress = spring.flatMap(OnlineClass2::getProgress);
        System.out.println("Optional.flatMap: " + progress.isEmpty());
    }

    private static OnlineClass2 createNewClasses() {
        System.out.println("--- creating new online class ---");
        return new OnlineClass2(10, "New class", false);
    }
}
