package stream.lazy;


import java.time.LocalDateTime;
import java.util.function.Supplier;

public class LazySample {
    static boolean compute(String str) {
        LocalDateTime start = LocalDateTime.now();
        System.out.println("start = " + start);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignore) {
            System.out.println("error!");
        }
        LocalDateTime end = LocalDateTime.now();
        System.out.println("end = " + end);
        return str.contains("a");
    }

    static String eagerMatch(boolean b1, boolean b2) {
        return b1 && b2 ? "match" : "incompatible";
    }

    static void eagerSolution() {
        boolean b1 = compute("Hello_1");
        boolean b2 = compute("Hello_2");
        String result1 = eagerMatch(b1, b2);
        System.out.println("result1 = " + result1);
    }

    //너무 복잡하다
    static void lazySolutionInJava7() {
        String result2 = compute("Hello_1") && compute("Hello_2") ? "match" : "incompatible!";
        System.out.println("result2 = " + result2);
    }

    static void lazySolutionInJava8() {
        Supplier<Boolean> a = () -> compute("Hello_1");
        Supplier<Boolean> b = () -> compute("Hello_2");
        lazyMatch(a, b);
    }

    private static String lazyMatch(Supplier<Boolean> a, Supplier<Boolean> b) {
        return a.get() && b.get() ? "match" : "incompatible";
    }

    public static void main(String[] args) {
        //eagerSolution();
        //lazySolutionInJava7();
        lazySolutionInJava8();

    }

}
