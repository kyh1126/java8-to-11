package me.jenny.java8to11._6_completablefuture;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorsMain {
    public static void main(String[] args) {
        // 고수준(High-Level) Concurrency 프로그래밍
        // - 쓰레드를 만들고 관리하는 작업을 애플리케이션에서 분리
        // - 그런 기능을 Executors 에게 위임 (Executors 가 스레드를 만들고, 개발자는 Runnable 만 제공)

        // Executors 가 하는 일
        // - 쓰레드 만들기: 애플리케이션이 사용할 쓰레드 풀을 만들어 관리한다.
        // - 쓰레드 관리: 쓰레드 생명 주기를 관리한다.
        // - 작업 처리 및 실행: 쓰레드로 실행할 작업을 제공할 수 있는 API 를 제공한다.

        // Executors 주요 인터페이스
        // - Executor: execute(Runnable)
        // - ExecutorService: Executor 상속받은 인터페이스로, Callable 도 실행할 수 있으며,
        //                    Executor 를 종료시키거나, 여러 Callable 을 동시에 실행하는 등의 기능을 제공한다.
        //                    1. Blocking Queue: 여기에 작업들을 쌓아둔다.
        //                    2. Thread Pool: 스레드들이 들어있다. 스레드 생성 비용을 줄일 수 있는 장점이 있다.
        // - ScheduledExecutorService: ExecutorService 상속받은 인터페이스로 특정 시간 이후에 또는 주기적으로 작업을 실행할 수 있다.

        // 스레드를 1개만 쓰는 Executor
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        // 1. execute: 가장 고전적인 방법
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("execute Thread " + Thread.currentThread().getName());
            }
        });
        // 2. submit 방식
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("submit Thread " + Thread.currentThread().getName());
            }
        });
        // 3. submit lambda 방식
        executorService.submit(() -> System.out.println("submit lambda Thread " + Thread.currentThread().getName()));

        // ExecutorService 는 어떤 작업을 실행하고 나면 다음 작업이 들어올 때까지 계속 대기하므로 프로세스가 저절로 죽지 않는다.
        executorService.shutdown();     // Graceful shutdown: 현재 진행중 작업을 끝까지 마치고 종료
        executorService.shutdownNow();  // 진행중인 작업 상관없이 당장 끝내는거.
    }
}
