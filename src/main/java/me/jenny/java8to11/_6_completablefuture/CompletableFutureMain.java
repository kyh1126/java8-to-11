package me.jenny.java8to11._6_completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletableFutureMain {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // CompletableFuture: 자바에서 비동기(Asynchronous) 프로그래밍을 가능케하는 인터페이스
        // - Future 로도 어느정도 가능했지만 하기 힘든 일들이 많았다.
        // ex>
        // - Future 를 외부에서 완료시킬 수 없다. 취소하거나, get()에 타임아웃을 설정할 수는 있다.
        // - 블로킹 코드(get())를 사용하지 않고서는 작업이 끝났을 때 콜백을 실행할 수 없다.
        // - 여러 Future 를 조합할 수 없다.  ex> Event 정보 가져온 후, Event 에 참석하는 회원 목록 가져오기
        // - 예외 처리용 API 를 제공하지 않는다.

        // CompletableFuture
        // - Implements Future
        // - Implements CompletionStage

        CompletableFuture<String> future = new CompletableFuture<>();
        future.complete("jenny");
        System.out.println("CompletableFuture.complete: " + future.get());
        // 위와 같다.
        CompletableFuture<String> future2 = CompletableFuture.completedFuture("jenny");
        System.out.println("CompletableFuture.completedFuture: " + future2.get());

        // 비동기로 작업 실행하기
        // 리턴값이 없는 경우: runAsync()
        CompletableFuture<Void> future3 =
                CompletableFuture.runAsync(() -> System.out.println("runAsync: " + Thread.currentThread().getName()));
        future3.get();  // get()을 해야 작업이 수행된다.

        // 리턴값이 있는 경우: supplyAsync()
        CompletableFuture<String> future4 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello, " + Thread.currentThread().getName());
            return "supplyAsync!";
        });
        System.out.println(future4.get());

        // 콜백 제공하기
        // - thenApply(Function): 리턴값을 받아서 다른 값으로 바꾸는 콜백
        // - thenAccept(Consumer): 리턴값을 또 다른 작업을 처리하는 콜백(리턴없이)
        // - thenRun(Runnable): 리턴값 받지 않고 다른 작업을 처리하는 콜백, Runnable 밖에 못 온다.
        // - thenRunAsync(Runnable): 콜백 자체를 또 다른 쓰레드에서 실행할 수 있다.
        CompletableFuture<String> future5 = CompletableFuture.supplyAsync(() -> {
            return " supplyAsync!";
        }).thenApply(s -> {
            System.out.print(Thread.currentThread().getName());
            return s.toUpperCase();
        });
        System.out.println(future5.get());

        CompletableFuture.supplyAsync(() -> {
            System.out.print("Hello, " + Thread.currentThread().getName());
            return " supplyAsync!";
        }).thenAccept(s ->
                System.out.println(s + " thenAccept, thread: " + Thread.currentThread().getName())
        );

        CompletableFuture.supplyAsync(() -> {
            System.out.print("Hello, " + Thread.currentThread().getName());
            return " supplyAsync!";
        }).thenRun(() ->
                System.out.println(" thenRun, thread: " + Thread.currentThread().getName())
        );

        CompletableFuture.supplyAsync(() -> {
            System.out.print("Hello, " + Thread.currentThread().getName());
            return " supplyAsync!";
        }).thenRunAsync(() ->
                System.out.println(" thenRunAsync, diff thread: " + Thread.currentThread().getName())
        );

        // - 원하는 Executor(쓰레드풀)를 사용해서 실행할 수도 있다.(Executor 사용하지 않아도 기본은 ForkJoinPool.commonPool())
        // - ForkJoin 프레임워크: dequeue 를 방식을 써서 작업 단위를, 자기가 파생시키는 sub task 들을 잘게 쪼개서 다른 스레드에 분산시키고,
        //                     모아서(join) 그 결과 값을 도출해내는 프레임워크
        // dequeue: 맨 마지막에 들어온게 먼저 나간다. 이걸 써서 자기 스레드가 할일이 없으면 스레드가 직접 dequeue 에서 할 일 가져와 처리하는 방식

        // supplyAsync 두 번째 인자로 주면 스레드풀 이름이 달라진다
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        CompletableFuture.supplyAsync(() -> {
            System.out.println("supplyAsync run!" + Thread.currentThread().getName());
            return "Hi";
        }, executorService).thenRun(() -> {
            System.out.println("원하는 Executor(스레드풀) 사용했다!" + Thread.currentThread().getName());
        });
        executorService.shutdown();
    }
}
