package me.jenny.java8to11._7_etc;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class ParallelSortMain {
    public static void main(String[] args) {
        // Arrays.parallelSort(): Fork/Join 프레임워크를 사용해서 배열을 병렬로 정렬하는 기능을 제공한다.
        int size = 1500;
        int[] numbers = new int[size];
        Random random = new Random();

        IntStream.range(0, size).forEach(i -> numbers[i] = random.nextInt());
        long start = System.nanoTime();
        // thread 를 하나만 쓴다.
        Arrays.sort(numbers);
        System.out.println("serial sorting took " + (System.nanoTime() - start));

        IntStream.range(0, size).forEach(i -> numbers[i] = random.nextInt());
        start = System.nanoTime();
        // parallelSort: 병렬 정렬 알고리즘
        // - 배열을 둘로 계속 쪼갠다.
        // - 합치면서 정렬한다.
        // - 알고리즘 효율성은 같다. 시간: O(n logN), 공간: O(n)
        Arrays.parallelSort(numbers);
        System.out.println("parallel sorting took " + (System.nanoTime() - start));
    }
}
