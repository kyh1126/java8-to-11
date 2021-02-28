package me.jenny.java8to11._4_optional;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class App2 {
    public static void main(String[] args) {
        List<OnlineClass2> springClasses = new ArrayList<>();
        springClasses.add(new OnlineClass2(1, "spring boot", true));
        springClasses.add(new OnlineClass2(2, "spring data jpa", true));
        springClasses.add(new OnlineClass2(3, "spring mvc", false));
        springClasses.add(new OnlineClass2(4, "spring core", false));
        springClasses.add(new OnlineClass2(5, "rest api development", false));

        OnlineClass2 spring_boot = new OnlineClass2(1, "spring boot", true);
        // NPE 발생한다.
//        Duration studyDuration = spring_boot.getProgress().getStudyDuration();
        Duration studyDuration = spring_boot.getProgress().map(Progress::getStudyDuration).orElse(null);
        System.out.println(studyDuration);
    }
}
