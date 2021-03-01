package me.jenny.java8to11._6_completablefuture;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class CallableFutureMain {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<String> callable1 = () -> {
            Thread.sleep(2000L);
            return "Hello";
        };
        Callable<String> callable2 = () -> {
            Thread.sleep(3000L);
            return "Java";
        };
        Callable<String> callable3 = () -> {
            Thread.sleep(1000L);
            return "Jenny";
        };

        // Callable: Runnable 과 유사하지만 작업의 결과를 받을 수 있다.
        // Future: 비동기적인 작업의 현재 상태를 조회하거나 결과를 가져올 수 있다.
        //         https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/Future.html
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        // submit 은 리턴으로 Future 를 받을 수 있다.
        Future<String> helloFuture = executorService.submit(callable1);

        // 작업 상태 확인하기 isDone()
        // - 완료 했으면 true, 아니면 false 를 리턴한다.
        System.out.println("isDone: " + helloFuture.isDone());
        System.out.println("Started!"); // 여기까진 안기다리고 실행된다.

        // 결과를 가져오기 get()
        // - get 만난 순간, 결과값 갖고올 때까지 기다린다 - Blocking call!
        //   -> Callable 썼다고 빨라지진 않는다.
        // - 타임아웃(최대한으로 기다릴 시간)을 설정할 수 있다.
        helloFuture.get();

        // 작업 취소하기 cancel()
        // - 취소 했으면 true 못했으면 false 를 리턴한다.
        // - cancel 했는데 get 하면 에러난다. isDone()은 무조건 true.
        // - parameter 로 true 전달하면 현재 진행중인 쓰레드를 interrupt, 그렇지 않으면 현재 진행중인 작업이 끝날때까지 기다린다.
        boolean cancel = helloFuture.cancel(false);
        System.out.println("cancel: " + cancel);

        System.out.println("isDone: " + helloFuture.isDone());
        System.out.println("End!!");
        executorService.shutdown();

        System.out.println("--------------------");
        ExecutorService fixedExecutorService = Executors.newFixedThreadPool(4);

        // 여러 작업 동시에 실행하기 invokeAll()
        // - 동시에 실행한 작업 중 제일 오래 걸리는 작업만큼 시간이 걸린다.
        List<Future<String>> futureInvokeAll = fixedExecutorService.invokeAll(Arrays.asList(callable1, callable2, callable3));
        for (Future future : futureInvokeAll) {
            System.out.println(future.get());
        }

        // 여러 작업 중에 하나라도 먼저 응답이 오면 끝내기 invokeAny()
        // - 동시에 실행한 작업 중 제일 짧게 걸리는 작업만큼 시간이 걸린다.
        // - 블록킹 콜이다.
        String result = fixedExecutorService.invokeAny(Arrays.asList(callable1, callable2, callable3));
        System.out.println("invokeAny: " + result);
        fixedExecutorService.shutdown();
    }
}
