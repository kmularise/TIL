package stream.parallel;

import java.util.Arrays;
import java.util.List;

public class StreamParallelTest {
    public void executeSequential(){
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4);
        numbers.stream().forEach(number ->
                System.out.println("number = " + number + " " + Thread.currentThread().getName()));
    }
    public void parallelSequential(){
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4);
        numbers.parallelStream().forEach(number ->
                System.out.println("number = " + number + " " + Thread.currentThread().getName()));
    }

    public void parallelSum() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4);
        //Integer sumResult = numbers.parallelStream().reduce(5,Integer::sum);
        Integer sumResult = numbers.parallelStream().reduce(0,Integer::sum) + 5;
        System.out.println("sumResult = " + sumResult);

    }
    public void sequentialSum() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4);
        Integer sumResult = numbers.stream().reduce(5,Integer::sum);
        System.out.println("sumResult = " + sumResult);

    }

    public static void main(String[] args) {
        StreamParallelTest test = new StreamParallelTest();
        //test.executeSequential();
//        //System.out.println("System.getProperty() = " + System.getProperty("java.util.concurrent.ForkJoinPool.common.parallelism"));
//        test.parallelSequential();
        test.parallelSum();
        test.sequentialSum();
    }

}
