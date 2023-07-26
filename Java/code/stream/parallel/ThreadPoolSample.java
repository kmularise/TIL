package stream.parallel;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;


public class ThreadPoolSample {
    public void showCommonThreadPool() throws ExecutionException, InterruptedException {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4);
        ForkJoinPool commonPool = ForkJoinPool.commonPool();
        System.out.println("commonPool.toString() = " + commonPool.toString());
        System.out.println("commonPool.getPoolSize() = " + commonPool.getPoolSize());
        int sum = commonPool.submit(
                () -> numbers.parallelStream().reduce(0, Integer::sum)).get();
        System.out.println("sum = " + sum);
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadPoolSample poolSample = new ThreadPoolSample();
        poolSample.showCommonThreadPool();
    }
}
