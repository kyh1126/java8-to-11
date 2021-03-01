package me.jenny.java8to11._6_completablefuture;

public class ThreadMain {
    public static void main(String[] args) throws InterruptedException {
        // Default 스레드는 main 스레드이다.
        System.out.println(Thread.currentThread().getName());

        MyThread myThread = new MyThread();
        myThread.start();   // 별도의 스레드가 만들어진다.

        // 순서는 보장 못한다.
        System.out.println("Hello: " + Thread.currentThread().getName());

        // Runnable 구현(익명 클래스)로 스레드 생성 가능
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Runnable thread1: " + Thread.currentThread().getName());
            }
        });
        thread1.start();

        // 자바 8부터 람다로 스레드 생성 가능
        Thread thread2 = new Thread(() -> System.out.println("Lambda thread2: " + Thread.currentThread().getName()));
        thread2.start();

        // 쓰레드 주요 기능
        // - 현재 쓰레드 멈춰두기(sleep): 다른 쓰레드가 처리할 수 있도록 기회를 주지만 그렇다고 락을 놔주진 않는다.(잘못하면 데드락 걸릴 수 있겠죠)
        // - 다른 쓰레드 깨우기(interrupt): 다른 쓰레드를 깨워서 interruptedException 발생시킨다. 그 에러가 발생했을 때 할 일은 코딩하기 나름.
        //                             종료시킬 수도 있고, 계속 하던일 할 수도 있고.
        // - 다른 쓰레드 기다리기(join): 다른 쓰레드가 끝날 때까지 기다린다.
        Thread thread3 = new Thread(() -> {
            while (true) {
                System.out.println("interrupt test thread: " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    System.out.println("Interrupted... exit!");
                    return; // 이거 안하면 종료 안되고 루프 계속 돈다.
                }
            }
        });
        thread3.start();
        Thread.sleep(3000L);
        thread3.interrupt();

        Thread thread4 = new Thread(() -> {
            System.out.println("join test thread: " + Thread.currentThread().getName());
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
        });
        thread4.start();
        // 기다릴 thread 에 join 을 붙여서 현재 스레드가 이 스레드를 대기하게 한다.
        thread4.join();
        System.out.println(thread4.getName() + " is finished, this is " + Thread.currentThread().getName());
    }

    static class MyThread extends Thread {
        @Override
        public void run() {
            try {
                Thread.sleep(15L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread: " + Thread.currentThread().getName());
            super.run();
        }
    }
}
