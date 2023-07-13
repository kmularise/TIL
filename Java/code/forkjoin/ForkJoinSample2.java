package forkjoin;

import java.util.concurrent.ForkJoinPool;

public class ForkJoinSample2 {
    static final ForkJoinPool mainPool = new ForkJoinPool();

    public static void main(String[] args) {
        ForkJoinSample2 forkJoinSample2 = new ForkJoinSample2();
        forkJoinSample2.calculate();
    }

    private void calculate() {
        long from = 0;
        long to = 10;
        GetSum2 getSum2 = new GetSum2(from, to);
        Long result = mainPool.invoke(getSum2);
        System.out.println("from = " + from);
        System.out.println("to = " + to);
        System.out.println("result = " + result);
    }
}
