package me.jenny.java8to11._6_completablefuture;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class CompletableFutureMain2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 조합하기
        // - thenCompose(): 두 작업이 서로 이어서 실행하도록 조합
        // - thenCombine(): 두 작업을 독립적으로 실행하고, 둘 다 종료 되었을 때 콜백 실행(BiFunction)
        // - allOf(): 여러 작업을 모두 실행하고 모든 작업 결과에 콜백 실행
        // - anyOf(): 여러 작업 중 가장 빨리 끝난 하나의 결과에 콜백 실행

        CompletableFuture<String> completableFuture1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello, " + Thread.currentThread().getName());
            return "Hi ";
        });

        CompletableFuture<String> future1 =
                completableFuture1.thenCompose(CompletableFutureMain2::getCompletableFuture2);
        System.out.println(future1.get());

        CompletableFuture<String> future2 =
                completableFuture1.thenCombine(getCompletableFuture2(""), (c1, c2) -> c1 + " " + c2);
        System.out.println(future2.get());

        // 이 Task 들의 결과 값들이 모두 동일한 타입일 보장도 없고, 어떤 Task 에선 Exception 이 날 수도 있다.
        CompletableFuture.allOf(completableFuture1, getCompletableFuture2(""))
                .thenAccept(System.out::println);   // 이렇게 하면 null.

        List<CompletableFuture<String>> futures = Arrays.asList(completableFuture1, getCompletableFuture2(""));
        CompletableFuture[] futuresArray = futures.toArray(new CompletableFuture[futures.size()]);

        CompletableFuture<List<String>> results = CompletableFuture.allOf(futuresArray)
                .thenApply(v -> futures.stream()
                        // join: get 이랑 같은데, Unchecked Exception 을 발생시킨다.
                        .map(CompletableFuture::join).collect(Collectors.toList()));
        results.get().forEach(System.out::println);

        CompletableFuture<Void> future =
                CompletableFuture.anyOf(completableFuture1, getCompletableFuture2(""))
                        .thenAccept(System.out::println);
        future.get();

        // 예외처리
        // - exeptionally(Function): 에러 있을 때만 Function 실행
        // - handle(BiFunction): 첫 번째 파라미터는 정상 결과값, 두 번째는 exception 발생했을 때 에러.
        boolean throwError = false; // let's change this value!
        CompletableFuture<String> ex1 = CompletableFuture.supplyAsync(() -> {
            if (throwError) {
                throw new IllegalArgumentException();
            }
            System.out.println("hey, " + Thread.currentThread().getName());
            return "exceptionally!";
        }).exceptionally(ex -> "Error!");
        System.out.println(ex1.get());

        CompletableFuture<String> ex2 = CompletableFuture.supplyAsync(() -> {
            if (throwError) {
                throw new IllegalArgumentException();
            }
            System.out.println("hey, " + Thread.currentThread().getName());
            return "Success!";
        }).handle((result, ex) -> {
            if (ex != null) {
                System.out.println(ex);
                return "Error!";
            }
            return result;
        });
        System.out.println(ex2.get());
    }

    private static CompletableFuture<String> getCompletableFuture2(String message) {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("World!, " + Thread.currentThread().getName());
            return message + "World!";
        });
    }
}
