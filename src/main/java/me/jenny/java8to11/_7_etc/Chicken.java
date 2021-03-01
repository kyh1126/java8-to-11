package me.jenny.java8to11._7_etc;

import java.lang.annotation.*;

// 애노테이션 관련 두가지 큰 변화
// - @Target(ElementType.TYPE_PARAMETER): (제네릭) 타입 파라미터에도 쓸 수 있게 해준다.
// - @Target(ElementType.TYPE_USE): TYPE_PARAMETER 포함해서, 좀 더 자유롭게 여기저기서 쓸 수 있게 된다.

// 타입 선언부
// - 제네릭타입
// - 변수타입
// - 매개변수 타입
// - 예외타입
// ...

// 타입에 사용할 수 있으려면
// - TYPE_PARAMETER: 타입 변수에만 사용할 수 있다.
// - TYPE_USE: 타입 변수를 포함해서 모든 타입 선언부에 사용할 수 있다.

@Retention(RetentionPolicy.RUNTIME)
//@Target(ElementType.TYPE_PARAMETER)
@Target(ElementType.TYPE_USE)
@Repeatable(ChickenContainer.class) // 중복 가능
public @interface Chicken {
    String value() default "";
}
