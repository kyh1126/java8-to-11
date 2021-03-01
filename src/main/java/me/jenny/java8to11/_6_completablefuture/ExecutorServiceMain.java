package me.jenny.java8to11._6_completablefuture;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceMain {
    public static void main(String[] args) {
        // Executors 주요 인터페이스
        // - Executor: execute(Runnable)
        // - ExecutorService: Executor 상속받은 인터페이스로, Callable 도 실행할 수 있으며,
        //                    Executor 를 종료시키거나, 여러 Callable 을 동시에 실행하는 등의 기능을 제공한다.
        //                    1. Blocking Queue: 여기에 작업들을 쌓아둔다.
        //                    2. Thread Pool: 스레드들이 들어있다. 스레드 생성 비용을 줄일 수 있는 장점이 있다.
        // - ScheduledExecutorService: ExecutorService 상속받은 인터페이스로 특정 시간 이후에 또는 주기적으로 작업을 실행할 수 있다.

        // Thread 는 2개인데 작업은 5개인 상황, 스레드 2개가 작업 5개를 나누어 실행한다.
        ExecutorService fixedExecutorService = Executors.newFixedThreadPool(2);
        fixedExecutorService.submit(getRunnable("Hello"));
        fixedExecutorService.submit(getRunnable("jenny"));
        fixedExecutorService.submit(getRunnable("The"));
        fixedExecutorService.submit(getRunnable("Java"));
        fixedExecutorService.submit(getRunnable("Thread"));
        fixedExecutorService.shutdownNow();

        // schedule 메소드로 주기적으로 실행하게 할 수 있다.
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        // 3초 있다가 작업 실행
        scheduledExecutorService.schedule(getRunnable("Hello schedule!"), 3, TimeUnit.SECONDS);
        // 1초 있다가 작업 실행해서 2초마다 계속 실행.
        scheduledExecutorService.scheduleAtFixedRate(getRunnable("Hello scheduleAtFixedRate!"), 1, 2, TimeUnit.SECONDS);

        // Fork/Join 프레임워크: ExecutorService 의 구현체로 손쉽게 멀티 프로세서를 활용할 수 있게끔 도와준다.
        // 지금까진 Runnable 만(return void) 사용하고, 자바 1.5 에 등장한 Callable 이 나올 차례다.
        // Runnable 과 같은데, 차이점은 리턴을 할 수 있다.
    }

    private static Runnable getRunnable(String message) {
        return () -> System.out.println(message + ", " + Thread.currentThread().getName());
    }
}
