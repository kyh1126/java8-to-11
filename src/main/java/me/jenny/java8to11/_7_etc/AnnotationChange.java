package me.jenny.java8to11._7_etc;

import java.util.Arrays;
import java.util.List;

@Chicken
@Chicken("양념")
@Chicken("마늘간장")
public class AnnotationChange {
    public static void main(String[] args) throws @Chicken RuntimeException {
        // 애노테이션 관련 두가지 큰 변화
        // - 자바 8부터 애노테이션을 타입 선언부에도 사용할 수 있게 됨
        // - 자바 8부터 애노테이션을 중복해서 사용할 수 있게 됨
        List<@Chicken String> names = Arrays.asList("jenny");

        // 중복 사용할 수 있는 애노테이션을 만들기
        // - 중복 사용할 애노테이션 만들기
        // - 중복 애노테이션 컨테이너 만들기: 컨테이너 애노테이션은 중복 애노테이션과 @Retention 및 @Target 이 같거나 더 넓어야 한다.

        // 컨테이너 애노테이션으로 중복 애노테이션 참조하기
        ChickenContainer chickenContainer = AnnotationChange.class.getAnnotation(ChickenContainer.class);
        Arrays.stream(chickenContainer.value()).forEach(c -> {
            System.out.println(c.value());
        });
    }

// @Target(ElementType.TYPE_PARAMETER)
    /*static class FeelsLikeChicken<@Chicken T> {
        public static <@Chicken C> void print(C c){
            System.out.println(c);
        }
    }*/

    // @Target(ElementType.TYPE_USE)
    static class FeelsLikeChicken<@Chicken T> {
        public static <@Chicken C> void print(@Chicken C c) {
            System.out.println(c);
        }
    }
}
